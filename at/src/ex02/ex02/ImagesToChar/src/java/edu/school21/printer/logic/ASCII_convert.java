package edu.school21.printer.logic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCII_convert {
    private char black;
    private char white;
    public ASCII_convert() {
        this.black = '0';
        this.white = '.';
    }

    public String convert(final BufferedImage image) {
        StringBuilder sb = new StringBuilder((image.getWidth() + 1) * image.getHeight());
        for (int y = 0; y < image.getHeight(); y++) {
            if (sb.length() != 0) sb.append("\n");
            for (int x = 0; x < image.getWidth(); x++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                double gValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870 + (double) pixelColor.getGreen() * 0.1140;
                final char s = returnStrPos(gValue);
                sb.append(s);
            }
        }
        return sb.toString();
    }

    private char returnStrPos(double g)
    {
        final char str;

        if (g >= 230.0) {
            str = white;
        } else {
            str = black;
        }
        return str;

    }
}
