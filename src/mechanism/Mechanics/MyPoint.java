package Mechanics;

public class MyPoint {
    private boolean constant;
    private double centerX;
    private double centerY;

    public double getCenterX() { return centerX; }
    void setCenterX(double value) { centerX = value; }

    public double getCenterY() { return centerY; }
    void setCenterY(double value) { centerY = value; }

    MyPoint(){}

    public MyPoint(double x, double y){
        setCenterX(x);
        setCenterY(y);
    }
    public MyPoint(double x, double y, boolean fixed){
        constant = fixed;
        setCenterX(x);
        setCenterY(y);
    }

    void move(double x, double y){
        setCenterX(x);
        setCenterY(y);
    }

    public boolean isConstant() { return constant; }
}