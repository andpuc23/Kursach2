package helpers;

import javafx.scene.Node;
import mechanics_swing.Edge;
import mechanics_swing.MechPart;
import mechanics_swing.myPoint;

public class Utils {
    public static double distance(DoublePair a, DoublePair b) {
        return Math.sqrt(
                (a.first-b.first)
                        *(a.first-b.first) +
                (a.second-b.second)
                        *(a.second-b.second));
    }

    public static double distance(myPoint a, DoublePair b){
        return Math.sqrt(
                (a.getCenterX()-b.first)*
                        (a.getCenterX()-b.first) +
                (a.getCenterY()-b.second)*
                        (a.getCenterY()-b.second));
    }

    public static double distance(myPoint a, myPoint b){
        return Math.sqrt(
                (a.getCenterX()-b.getCenterX()) *
                        (a.getCenterX()-b.getCenterX()) +
                (a.getCenterY()-b.getCenterY()) *
                        (a.getCenterY()-b.getCenterY()));
    }


    public static DoublePair Pif2Pol(DoublePair pair){
        double x = pair.first;
        double y = pair.second;

        double ro = Math.sqrt(x*x + y*y);
        double fi = Math.atan2(y, x);

        return new DoublePair(ro, fi);
    }

    public static DoublePair Pol2Pif(DoublePair pair){
        double ro = pair.first;
        double fi = pair.second;

        double x = ro * Math.cos(fi);
        double y = ro * Math.sin(fi);

        return new DoublePair(x, y);
    }

    public static double asDegrees(double rad){
        return rad*180/Math.PI;
    }

    public static double asRadians(double degrees){
        return degrees*Math.PI/180;
    }

    /**
     * @param pointToMove point to calculate distance from
     * @param edge which is going to be moved
     * @return coords of the closest possible position of edge
     */
    public static DoublePair getBestPosition(DoublePair pointToMove, Edge edge){
        DoublePair[] positions = new DoublePair[31];

        edge.rotate(-15d);
        for (int i = 0; i < 31; i++){
            positions[i] = edge.getFinish();
            edge.rotate(1);
        }
        edge.rotate(-15d); // return to starting position

        double min = Utils.distance(pointToMove, positions[0]);

        int index = 0;
        DoublePair pos;
        for (int i = -15; i <= 15; i++){
            pos = Utils.Pif2Pol(pointToMove);
            pos.second += i;
            positions[i+15] = Utils.Pol2Pif(pos);
            if (min > Utils.distance(pointToMove, positions[i+15])) {
                min = Utils.distance(pointToMove, positions[i+15]);
                index = i;
            }
        }
        return positions[index+15];
    }

    public static Node[] toNode(MechPart[] ar){
        Node[] res = new Node[ar.length];
        for (int i = 0; i < ar.length; i++)
            res[i] = (Node)ar[i];

        return res;
    }
}

