package runtimeAnnotations;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class ButtonFrame extends JFrame {
    private final JPanel panel = new JPanel();

    public ButtonFrame() {
        Function<String, JButton> createButton = JButton::new;

        panel.add(createButton.apply("yellow"));
        panel.add(createButton.apply("blue"));
        panel.add(createButton.apply("red"));

        add(panel);
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListenerInstaller.processAnnotations(this, panel.getComponents());
    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() { panel.setBackground(Color.YELLOW); }

    @ActionListenerFor(source = "blueButton")
    public void blueBackground() { panel.setBackground(Color.BLUE); }

    @ActionListenerFor(source = "redButton")
    public void redBackground() { panel.setBackground(Color.RED); }

    @ActionListenerFor(source = "blackButton")
    public void blackBackground() {}
}