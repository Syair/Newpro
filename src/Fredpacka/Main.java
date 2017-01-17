package Fredpacka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        File file = new File("ccc.txt");
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("run dos run!");
            } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        System.out.println(args[0]);
    }
}
