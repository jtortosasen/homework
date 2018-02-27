package com.wasdf.view;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PrintPanel extends JPanel {
    public PrintPanel(){
        super();
        setLayout(new FlowLayout());
        JButton jButton = new JButton("1");
        JButton jButton2= new JButton("2");
        add(jButton);
        add(jButton2);

        jButton.addActionListener(e -> event1());
        jButton2.addActionListener(e -> event2());
    }

    private void event1(){
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros PDF", "pdf");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();;
            try {
                crearPdf(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void crearPdf(File f) throws IOException{
        String path = f.getAbsolutePath();
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        Table tableTitle = new Table(new float[]{1});
        tableTitle.setWidthPercent(100);
        tableTitle.addCell(new Cell(1,2).add("Departamento"));
        document.add(tableTitle);
        Table tableInfo =

    }

    public void crearPdf1(File f) throws IOException {
        String path = f.getAbsolutePath();
        PdfWriter writer = new PdfWriter(path);
        PdfDocument pdf = new PdfDocument(writer);

        // Initializar el document, ara si definim el tamany de pàgina per si la taula es molt gran
        // que s’adapte al tamany que volem de resultat, a més li definim els marges.

        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        //Crear la taula definint les columnes amb les proporcions de tamany de columna

        Table table = new Table(new float[]{2, 2});
        table.setWidthPercent(100);
        //Es crea la capçalera de la factura per això creem dues parts, la de l'esquerra serà altra taula
        //que contindrà les dades del client

        Table tableClient = new Table(new float[]{2, 2}).setBorder(Border.NO_BORDER);
        tableClient.setWidthPercent(100);

        //Primer mostrem el text FACTURA amb un RowSpan de 2
        Cell cela = new Cell(1, 2).add("FACTURA:").setBorder(Border.NO_BORDER).setFont(bold);
        tableClient.addCell(cela);
        //Dins de taula per mantindre alineat els components fiquem etiqueta i valor
        // Estes dades vindrien de la base de dades, segons el client que tenim seleccionat

        tableClient.addCell(new Cell().add(new Paragraph("Nom: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("Sergi").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Cognoms: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("Cognom1 Cognom2").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Num Factura: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("A45632").setFont(font)));
        tableClient.addCell(new Cell().add(new Paragraph("Data: ").setFont(bold)));
        tableClient.addCell(new Cell().add(new Paragraph("01/02/2017 ").setFont(font)));
        table.addCell(tableClient).setVerticalAlignment(VerticalAlignment.BOTTOM);
        //Ara generem una taula amb les dades de l'empresa i el logo
        Table tableEmpresa = new Table(new float[]{2, 2});
        tableEmpresa.setWidthPercent(100);
        Image fox = new Image(ImageDataFactory.create(DOG));

        //En aquest cas apliquem un colspan 2 per que ocupe tot el ample
        // el primer parametre indica les files q ocupa i el segon les columnes
        // incloguem el logo i centrat a la taula

        tableEmpresa.addCell(new Cell(1, 2).add(new Paragraph().add(fox)).setTextAlignment(TextAlignment.CENTER));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Empresa: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("2 DAM Enterprise").setFont(font)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("CIF: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Q2345234P").setFont(font)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Raó Social: ").setFont(bold)));
        tableEmpresa.addCell(new Cell().add(new Paragraph("Camí la Dula nº 31").setFont(font)));
        table.addCell(tableEmpresa);

        // Afegir la taula que conté la capçalera a la taula i un paragraf buit per separar
        document.add(table);
        document.add(new Paragraph(""));
        //Ara es crea la Taula que genera el cos de la factura amb els articles
        Table table2 = new Table(new float[]{4, 1, 3, 3});
        table2.setWidthPercent(100);
        //Capçalera de la taula de les linies de factura
        process(table2, "Article", bold, true);
        process(table2, "Preu", bold, true);
        process(table2, "Quantitat", bold, true);
        process(table2, "IVA", bold, true);

        //Les linies de factura ens vindran de la base de dades,
        // Aci les generem automàticament
        for (int i = 0; i < 5; i++) {
            process(table2, "Article" + i, font, false);
            process(table2, Integer.toString(i * 2), font, false);
            process(table2, Integer.toString(i), font, false);
            process(table2, "21%", font, false);
        }
        // Afegir la taula del cos de la factura
        document.add(table2);
        //Afegim un paragraf en blanc per separar
        document.add(new Paragraph(""));
        //Mostrar el total o fins i tot el desglose de IVA i dades adicionals
        Table tableTotal = new Table(new float[]{2, 2});
        tableTotal.setWidthPercent(100);
        tableTotal.addCell(new Cell().add(new Paragraph("TOTAL: ").setFont(bold)));
        tableTotal.addCell(new Cell().add(new Paragraph("60 €: ").setFont(bold)).setTextAlignment(TextAlignment.RIGHT));
        document.add(tableTotal);
        document.add(new Paragraph(""));
        document.add(new Paragraph("Inscrito en el registro mercantil bla bla bla bla"));

        Paragraph footer = new Paragraph("this is a footer");

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
