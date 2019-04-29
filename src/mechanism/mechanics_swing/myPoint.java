package mechanics_swing;

import helpers.DoublePair;

import java.util.ArrayList;

public class myPoint {

    public ArrayList<Integer> adjacent = new ArrayList<>();
    private boolean constant;
    private double centerX;
    private double centerY;

    public double getCenterX() { return centerX; }
    public void setCenterX(double value) { centerX = value; }

    public double getCenterY() { return centerY; }
    public void setCenterY(double value) { centerY = value; }

    public myPoint(double x, double y){
        setCenterX(x);
        setCenterY(y);
    }
    public myPoint(double x, double y, boolean fixed){
        constant = fixed;
        setCenterX(x);
        setCenterY(y);
    }

    public void move(double x, double y){
        setCenterX(x);
        setCenterY(y);
    }

    public boolean equals(myPoint p){
        return (this.getCenterY() - p.getCenterY()) < 1d &&
                (this.getCenterX() - p.getCenterY()) < 1d;
    }

    public boolean equals (DoublePair p){
        return (getCenterX() - p.first) < 1d &&
                (getCenterY() - p.second) < 1d;
    }

    public void add(DoublePair d){
        setCenterX(getCenterX() + d.first);
        setCenterY(getCenterY() + d.second);
    }

    public boolean isConstant() { return constant; }
}