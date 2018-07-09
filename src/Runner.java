import model.GeneratorCashier;
import model.GeneratorPurchaser;

import javax.swing.*;
import java.awt.*;


public class Runner extends JFrame {
    Graphics g;
    JPanel panel = new JPanel();

    public Runner() {
        setBounds(200, 200, 500, 500);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        panel.setBounds(0, 0, 500, 500);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GeneratorPurchaser generatorPurchaser = new GeneratorPurchaser(20, runner.panel);
        GeneratorCashier generatorCashier = new GeneratorCashier(2, runner.panel);
        generatorCashier.runCashiers();
        new Thread(generatorPurchaser).start();

        runner.setVisible(true);
        runner.repaint();

        Graphics gr = runner.panel.getGraphics();
        gr.setColor(Color.BLACK);

//        gr.setColor(Color.WHITE);
//        gr.drawArc(51, 51, 40, 40, 120, 30);
    }
}
