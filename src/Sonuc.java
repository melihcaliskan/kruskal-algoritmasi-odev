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
import java.util.Random;

public class Sonuc extends JPanel {
    static JPanel panel = new JPanel();

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

        int kumedeki_eleman_sayisi = kume.getItemCount();
        int bolum = kumedeki_eleman_sayisi / kume_sayisi;
        System.out.println("Bölüm: " + bolum);
        int gecici_kume_sayisi = 0;

        while (gecici_kume_sayisi < kume_sayisi) {
            XYSeries veri = new XYSeries("Grup " + (gecici_kume_sayisi + 1));

            System.out.println("Şuanki küme: " + gecici_kume_sayisi);
            System.out.println("-------------------");

            // 0-20
            // 20-40
            // 40-60
            // arasında çalışacak for döngüsü.
            // Küme sayısından sırayla 0-1-2 gelecek.
            for (int i = gecici_kume_sayisi * bolum; i < bolum + (gecici_kume_sayisi * bolum); i++) {
                System.out.println("X: " + kume.getDataItem(i).getX() + ", Y: " + kume.getDataItem(i).getY());
                veri.add(kume.getDataItem(i).getX(), kume.getDataItem(i).getY());
            }
            dataset.addSeries(veri);


            //İSTATİSTİK KISMI İÇİN VERİLER.
            JLabel ad = new JLabel((gecici_kume_sayisi + 1) + ". küme");
            ad.setFont(new java.awt.Font("Trebuchet", 1, 16));

            JLabel nok_sayisi = new JLabel("Nokta sayısı: " + veri.getItemCount());
            nok_sayisi.setFont(new java.awt.Font("Trebuchet", 0, 16));
            JLabel ort_nokta = new JLabel("Orta noktası: ");
            ort_nokta.setFont(new java.awt.Font("Trebuchet", 0, 16));
            JLabel standart_sapma = new JLabel("Standart sapma : ");
            standart_sapma.setFont(new java.awt.Font("Trebuchet", 0, 16));

            panel.add(ad);
            panel.add(nok_sayisi);
            panel.add(ort_nokta);
            panel.add(standart_sapma);
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));

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