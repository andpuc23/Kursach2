package Optimization;

import Helpers.WriterReader;

import java.io.*;
import java.nio.file.Paths;

public class OutsourceAttractor {
    public OutsourceAttractor(){}

    public void runPython(){
        String lastPath = Paths.get(".").toAbsolutePath().normalize().toString();
        lastPath = lastPath.substring(lastPath.length()-11);
        String path = "";
        if (lastPath.equals("Project_jar"))
            path += "..\\..\\..\\";

        WriterReader wr = new WriterReader(path + "data\\mechs.csv");
        wr.readSingle();
        try {
//            System.out.println("python LinReg.py " + wr.features.length);
            String num = Integer.toString(wr.features.length);
            new ProcessBuilder("python", path + "LinReg.py", num).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        OutsourceAttractor os = new OutsourceAttractor();
        os.runPython();
    }
}