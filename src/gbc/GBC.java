package gbc;

import java.awt.*;

public class GBC extends GridBagConstraints {
    public GBC() { this(0, 0); }

    public GBC(int gridX, int gridY) {
        this.gridx = gridX;
        this.gridy = gridY;
    }

    public GBC(int gridX, int gridY, int gridWidth, int gridHeight) {
        this.gridx = gridX;
        this.gridy = gridY;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
    }

    public GBC setGridXY(int newGridX, int newGridY) {
        gridx = newGridX;
        gridy = newGridY;
        return this;
    }

    public GBC setGrid(int newGridX, int newGridY, int newGridWidth, int newGridHeight) {
        gridx = newGridX;
        gridy = newGridY;
        gridwidth = newGridWidth;
        gridheight = newGridHeight;
        return this;
    }

    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public GBC setWeight(double weightX, double weightY) {
        this.weightx = weightX;
        this.weighty = weightY;
        return this;
    }

    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    public GBC setInsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GBC setIpad(int ipadX, int ipadY) {
        this.ipadx = ipadX;
        this.ipady = ipadY;
        return this;
    }

    public GBC resetGridBagConstraints() {
        this.gridx = GBC.RELATIVE;
        this.gridy = GBC.RELATIVE;
        this.gridwidth = 1;
        this.gridheight = 1;
        this.ipadx = 0;
        this.ipady = 0;
        this.weightx = 0;
        this.weighty = 0;
        this.insets = new Insets(0, 0, 0, 0);
        this.anchor = GBC.CENTER;
        this.fill = GBC.NONE;
        return this;
    }
}
