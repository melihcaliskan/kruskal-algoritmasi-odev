import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Sonuc extends JPanel {
    static JPanel panel = new JPanel();
    static ArrayList<Float> oklidDizisi = new ArrayList<Float>();

    public Sonuc(XYSeries kume, int kume_sayisi) {
        add(istatistikleriOlustur());
        add(createDataSetPanel(kume, kume_sayisi));
    }

    private static XYSeriesCollection dataset = new XYSeriesCollection();

    private static JPanel createDataSetPanel(XYSeries kume, int kume_sayisi) {
        XYDataset dataset = createDataset(kume, kume_sayisi);
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Kruskal Algoritması",
                "",
                "",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 255, 255));
        plot.getRangeAxis().setVisible(false);
        plot.getDomainAxis().setVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(1000, 800));
        return panel;
    }

    private static XYDataset createDataset(XYSeries kume, int kume_sayisi) {

        JLabel adet = new JLabel("İSTATİSTİKLER");
        adet.setFont(new java.awt.Font("Trebuchet", 1, 25));
        panel.add(adet);


        XYSeriesCollection dataset = new XYSeriesCollection();

        List<Oklid> oklidDizisi = new ArrayList<Oklid>();

        for (int i = 0; i < kume.getItemCount(); i++) {
            int suankiX = kume.getDataItem(i).getX().intValue();
            int suankiY = kume.getDataItem(i).getY().intValue();

            for (int j = 0; j < kume.getItemCount(); j++) {

                int geciciX = kume.getDataItem(j).getX().intValue();
                int geciciY = kume.getDataItem(j).getY().intValue();

                if (suankiX != geciciX && suankiY != geciciY) {
                	oklidDizisi.add(new Oklid(suankiX, geciciX, suankiY, geciciY));

                    System.out.println("X: " + suankiX + ", Y: " + suankiY + " / Geçici X: " + geciciX + " Geçici Y: " + geciciY);
                    System.out.println("-------- " + i + " -----------");
                }
            }
        }
       
        // Tekrarlanan öğeleri siliyoruz.
        oklidDizisi = new ArrayList<Oklid>(new LinkedHashSet<Oklid>(oklidDizisi));


        // Diziyi sıralıyoruz.
        Collections.sort(oklidDizisi);
        
        for (int i = 0; i < oklidDizisi.size(); i++) {
            System.out.println(oklidDizisi.get(i).hesapla());
        }
        int kumedeki_eleman_sayisi = kume.getItemCount();
        int bolum = kumedeki_eleman_sayisi / kume_sayisi;
        System.out.println("Bölüm: " + bolum);
        int gecici_kume_sayisi = 0;

        while (gecici_kume_sayisi < kume_sayisi) {
            XYSeries veri = new XYSeries("Grup " + (gecici_kume_sayisi + 1));

            //System.out.println("Şuanki küme: " + gecici_kume_sayisi);
            //System.out.println("-------------------");

            // 0-20
            // 20-40
            // 40-60
            // arasında çalışacak for döngüsü.
            // Küme sayısından sırayla 0-1-2 gelecek.
            
            for (int i = gecici_kume_sayisi * bolum; i < bolum + (gecici_kume_sayisi * bolum); i++) {
                System.out.println("X: " + oklidDizisi.get(i).x1 + ", Y: " + oklidDizisi.get(i).y1);
                veri.add(oklidDizisi.get(i).x1, oklidDizisi.get(i).y1);
                veri.add(oklidDizisi.get(i).x2, oklidDizisi.get(i).y2);
            }
            dataset.addSeries(veri);
            gecici_kume_sayisi++;
        }
        return dataset;
    }

    private static JPanel istatistikleriOlustur() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Algoritma Analizi");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 800));

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 90));
        frame.pack();
    }

    public static double standartSapmaHesapla(double numArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;
        for (double num : numArray) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }
}