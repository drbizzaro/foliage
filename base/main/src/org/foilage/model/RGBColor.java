package org.foilage.model;

import static org.foilage.utils.checkers.NullChecker.notNull;

public class RGBColor {

    private final int red;

    private final int green;

    private final int blue;


    public RGBColor(String rgbString) {

        String[] splitString = notNull(rgbString).split(",");

        this.red = Integer.parseInt(splitString[0]);
        this.green = Integer.parseInt(splitString[1]);
        this.blue = Integer.parseInt(splitString[2]);
    }

    public RGBColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(red);
        stringBuilder.append(",");
        stringBuilder.append(green);
        stringBuilder.append(",");
        stringBuilder.append(blue);

        return stringBuilder.toString();
    }
}
