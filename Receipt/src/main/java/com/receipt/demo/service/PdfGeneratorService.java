package com.receipt.demo.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.receipt.demo.model.ReceiptLine;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generatePdfReceipt(List<ReceiptLine> lines) {
        Document document = new Document(PageSize.A6); // Typical small receipt size
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            for (ReceiptLine line : lines) {
                // Determine Font Style
                int fontStyle = Font.NORMAL;
                if ("BOLD".equalsIgnoreCase(line.getStyle())) {
                    fontStyle = Font.BOLD;
                } else if ("ITALIC".equalsIgnoreCase(line.getStyle())) {
                    fontStyle = Font.ITALIC;
                }

                // Determine Font Size
                float fontSize = "2X".equalsIgnoreCase(line.getFontSize()) ? 16 : 10;

                Font font = FontFactory.getFont(FontFactory.HELVETICA, fontSize, fontStyle);
                Paragraph para = new Paragraph(line.getText(), font);

                // Determine Alignment
                if ("CENTER".equalsIgnoreCase(line.getAlignment())) {
                    para.setAlignment(Element.ALIGN_CENTER);
                } else if ("RIGHT".equalsIgnoreCase(line.getAlignment())) {
                    para.setAlignment(Element.ALIGN_RIGHT);
                } else {
                    para.setAlignment(Element.ALIGN_LEFT);
                }

                document.add(para);
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}