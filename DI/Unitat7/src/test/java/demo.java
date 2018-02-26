import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.IOException;
import java.util.StringTokenizer;

import static com.itextpdf.kernel.pdf.PdfName.Table;

public class demo {
    public static final String DEST = "hola_mon.pdf";

    public static void main(String[] args) throws IOException {
        new demo().crearPdf(DEST);
    }

    public void crearPdf(String dest) throws IOException {
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{2, 2});
        table.setWidthPercent(100);
        Table tableClient = new Table(new float[]{2, 2}).setBorder(Border.NO_BORDER);
        tableClient.setWidthPercent(100);
        Cell cela = new Cell(1, 2).add("FACTURA:").setBorder(Border.NO_BORDER).setFont(bold);
        tableClient.addCell(cela);
        tableClient.addCell(new Cell().add(new Paragraph("Nom: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("Sergi").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Cognoms: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("Cognom1 Cognom2").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Num Factura: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("A45632").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Data: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("01/02/2017 ").setFont(font)));
        table.addCell(tableClient).setVerticalAlignment(VerticalAlignment.BOTTOM);
        Table tableEmpresa = new Table(new float[]{2, 2});
        tableEmpresa.setWidthPercent(100);
        Image fox = new Image(ImageDataFactory.create(FOX));
        tableEmpresa.addCell(new Cell(1, 2).add(new Paragraph().add(fox)).setTextAlignment(TextAlignment.CENTER));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Empresa: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("2 DAM Enterprise").setFont(font)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("CIF: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Q2345234P").setFont(font)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Raó Social: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Camí la Dula nº 31").setFont(font)));
        table.addCell(tableEmpresa);
        document.add(table);
        document.add(new Paragraph(""));
        Table table2 = new Table(new float[]{4, 1, 3});
        table2.setWidthPercent(100);
        process(table2, "Article", bold, true);
        process(table2, "Preu", bold, true);
        process(table2, "Quantitat", bold, true);
        for (int i = 0; i < 3; i++) {
            process(table2, "Article" + i, font, false);
            process(table2, Integer.toString(i * 2), font, false);
            process(table2, Integer.toString(i), font, false);
        }
        document.add(table2);
        document.add(new Paragraph(""));
        Table tableTotal = new Table(new float[]{2, 2});
        tableTotal.setWidthPercent(100);
        tableTotal.addCell(new Cell().add(new Paragraph("TOTAL: ").setFont(bold)));
        tableTotal.addCell(new Cell().add(new Paragraph("16 €: ").setFont(bold)).setTextAlignment(TextAlignment.RIGHT));
        document.add(tableTotal);
        document.add(new Paragraph(""));
        document.add(new Paragraph("Inscrito en el registro mercantil bla bla bla bla"));
        document.close();
    }

    public void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                table.addHeaderCell(
                        new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)).setBorder(Border.NO_BORDER));
            } else {
                table.addCell(new Cell().add(new Paragraph(tokenizer.nextToken()).setFont(font)));
            }
        }
    }
}
