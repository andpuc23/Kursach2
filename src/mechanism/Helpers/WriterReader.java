package Helpers;

import java.io.*;
import java.util.ArrayList;

public class WriterReader {
    private String path;
    public double[][] features;

    public WriterReader(String path) {
        this.path = path;
    }

    public void readSingle(){
        String row;
        ArrayList<double[]> feats = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                double[] pData = new double[data.length];
                for (int i = 0; i < data.length; i++)
                    pData[i] = Double.parseDouble(data[i]);
                feats.add(pData);
            }

            features = new double[feats.size()][];
            for (int i = 0; i < feats.size(); i++)
                features[i] = new double[feats.get(i).length];


                for (int i = 0; i < feats.size(); i++)
                    System.arraycopy(feats.get(i), 0,
                            features[i], 0, feats.get(i).length);


            csvReader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Some files are not found");
            System.out.println("Ensure that you've run the file, and it contains phrase like\n" +
                    "python LinReg.py [some number]");
            return;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }



    public void write(double[] numbers, double dev){
        try{
            FileWriter fw = new FileWriter(path, true);
            for (double num : numbers)
                fw.write((int)num + ", ");
            fw.write((int)dev + "\n");
            fw.close();

        } catch (Exception e) {e.printStackTrace();}
    }

    public void write(double[] numbers){
        try{
            FileWriter fw = new FileWriter(path, true);
            for (double num : numbers)
                fw.write((num == numbers[0]?"":", ") + (int)num );
            fw.write("\n");
            fw.close();

        } catch (Exception e) {e.printStackTrace();}
    }
}
