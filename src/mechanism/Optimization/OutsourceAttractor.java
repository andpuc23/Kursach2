package Optimization;

import Helpers.WriterReader;

import java.io.*;

public class OutsourceAttractor {
    public OutsourceAttractor(){}

    public void runPython(){
        WriterReader wr = new WriterReader("data\\mechs.csv");
        wr.readSingle();
        try {
            String num = Integer.toString(wr.features.length);

            FileWriter appender = new FileWriter("PythonRunner.bat", true);
            appender.write(" " + num);
            appender.close();

            System.out.println("run PythonRunner.bat, then type \"done\"");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inp = "";
            while (!inp.toLowerCase().equals("done"))
                inp = in.readLine();

            appender = new FileWriter("PythonRunner.bat");
            appender.write("python LinReg.py");
            appender.close();


        }catch (FileNotFoundException f) {
            System.out.println("Some files are not found");
            System.out.println("Ensure that you've run the file, and it contains phrase like\n" +
                    "python LinReg.py [some number]");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        OutsourceAttractor os = new OutsourceAttractor();
        os.runPython();
    }
}