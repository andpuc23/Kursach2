package Optimization;


import Helpers.WriterReader;

import java.io.*;
import java.nio.file.Paths;

public class OutsourceAttractor {
    public OutsourceAttractor(){}

    public void runPython(){
        WriterReader wr = new WriterReader("C:\\Users\\user\\Desktop\\Курсовая 2\\TestProject\\data\\mechs.csv");
        wr.readSingle();
        try {
            System.out.println("python LinReg.py " + wr.features.length);
            String num = Integer.toString(wr.features.length);
            new ProcessBuilder("python",  "LinReg.py", num).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
    }
}