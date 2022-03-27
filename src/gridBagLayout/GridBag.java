package gridBagLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GridBag extends JFrame {
    public void init() {
        createLayout();
        setSize(800, 500);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        setFont(new Font("JetBrains Mono NL", Font.PLAIN, 12));
        setLayout(gridBagLayout);

        Function<GridBagLayout, BiFunction<String, GridBagConstraints, ?>> _makeButton = layout -> (buttonName, constraints) -> {
            JButton button = new JButton(buttonName);
            gridBagLayout.setConstraints(button, gridBagConstraints);
            add(button);
            return null;
        };

        BiFunction<String, GridBagConstraints, ?> makeButton = _makeButton.apply(gridBagLayout);

        gridBagConstraints.fill = GridBagConstraints.BOTH;  // 填充至整个视图窗口，无论是横向还是纵向
        gridBagConstraints.weightx = 1.0;   // 不设置的话，大窗口或改变窗口尺寸时，所有组件将会聚集到中心。也就是说不会伸展！
        gridBagConstraints.weighty = 1.0;
        for (int i = 1; i <= 3; i++) makeButton.apply("button" + i, gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;    // 占满剩下的行或列
        makeButton.apply("button4", gridBagConstraints);
        makeButton.apply("button5", gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.RELATIVE; // 相对于上一个组件，通常会相对于上一个组件的右下角或下方进行摆放
        makeButton.apply("button6", gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        makeButton.apply("button7", gridBagConstraints);

        gridBagConstraints.gridwidth = 1;   // 横跨多少列
        gridBagConstraints.gridheight = 2;  // 横跨多少行
        makeButton.apply("button8", gridBagConstraints);

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = 1;
        makeButton.apply("button9", gridBagConstraints);
        makeButton.apply("button10", gridBagConstraints);

    }
}