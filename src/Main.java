import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * @author imssbora
 */
class Test extends JFrame {
    private static XYSeriesCollection dataset = new XYSeriesCollection();

    private static JPanel createDataSetPanel() {
        XYDataset dataset = createDataset();
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

    private static XYDataset createDataset() {
        XYSeries series1 = new XYSeries("Grup 1");
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            int randomInt = r.nextInt(20) + 0;
            int randomInt2 = r.nextInt(20) + 0;

            series1.add(randomInt, randomInt2);
        }
        dataset.addSeries(series1);

        XYSeries series2 = new XYSeries("Grup 2");
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            int randomInt = r.nextInt(20) + 15;
            int randomInt2 = r.nextInt(20) + 15;
            series2.add(randomInt, randomInt2);
        }
        dataset.addSeries(series2);

        XYSeries series3 = new XYSeries("Grup 3");
        for (int i = 0; i < 30; i++) {
            Random r = new Random();
            int randomInt = r.nextInt(20) + 20;
            int randomInt2 = r.nextInt(20) + 20;
            series3.add(randomInt, randomInt2);
        }
        dataset.addSeries(series3);
        return dataset;
    }

    private static JPanel istatistikleriOlustur() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel adet = new JLabel("İSTATİSTİKLER");
        adet.setFont(new java.awt.Font("Trebuchet", 1, 25));
        panel.add(adet);

        for (int i = 0; i < 3; i++) {
            JLabel ad = new JLabel(i + ". küme");
            ad.setFont(new java.awt.Font("Trebuchet", 1, 16));

            JLabel nok_sayisi = new JLabel("Nokta sayısı: ");
            nok_sayisi.setFont(new java.awt.Font("Trebuchet", 0, 16));
            JLabel ort_nokta = new JLabel("Ortak noktası: ");
            ort_nokta.setFont(new java.awt.Font("Trebuchet", 0, 16));
            JLabel standart_sapma = new JLabel("Standart sapma : ");
            standart_sapma.setFont(new java.awt.Font("Trebuchet", 0, 16));

            panel.add(ad);
            panel.add(nok_sayisi);
            panel.add(ort_nokta);
            panel.add(standart_sapma);
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        }
        return panel;
    }

    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 800));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238), 20));

        JLabel adet = new JLabel("Adet:");
        panel.add(adet);

        JTextField adetSayisi = new JTextField();
        panel.add(adetSayisi);

        JButton ekleButonu = new JButton("Rastgele Nokta Ekle");
        ekleButonu.setPreferredSize(new Dimension(50, 100));
        panel.add(ekleButonu);


        ekleButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kullanici_adet = Integer.parseInt(adetSayisi.getText());
                ekleButonuFonksiyonu(e, kullanici_adet);
                adetSayisi.setText("");
            }
        });

        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel kumeLabel = new JLabel("Küme Sayısı:");
        panel.add(kumeLabel);

        JTextField kumeSayisi = new JTextField();
        kumeSayisi.setFont(adetSayisi.getFont().deriveFont(10f));
        panel.add(kumeSayisi);

        JButton calistirButonu = new JButton("Çalıştır");
        ekleButonu.setPreferredSize(new Dimension(20, 10));
        panel.add(calistirButonu);

        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(istatistikleriOlustur());


        return panel;
    }

    public static void showScene() {
        JFrame frame = new JFrame("Algoritma Analizi");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1200, 800));

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 90));

        frame.add(createMainPanel());
        frame.add(createDataSetPanel());
        frame.pack();

    }

    private static void ekleButonuFonksiyonu(java.awt.event.ActionEvent evt, int adet) {
        XYSeries series1 = new XYSeries("Grup 4");
        for (int i = 0; i < adet; i++) {
            Random r = new Random();
            int randomInt = r.nextInt(60) + 0;
            int randomInt2 = r.nextInt(60) + 0;
            series1.add(randomInt, randomInt2);
        }
        dataset.addSeries(series1);

    }


    public static void main(String[] args) {
        showScene();
    }
}