package edu.school21.printer.app;
import edu.school21.printer.logic.ASCII_convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Need 3 arguments: path to image, symbol for black, symbol for white");
            System.exit(-1);
        } else if (args[1].length() != 1 || args[2].length() != 1) {
            System.out.println("Need SYMBOL for the colour");
            System.exit(-1);
        }

        try {
            if (getFileExtension(args[0]).equals("bmp")) {
                File file = new File(args[0]);
                BufferedImage image = ImageIO.read(file);
                if (image == null)
                    throw new IllegalArgumentException(file.getName() + " is invalid image");
                String ascii = new ASCII_convert(args[2].toCharArray()[0], args[1].toCharArray()[0]).convert(image);
                System.out.println(ascii);
            } else {
                System.out.println("Error file extension");
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else
            return "";
    }
}
