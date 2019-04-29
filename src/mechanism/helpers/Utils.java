package helpers;

import javafx.scene.Node;
import mechanics_fx.MechPart;
import mechanics_fx.myPoint;

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
     *
     * @param pointToMove the point to find best position for
     * @return distance as DoublePair to the new position
     */
    public static DoublePair getDistance(DoublePair pointToMove){
        DoublePair[] positions = new DoublePair[31];
        DoublePair pos;
        int index = 0;

        double min = Utils.distance(pointToMove, positions[0]);

        for (int i = -15; i <= 15; i++){
            pos = Utils.Pif2Pol(pointToMove);
            pos.second += i;
            positions[i+15] = Utils.Pol2Pif(pos);
            if (min > Utils.distance(pointToMove, positions[i])) {
                min = Utils.distance(pointToMove, positions[i]);
                index = i;
            }
        }
        return positions[index];
    }

    public static Node[] toNode(MechPart[] ar){
        Node[] res = new Node[ar.length];
        for (int i = 0; i < ar.length; i++)
            res[i] = (Node)ar[i];

        return res;
    }
}

