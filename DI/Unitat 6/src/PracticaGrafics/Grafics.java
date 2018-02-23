package PracticaGrafics;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class Grafics extends JFrame {
    JTabbedPane tabbedPane;
    JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel5;

    public Grafics() {
        tabbedPane = new JTabbedPane();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jPanel5 = new JPanel();
        getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));

        practica1();
        practica2();
        practica3();
        practica4();
        practica5();

        tabbedPane.addTab("Bar chart", jPanel1);
        tabbedPane.addTab("Area chart image", jPanel2);
        tabbedPane.addTab("Pie chart", jPanel3);
        tabbedPane.addTab("LineXY chart", jPanel4);
        tabbedPane.addTab("Line chart", jPanel5);

        add(tabbedPane);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @SuppressWarnings("Duplicates")
    private void practica1() {

        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Example Bar Chart", "Any", "Població en Milions", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        jPanel1.add(panel);

    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
// Poblacio en 2005
        dataset.addValue(10, "USA", "2005");
        dataset.addValue(15, "India", "2005");
        dataset.addValue(20, "China", "2005");
// Poblacio en 2010
        dataset.addValue(15, "USA", "2010");
        dataset.addValue(20, "India", "2010");
        dataset.addValue(25, "China", "2010");
// Poblacio en 2015
        dataset.addValue(20, "USA", "2015");
        dataset.addValue(25, "India", "2015");
        dataset.addValue(30, "China", "2015");
        return dataset;
    }

    @SuppressWarnings("Duplicates")
    private void practica2() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createAreaChart(
                "Example Bar Chart", "Any", "Població en Milions", dataset,
                PlotOrientation.VERTICAL, true, true, false);
//        ChartPanel panel = new ChartPanel(chart);
        BufferedImage bufferedImage = chart.createBufferedImage(500,500);
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        JLabel jLabel = new JLabel(imageIcon);
        jPanel2.add(jLabel);
    }

    @SuppressWarnings("Duplicates")
    private void practica3() {
        PieDataset dataset = createDatasetPie();
        JFreeChart chart = ChartFactory.createPieChart("Dataset", dataset,
                true, true, false);

        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Notes {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);
        ChartPanel panel = new ChartPanel(chart);

        jPanel3.add(panel);
    }



    private PieDataset createDatasetPie() {
        DefaultPieDataset dataset= new DefaultPieDataset();
        dataset.setValue("80-100", 120);
        dataset.setValue("60-79", 80);
        dataset.setValue("40-59", 20);
        dataset.setValue("20-39", 7);
        dataset.setValue("0-19", 3);
        return dataset;
    }

    private void practica4(){
        XYDataset dataset = createDatasetXYLine();
        JFreeChart chart = ChartFactory.createXYLineChart("Exemple XY Line Chart ", "X-Axis", "Y-Axis",
                dataset, PlotOrientation.VERTICAL,true,true,false);
        ChartPanel panel = new ChartPanel(chart);
        jPanel4.add(panel);
    }

    private XYDataset createDatasetXYLine() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Y = X + 2");
        series.add(2, 4);
        series.add(8, 10);
        series.add(10, 12);
        series.add(13, 15);
        series.add(17, 19);
        series.add(18, 20);
        series.add(21, 23);
        dataset.addSeries(series);

        return dataset;
    }

    private void practica5(){
        DefaultCategoryDataset dataset = createDatasetLine();
        JFreeChart chart = ChartFactory.createLineChart(
                "Site Traffic (WWW.2DAM.COM)", // titol
                "Data", // Etiqueta Eix X
                "Nombrer de Visitants", // Etiqueta Eix Y
                dataset
        );
        ChartPanel panel = new ChartPanel(chart);
        jPanel5.add(panel);
    }

    private DefaultCategoryDataset createDatasetLine() {
        String series1 = "Visitants";
        String series2 = "Visitants unics";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(200, series1, "2016-12-19");
        dataset.addValue(150, series1, "2016-12-20");
        dataset.addValue(100, series1, "2016-12-21");
        dataset.addValue(210, series1, "2016-12-22");
        dataset.addValue(240, series1, "2016-12-23");
        dataset.addValue(195, series1, "2016-12-24");
        dataset.addValue(245, series1, "2016-12-25");
        dataset.addValue(150, series2, "2016-12-19");
        dataset.addValue(130, series2, "2016-12-20");
        dataset.addValue(95, series2, "2016-12-21");
        dataset.addValue(195, series2, "2016-12-22");
        dataset.addValue(200, series2, "2016-12-23");
        dataset.addValue(180, series2, "2016-12-24");
        dataset.addValue(230, series2, "2016-12-25");
        return dataset;
    }



        private static void createAndShowGUI() {
        //Create and set up the window.
        new Grafics();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
