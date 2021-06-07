package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;

public class Arguments {
    @Parameter(names = {"--white", "-w"}, arity = 1)
    private String agr1;
    @Parameter(names = {"--black", "-b"}, arity = 1)
    private String agr2;

    public void run(int size, String[] colors) {
        if(size != 4 || agr1 == null || agr2 == null) {
            System.out.println("Error arguments!");
            System.exit(-1);
        }
        colors[0] = agr1;
        colors[1] = agr2;
    }
}
