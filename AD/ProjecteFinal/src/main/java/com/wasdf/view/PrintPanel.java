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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.wasdf.Util.Util;
import com.wasdf.model.Departments;
import com.wasdf.model.Employees;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PrintPanel extends JPanel {
    MainView mainView;

    public PrintPanel(MainView mainView) {
        super();
        this.mainView = mainView;
        setLayout(new FlowLayout());
        JButton jButton = new JButton("Imprimir empleados de todos los departamentos");
        JButton jButton2 = new JButton("Imprimir todos los empleados");
        add(jButton);
        add(jButton2);

        jButton.addActionListener(e -> event1());
        jButton2.addActionListener(e -> event2());
    }

    private void event2() {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros PDF", "pdf");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();
            try {
                crearPdf(f, 2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void event1() {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ficheros PDF", "pdf");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();
            try {
                crearPdf(f, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void crearPdf(File f, int option) throws IOException {

        String path = f.getAbsolutePath();
        PdfWriter writer = new PdfWriter(path + ".pdf");
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);

        ArrayList<Departments> listDepartments = mainView.getDepartments();
        ArrayList<Employees> listEmployees;
        if (option == 1) {
            PieDataset dataset = createDatasetPie(listDepartments);
            JFreeChart chart = ChartFactory.createPieChart("Empleados por departamento", dataset,
                    true, true, false);

            BufferedImage bufferedImage = chart.createBufferedImage(300, 300);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Image image = new Image(ImageDataFactory.create(byteArray));
            Table tableImage = new Table(1);
            tableImage.addCell(new Cell().add(new Paragraph().add(image)).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            document.add(tableImage);
        }
        for (Departments departments : listDepartments) {
            if (option == 1 && !listDepartments.isEmpty()) {
                Table tableTitle = new Table(new float[]{3});
                tableTitle.setWidthPercent(100);
                tableTitle.addCell(new Cell().add(new Paragraph(departments.getDeptNo() + " " + departments.getDeptName())).setFont(bold));
                document.add(tableTitle);
            }
            if (option == 2) {
                listEmployees = mainView.getEmployeesFromDepartment(departments.getDeptNo(), 300);
            } else {
                listEmployees = mainView.getEmployeesFromDepartment(departments.getDeptNo(), 50);
            }
            Table tableInfo = new Table(new float[]{(float) 1.5, 2, 2, 1, 2, 2});
            tableInfo.setWidthPercent(100);
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("EmpNo")).setFont(bold));
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("FirstName")).setFont(bold));
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("LastName")).setFont(bold));
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("Gender")).setFont(bold));
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("Birthdate")).setFont(bold));
            tableInfo.addHeaderCell(new Cell().add(new Paragraph("Hiredate")).setFont(bold));
            for (Employees employees : listEmployees) {
                tableInfo.addCell(new Cell().add(new Paragraph(String.valueOf(employees.getEmpNo()))).setFont(font));
                tableInfo.addCell(new Cell().add(new Paragraph(employees.getFirstName())).setFont(font));
                tableInfo.addCell(new Cell().add(new Paragraph(employees.getLastName())).setFont(font));
                tableInfo.addCell(new Cell().add(new Paragraph(employees.getGender())).setFont(font));
                tableInfo.addCell(new Cell().add(new Paragraph(Util.dateToString(employees.getBirthDate()))).setFont(font));
                tableInfo.addCell(new Cell().add(new Paragraph(Util.dateToString(employees.getHireDate()))).setFont(font));
            }
            document.add(tableInfo);
            if (option == 1)
                document.add(new Paragraph());
        }
        document.close();
    }

    private PieDataset createDatasetPie(ArrayList<Departments> listDepartments) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<Integer> count = new ArrayList<>();

        for (Departments departments : listDepartments) {
            count.add(mainView.getCountEmployees(departments.getDeptNo()));
        }
        int i = 0;
        for (Integer value : count) {
            dataset.setValue(listDepartments.get(i).getDeptNo(), value);
            i++;
        }
        return dataset;
    }


}
