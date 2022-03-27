package permissions;

import javax.swing.*;
import java.awt.*;

public class PermissionTestFrame extends JFrame {
    private final JTextField textField;
    private final WordCheckTextArea textArea;
    private static final int TEXT_ROWS = 20;
    private static final int TEXT_COLUMNS = 60;

    public PermissionTestFrame() {
        textField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(textField);
        JButton openButton = new JButton("Insert");
        panel.add(openButton);
        openButton.addActionListener(event -> insertWords(textField.getText()));

        add(panel, BorderLayout.NORTH);

        textArea = new WordCheckTextArea();
        textArea.setRows(TEXT_ROWS);
        textArea.setColumns(TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        pack();

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width - getWidth()) / 2, (dimension.height - getHeight()) / 2);
    }

    public void insertWords(String words) {
        try {
            textArea.append(words + "\n");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(this, "I am sorry, but I cannot do that.");
            e.printStackTrace();
        }
    }

    // 重写getPreferredSize方法后必须在构造函数中调用超类的pack方法，使得自动调整大小，不调用则不生效(即：定义了这个重写方法后，出现的窗口不会定义成预定的窗口大小)
    @Override
    public Dimension getPreferredSize() { return new Dimension(500, 300); }
}