package Helpers;

import Mechanics.MechPart;
import Mechanics.MyPoint;

public class Utils {
    public static double distance(DoublePair a, DoublePair b) {
        return Math.sqrt(
                (a.first-b.first)
                        *(a.first-b.first) +
                (a.second-b.second)
                        *(a.second-b.second));
    }

    private static double distance(MyPoint a, DoublePair b){
        return Math.sqrt(
                (a.getCenterX()-b.first)*
                        (a.getCenterX()-b.first) +
                (a.getCenterY()-b.second)*
                        (a.getCenterY()-b.second));
    }

    public static double distance(MyPoint a, MyPoint b){
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

    public static double distanceToLine(double X, double Y, double k, double b){
        return (X*k + b - Y)/Math.sqrt(k*k+1);
    }

    public static boolean distanceEquals (MyPoint point, DoublePair pair, double distance){
        return Math.abs(Utils.distance(point, pair) - distance) < 5;
    }


}

