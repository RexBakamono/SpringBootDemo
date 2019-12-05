package com.rex.springboot.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/itext")
public class ItextController {
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
//        test1();

    }

    @RequestMapping("/test1")
    public boolean test1() throws DocumentException, FileNotFoundException {
        FileOutputStream out = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\itext.pdf");

        //页面大小
        Rectangle rect = new Rectangle(PageSize.A4.rotate());
        //页面背景色
//        rect.setBackgroundColor(BaseColor.ORANGE);

        Document doc = new Document(rect);

        PdfWriter writer = PdfWriter.getInstance(doc, out);

        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);

        doc.addTitle("Title@sample");
        doc.addAuthor("Author@rensanning");
        doc.addSubject("Subject@iText sample");
        doc.addKeywords("Keywords@iText");
        doc.addCreator("Creator@iText");

        //页边空白
        doc.setMargins(10, 20, 30, 40);

        doc.open();



        // 条形码=================================================================
        String myString = "http://www.google.com";
        PdfContentByte cb = writer.getDirectContent();
        Barcode128 code128 = new Barcode128();
        code128.setCode(myString.trim());
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = code128.createImageWithBarcode(cb, null, null);
        code128Image.setAbsolutePosition(100,50);
        code128Image.scalePercent(125);
        doc.add(code128Image);
        // 条形码=================================================================

        // 二维码=================================================================
        BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
        Image qrcodeImage = qrcode.getImage();
        qrcodeImage.setAbsolutePosition(100,100);
        qrcodeImage.scalePercent(200);
        doc.add(qrcodeImage);
        // 二维码=================================================================




        doc.add(new Paragraph("Hello World"));
        doc.close();
        return true;
    }
}
