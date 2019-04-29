package mechanics_fx;

import helpers.DoublePair;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class myPoint extends Circle{

    public ArrayList<Integer> adjacent = new ArrayList<>();
    boolean constant;

    public myPoint(double x, double y){
        super(x, y, 5);
        setFill(Color.BLUE);
    }
    public myPoint(double x, double y, boolean fixed){
        super(x, y, 5);
        if(fixed) {
            setFill(Color.RED);
            constant = fixed;
        }
    }

    public void move(double x, double y){
        setCenterY(y);
        setCenterX(x);
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
}