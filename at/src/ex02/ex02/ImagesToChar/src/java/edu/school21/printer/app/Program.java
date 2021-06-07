package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.diogonunes.jcdp.color.api.Ansi;
import edu.school21.printer.logic.ASCII_convert;
import edu.school21.printer.logic.Arguments;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        String colors[] = new String[2];
        try {
            Arguments arguments = new Arguments();
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);
            arguments.run(args.length, colors);
        } catch (ParameterException ex) {
            System.out.println("Error arguments!");
            System.exit(-1);
        }
        try {
            if(getFileExtension("./src/resources/it.bmp").equals("bmp")) {
                File file = new File("./src/resources/it.bmp");
                BufferedImage image = ImageIO.read(file);
                if (image == null)
                    throw new IllegalArgumentException(file.getName() + " is not a valid image.");
                String ascii = new ASCII_convert().convert(image);
                String str = "";
                System.out.print(Ansi.PREFIX + Ansi.BColor.valueOf(colors[0]) + Ansi.POSTFIX);
                System.out.print(str + "  ");
                for(int i = 0; i != ascii.length(); i++)
                {
                    if(ascii.charAt(i) == '\n')
                        System.out.println(Ansi.PREFIX + Ansi.BColor.NONE + Ansi.POSTFIX);
                    if(ascii.charAt(i) == '.') {
                        str = Ansi.PREFIX + Ansi.BColor.valueOf(colors[0]) + Ansi.POSTFIX;
                    }
                    if(ascii.charAt(i) == '0') {
                        str = Ansi.PREFIX + Ansi.BColor.valueOf(colors[1]) + Ansi.POSTFIX;
                    }
                    System.out.print(str + "  ");
                }
                System.out.println(Ansi.PREFIX + Ansi.BColor.NONE + Ansi.POSTFIX);
            }
            else {
                System.out.println("Error file extension!");
            }
        } catch (Exception e) {
            System.out.println("Error read file!!!");
        }
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else
            return "";
    }
}
