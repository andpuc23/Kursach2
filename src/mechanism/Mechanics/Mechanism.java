package Mechanics;

import Helpers.DoublePair;
import Helpers.Utils;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.LinkedList;

public class Mechanism {
    public MechPart[] contents;
    public MyPoint[] joints;
    public LinkedList<Point2D> positions = new LinkedList<>();

    public Mechanism(LinkedList<int[]> edges, MyPoint[] points) {
        joints = points;
        contents = new MechPart[edges.size()];
        for (int i = 0; i < edges.size(); i++){
            if(edges.get(i).length == 2)
                contents[i] = new Edge(
                        points[edges.get(i)[0]],
                        points[edges.get(i)[1]]);

            else
                contents[i] = new TriEdge(
                        points[edges.get(i)[0]],
                        points[edges.get(i)[1]],
                        points[edges.get(i)[2]]);
        }
    }

    public static Mechanism createMech(double[] distances) {
        MyPoint[] joints = new MyPoint[5];
        MyPoint A = new MyPoint();
        MyPoint B = new MyPoint();
        MyPoint C = new MyPoint();
        MyPoint D = new MyPoint();
        MyPoint E = new MyPoint();

        C = new MyPoint(700, 400, true);
        D = new MyPoint(700 + distances[0], 400);
        A = new MyPoint(700 - distances[3], 400, true);


        boolean ok = false;
        for (int i = 0; i < 1800; i++){
            if (ok) break;
            for (int j = 0; j < 700; j++) {
                DoublePair d = new DoublePair(i, j);
                if (Utils.distanceEquals(A, d, distances[2]) &&
                        Utils.distanceEquals(D, d, distances[1] / 2)) {
                    B = new MyPoint(d.first, d.second);
                    ok = true;
                    break;
                }
            }
        }
        if (!ok) {
            return null;
        }

        E = new MyPoint(B.getCenterX()*2 - D.getCenterX(),
                B.getCenterY()*2 - D.getCenterY());

        joints[0] = A;
        joints[1] = B;
        joints[2] = C;
        joints[3] = D;
        joints[4] = E;


        LinkedList<int[]> edges = new LinkedList<>();
        edges.add(new int[]{2, 3}); //rotater
        edges.add(new int[]{3, 1, 4}); //longest

        edges.add(new int[]{0, 1}); //short left

        return new Mechanism(edges, joints);
    }

    public void moveMech(double angle){
        double length1 = contents[1].Length();
        double length2 = contents[2].Length();
        contents[0].rotate(angle);
        int newPosition = contents[2].getPointWithDistance(contents[0], false, length1);

        contents[2].setFinish(newPosition);
        contents[2].setLength(length2);

        ((TriEdge)contents[1]).inlineFinish();
        contents[1].setLength(length1);
    }

    private double[] toArray(){
        double[] result = new double[4];
        result[0] = contents[0].Length();
        result[1] = contents[1].Length();
        result[2] = contents[2].Length();
        result[3] = Utils.distance(joints[0], joints[2]);
        return result;
    }

    public boolean checkFunctionality(){
        double[] arr = toArray();
        return (arr[3] < arr[1]/2 + arr[2] - arr[0]) && (arr[0] < arr[3]) && (arr[0]*2 < arr[1]);
    }

    public void initPositions(){
        for (int i = 0; i < 360; i++){
            this.moveMech(1);

            //addPoint
            int index = positions.size()%360;
            if (positions.size() >= 360)
                positions.set(index, new Point2D.Double(
                        this.joints[4].getCenterX(), this.joints[4].getCenterY()));
            else
                positions.add(index, new Point2D.Double(
                        this.joints[4].getCenterX(), this.joints[4].getCenterY()));
        }
    }
}