package com.vjykhade.api.spring.web.helper;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.*;
import com.vjykhade.api.spring.web.entity.CurrencyUpload;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PDFGeneratorHelper {

    public void GeneratePDF(List<CurrencyUpload> currencyUploadList, HttpServletResponse response)
    {
        try
        {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font fontTiltle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTiltle.setSize(20);
            Paragraph paragraph1 = new Paragraph("List of the Currency's Exchange", fontTiltle);
            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph1);

            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);
            table.setWidths(new int[] {3,3,3,3});
            table.setSpacingBefore(5);

            PdfPCell cell = new PdfPCell();

            cell.setBackgroundColor(CMYKColor.BLUE);
            cell.setPadding(5);
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setColor(CMYKColor.BLACK);
            cell.setPhrase(new Phrase("ID", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("From Currency", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("To Currency", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Conversion Multiple", font));
            table.addCell(cell);
            for (CurrencyUpload currencyUpload: currencyUploadList) {
                table.addCell(String.valueOf(currencyUpload.getId()));
                table.addCell(currencyUpload.getFromCurrency());
                table.addCell(currencyUpload.getToCurrency());
                table.addCell(currencyUpload.getConversionMultiple().toString());
            }
            document.add(table);
            document.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception in generating PDF file" +e);
        }
    }


}
