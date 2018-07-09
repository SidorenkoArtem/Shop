import model.CashDesk;
import model.GeneratorCashier;
import model.GeneratorPurchaser;
import javax.swing.*;
import java.awt.*;

public class Runner extends JFrame {
    public static final Integer CASH_COUNT = 3;
    public static final Integer PURCHASER_COUNT = 40;
    public JPanel panel = new JPanel();

    public Runner() {
        setBounds(200, 200, 500, 500);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        panel.setBounds(0, 0, 500, 500);
        panel.setBackground(Color.GREEN);
        contentPane.add(panel);
    }

    public static void main(String[] args) {
        final Runner runner = new Runner();
        runner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final GeneratorPurchaser generatorPurchaser = new GeneratorPurchaser(PURCHASER_COUNT, runner.panel);
        final GeneratorCashier generatorCashier = new GeneratorCashier(CASH_COUNT, runner.panel);
        generatorCashier.runCashiers();
        new Thread(generatorPurchaser).start();
        runner.setVisible(true);
        runner.paint(runner.panel.getGraphics());
        System.out.println("Key Set = " + CashDesk.purchasersCashDesks.keySet().size());
    }

    public void paint(Graphics g) {
        g.drawRect(20, 40, 60, 50 + this.CASH_COUNT * 50);
    }
}
