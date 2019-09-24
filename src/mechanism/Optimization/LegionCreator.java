package Optimization;

import Helpers.WriterReader;
import Mechanics.Mechanism;

import java.awt.geom.Point2D;
import java.util.Random;

public class LegionCreator {
    private static Random ran = new Random();

    public LegionCreator(String path, int number){
        WriterReader wr = new WriterReader(path);
        double[] params = new double[4];
        for (int i = 0; i < number; i++){
            params[0] = 50 + ran.nextInt(100);
            params[1] = 50 + ran.nextInt(200);
            params[2] = 50 + ran.nextInt(200);
            params[3] = 50 + ran.nextInt(200);

            try {
                Mechanism mech = Mechanism.createMech(params);

                if (mech == null || !mech.checkFunctionality())
                    i--;
                else {

                    wr.write(params);
                    System.out.println(String.format("Written mech %d of %d", i + 1, number));

                    mech.initPositions();

                    WriterReader posWrite = new WriterReader(String.format("data\\positions%d.csv", i));
                    for (Point2D p : mech.positions)
                        posWrite.write(new double[]{p.getX()}, p.getY());

                }
            }
            catch (ArrayIndexOutOfBoundsException aiobe){
                aiobe.printStackTrace();
            }
        }
        System.out.println("Learning samples created at " + path);
    }
}
