package retire;

import gbc.GBC;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.DoubleSummaryStatistics;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Retire {
    public static void init() {
        EventQueue.invokeLater(() ->
        {
            RetireFrame frame = new RetireFrame();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((dimension.width - frame.getWidth()) / 2, (dimension.height - frame.getHeight()) / 2);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class RetireFrame extends JFrame {
    private final JTextField savingsField = new JTextField(10);
    private final JTextField contribField = new JTextField(10);
    private final JTextField incomeField = new JTextField(10);
    private final JTextField currentAgeField = new JTextField(4);
    private final JTextField retireAgeField = new JTextField(4);
    private final JTextField deathAgeField = new JTextField(4);
    private final JTextField inflationPercentField = new JTextField(6);
    private final JTextField investPercentField = new JTextField(6);
    private final JTextArea retireText = new JTextArea(10, 30);
    private final RetireComponent retireCanvas = new RetireComponent();
    private final JButton computeButton = new JButton();
    private final JLabel languageLabel = new JLabel();
    private final JLabel savingsLabel = new JLabel();
    private final JLabel contribLabel = new JLabel();
    private final JLabel incomeLabel = new JLabel();
    private final JLabel currentAgeLabel = new JLabel();
    private final JLabel retireAgeLabel = new JLabel();
    private final JLabel deathAgeLabel = new JLabel();
    private final JLabel inflationPercentLabel = new JLabel();
    private final JLabel investPercentLabel = new JLabel();
    private final RetireInfo info = new RetireInfo();
    private final Locale[] locales = {Locale.US, Locale.CHINA, Locale.GERMANY};
    private Locale currentLocale;
    private final JComboBox<Locale> localeCombo = new LocaleCombo(locales);
    private ResourceBundle res;
    private ResourceBundle resStrings;
    private NumberFormat currencyFmt;
    private NumberFormat numberFmt;
    private NumberFormat percentFmt;

    public RetireFrame() {
        setLayout(new GridBagLayout());

        GBC gbc = new GBC().setAnchor(GBC.EAST);
        add(languageLabel, gbc);
        add(savingsLabel, gbc.setGridXY(0, 1));
        add(contribLabel, gbc.setGridXY(2, 1));
        add(incomeLabel, gbc.setGridXY(4, 1));
        add(currentAgeLabel, gbc.setGridXY(0, 2));
        add(retireAgeLabel, gbc.setGridXY(2, 2));
        add(deathAgeLabel, gbc.setGridXY(4, 2));
        add(inflationPercentLabel, gbc.setGridXY(0, 3));
        add(investPercentLabel, gbc.setGridXY(2, 3));
        add(localeCombo, gbc.resetGridBagConstraints().setGrid(1, 0, 3, 1));

        gbc = gbc.resetGridBagConstraints().setGridXY(1, 1).setWeight(100, 0).setFill(GBC.HORIZONTAL);
        add(savingsField, gbc);
        add(contribField, gbc.setGridXY(3, 1));
        add(incomeField, gbc.setGridXY(5, 1));
        add(currentAgeField, gbc.setGridXY(1, 2));
        add(retireAgeField, gbc.setGridXY(3, 2));
        add(deathAgeField, gbc.setGridXY(5, 2));
        add(inflationPercentField, gbc.setGridXY(1, 3));
        add(investPercentField, gbc.setGridXY(3, 3));

        gbc = gbc.resetGridBagConstraints().setFill(GBC.BOTH);
        add(retireCanvas, gbc.setGrid(0, 4, 4, 1).setWeight(100, 100));
        add(new JScrollPane(retireText), gbc.setGrid(4, 4, 2, 1).setWeight(0, 100));

        computeButton.setName("computeButton");
        computeButton.addActionListener(event -> {
            updateInfo();
            updateData();
            updateGraph();
        });

        add(computeButton, gbc.resetGridBagConstraints());

        retireText.setEditable(false);
        retireText.setFont(new Font("Monospaced", Font.PLAIN, 14));

        info.setSavings(0);
        info.setContrib(9000);
        info.setIncome(60000);
        info.setCurrentAge(35);
        info.setRetireAge(65);
        info.setDeathAge(85);
        info.setInvestPercent(0.1);
        info.setInflationPercent(0.05);

        int localeIndex = 0;
        for (int i = 0; i < locales.length; i++) {
            if (getLocale().equals(locales[i])) {
                localeIndex = i;
                break;
            }
        }

        setCurrentLocale(locales[localeIndex]);

        localeCombo.addActionListener(event -> {
            setCurrentLocale((Locale) ((JComboBox<Locale>) event.getSource()).getSelectedItem());
            validate();
        });

        pack();
    }

    public void setCurrentLocale(Locale locale) {
        currentLocale = locale;
        localeCombo.setLocale(currentLocale);
        localeCombo.setSelectedItem(currentLocale);

        res = ResourceBundle.getBundle("retire.RetireResources", currentLocale);
        resStrings = ResourceBundle.getBundle("retire.RetireStrings", currentLocale);
        currencyFmt = NumberFormat.getCurrencyInstance(currentLocale);
        numberFmt = NumberFormat.getNumberInstance(currentLocale);
        percentFmt = NumberFormat.getPercentInstance(currentLocale);

        updateDisplay();
        updateTextField();
        updateData();
        updateGraph();
    }

    public void updateDisplay() {
        languageLabel.setText(resStrings.getString("language"));
        savingsLabel.setText(resStrings.getString("savings"));
        contribLabel.setText(resStrings.getString("contrib"));
        incomeLabel.setText(resStrings.getString("income"));
        currentAgeLabel.setText(resStrings.getString("currentAge"));
        retireAgeLabel.setText(resStrings.getString("retireAge"));
        deathAgeLabel.setText(resStrings.getString("deathAge"));
        inflationPercentLabel.setText(resStrings.getString("inflationPercent"));
        investPercentLabel.setText(resStrings.getString("investPercent"));
        computeButton.setText(resStrings.getString("computeButton"));
    }

    public void updateTextField() {
        savingsField.setText(currencyFmt.format(info.getSavings()));
        contribField.setText(currencyFmt.format(info.getContrib()));
        incomeField.setText(currencyFmt.format(info.getIncome()));
        currentAgeField.setText(numberFmt.format(info.getCurrentAge()));
        retireAgeField.setText(numberFmt.format(info.getRetireAge()));
        deathAgeField.setText(numberFmt.format(info.getDeathAge()));
        investPercentField.setText(percentFmt.format(info.getInvestPercent()));
        inflationPercentField.setText(percentFmt.format(info.getInflationPercent()));
    }

    public void updateData() {
        retireText.setText("");

        int currentAge = info.getCurrentAge();
        int deathAge = info.getDeathAge();
        MessageFormat retireMsg = new MessageFormat("");
        retireMsg.setLocale(currentLocale);
        retireMsg.applyPattern(resStrings.getString("retire"));

        for (int i = currentAge; i <= deathAge; i++) {
            Object[] args = { i, info.getBalance(i) };
            String content = retireMsg.format(args);
            if (i < deathAge) content += "\n\n";
            retireText.append(content);
        }
    }

    public void updateGraph() {
        retireCanvas.setColorPre((Color) res.getObject("colorPre"));
        retireCanvas.setColorGain((Color) res.getObject("colorGain"));
        retireCanvas.setColorLoss((Color) res.getObject("colorLoss"));
        retireCanvas.setInfo(info);
        repaint();
    }

    public void updateInfo() {
        try {
            info.setSavings(currencyFmt.parse(savingsField.getText()).doubleValue());
            info.setContrib(currencyFmt.parse(contribField.getText()).doubleValue());
            info.setIncome(currencyFmt.parse(incomeField.getText()).doubleValue());
            info.setCurrentAge(numberFmt.parse(currentAgeField.getText()).intValue());
            info.setRetireAge(numberFmt.parse(retireAgeField.getText()).intValue());
            info.setDeathAge(numberFmt.parse(deathAgeField.getText()).intValue());
            info.setInvestPercent(percentFmt.parse(investPercentField.getText()).doubleValue());
            info.setInflationPercent(percentFmt.parse(inflationPercentField.getText()).doubleValue());
        } catch (ParseException ex) { ex.printStackTrace(); }
    }
}

// 计算退休收入数据所需的信息
class RetireInfo {
    private double savings; // 既存
    private double contrib; // 每年存金
    private double income;  // 退休收入
    private int currentAge; // 现龄
    private int retireAge;  // 退休年龄
    private int deathAge;   // 预期寿命
    private double inflationPercent;    // 通货膨涨
    private double investPercent;   // 投资报酬
    private int age;    // 年龄
    private double balance; // 余额

    /**
     * 获取给定年份的可用余额。
     *
     * @param year 计算余额的年份
     * @return 当年可用（或需要）的金额
     */
    public double getBalance(int year) {
        if (year < currentAge) return 0;
        if (year == currentAge) {
            age = year;
            balance = savings;
            return balance;
        }
        if (year == age) return balance;
        if (year != age + 1) getBalance(year - 1);
        age = year;
        if (age < retireAge) balance += contrib;
        else balance -= income;
        balance *= 1 + (investPercent - inflationPercent);
        return balance;
    }

    public double getSavings() { return savings; }

    public void setSavings(double newValue) {
        savings = newValue;
    }

    public double getContrib() { return contrib; }

    public void setContrib(double newValue) { contrib = newValue; }

    public double getIncome() { return income; }

    public void setIncome(double newValue) {
        income = newValue;
    }

    public int getCurrentAge() { return currentAge; }

    public void setCurrentAge(int newValue) { currentAge = newValue; }

    public int getRetireAge() {
        return retireAge;
    }

    public void setRetireAge(int newValue) {
        retireAge = newValue;
    }

    public int getDeathAge() {
        return deathAge;
    }

    public void setDeathAge(int newValue) { deathAge = newValue; }

    public double getInflationPercent() {
        return inflationPercent;
    }

    public void setInflationPercent(double newValue) {
        inflationPercent = newValue;
    }

    public double getInvestPercent() {
        return investPercent;
    }

    public void setInvestPercent(double newValue) {
        investPercent = newValue;
    }
}

class RetireComponent extends JComponent {
    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 200;
    private RetireInfo info = null;
    private Color colorPre;
    private Color colorGain;
    private Color colorLoss;

    public RetireComponent() { setSize(PANEL_WIDTH, PANEL_HEIGHT); }

    public void setInfo(RetireInfo newInfo) {
        info = newInfo;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        if (info == null) return;

        final int currentAge = info.getCurrentAge();
        final int deathAge = info.getDeathAge();

        BiFunction<Integer, Integer, Supplier<IntStream>> _createAgeRange = (current, death) -> () -> IntStream.rangeClosed(current, death);
        Supplier<IntStream> createAgeRange = _createAgeRange.apply(currentAge, deathAge);

        DoubleSummaryStatistics doubleStream = createAgeRange.get().mapToDouble(age -> info.getBalance(age)).summaryStatistics();
        double negativeBalance = doubleStream.getMin();
        double totalBalance = doubleStream.getMax();

        if (totalBalance == negativeBalance) return;

        final int barWidth = getWidth() / (deathAge - currentAge + 1);
        final double scale = getHeight() / (totalBalance - negativeBalance);
        final int originY = (int) (totalBalance * scale);

        createAgeRange.get().forEach(age -> {
            double currentBalance = info.getBalance(age);
            int x = (age - currentAge) * barWidth + 1;
            int y = (int) ((totalBalance - currentBalance) * scale);
            int height = originY - y;

            if (currentBalance < 0) {
                y = originY;
                height = (int) (-currentBalance * scale);
            }

            if (age < info.getRetireAge()) graphics2D.setPaint(colorPre);
            else if (currentBalance >= 0) graphics2D.setPaint(colorGain);
            else graphics2D.setPaint(colorLoss);

            RoundRectangle2D.Double cylindricalBar = new RoundRectangle2D.Double(x, y, barWidth - 2, height, 5, 5);
            graphics2D.fill(cylindricalBar);
            graphics2D.setPaint(Color.black);
            graphics2D.draw(cylindricalBar);
        });
    }

    public void setColorPre(Color color) {
        colorPre = color;
        repaint();
    }

    public void setColorGain(Color color) {
        colorGain = color;
        repaint();
    }

    public void setColorLoss(Color color) {
        colorLoss = color;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}
