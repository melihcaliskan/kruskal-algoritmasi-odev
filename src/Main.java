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
 * @author
 */
class Main extends JFrame {

    static int baslangic_nokta_sayisi = 20;

    private static XYSeriesCollection dataset = new XYSeriesCollection();
    static XYSeries kume = new XYSeries("Kümelenmemiş Grup");

    private static JPanel createDataSetPanel() {
        XYDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Kümelenmemiş Noktalar",
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
        panel.setPreferredSize(new Dimension(600, 400));
        return panel;
    }

    private static XYDataset createDataset() {
        kumeyeElemanEkle(kume, baslangic_nokta_sayisi);
        dataset.addSeries(kume);
        return dataset;
    }

    private static JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(238, 238, 238), 20));

        JLabel suanki_adet = new JLabel("Nokta sayısı: " + baslangic_nokta_sayisi);
        suanki_adet.setFont(new java.awt.Font("Trebuchet", 1, 25));
        panel.add(suanki_adet);

        panel.add(new JSeparator(SwingConstants.HORIZONTAL));


        JLabel adet = new JLabel("Adet:");
        panel.add(adet);

        JTextField adetSayisi = new JTextField();
        adetSayisi.setText("20");
        adetSayisi.setFont(adetSayisi.getFont().deriveFont(10f));
        adetSayisi.setPreferredSize(new Dimension(120, 120));
        panel.add(adetSayisi);

        JButton ekleButonu = new JButton("Rastgele Nokta Ekle");
        ekleButonu.setPreferredSize(new Dimension(120, 50));
        panel.add(ekleButonu);


        ekleButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int kullanici_adet = Integer.parseInt(adetSayisi.getText());
                suanki_adet.setText("Nokta sayısı: " + String.valueOf(kullanici_adet + baslangic_nokta_sayisi));
                ekleButonuFonksiyonu(e, kullanici_adet);
                adetSayisi.setText("");
            }
        });

        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        JLabel kumeLabel = new JLabel("Küme Sayısı:");
        panel.add(kumeLabel);

        JTextField kumeSayisi = new JTextField();
        kumeSayisi.setText("3");
        kumeSayisi.setFont(kumeSayisi.getFont().deriveFont(10f));
        kumeSayisi.setPreferredSize(new Dimension(50, 80));
        panel.add(kumeSayisi);

        JButton calistirButonu = new JButton("Çalıştır");
        calistirButonu.setPreferredSize(new Dimension(50, 80));
        panel.add(calistirButonu);

        calistirButonu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Kümelenmemiş veri
                // Küme sayısı
                int kume_sayisi = Integer.parseInt(kumeSayisi.getText());

                JFrame frame = new JFrame("Sonuç");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new Sonuc(kume, kume_sayisi));
                frame.pack();
                frame.setVisible(true);
            }
        });

        return panel;
    }

    public static void showScene() {
        JFrame frame = new JFrame("Algoritma Analizi");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 400));

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 90));

        frame.add(createMainPanel());
        frame.add(createDataSetPanel());
        frame.pack();
    }

    private static void ekleButonuFonksiyonu(java.awt.event.ActionEvent evt, int adet) {
        kumeyeElemanEkle(kume, adet);
    }

    public static void kumeyeElemanEkle(XYSeries kume, int eleman_sayisi) {
        for (int i = 0; i < eleman_sayisi; i++) {
            Random r = new Random();
            int randomInt = r.nextInt(60) + 0;
            int randomInt2 = r.nextInt(60) + 0;
            kume.add(randomInt, randomInt2);
        }
    }


    public static void main(String[] args) {
        showScene();
        int kume_sayisi = Integer.parseInt("3");
        JFrame frame = new JFrame("Sonuç");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Sonuc(kume, kume_sayisi));
        frame.pack();
        frame.setVisible(true);
    }
}