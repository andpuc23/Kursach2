package mechanics_fx;

import helpers.DoublePair;

public class Edge implements MechPart{
    myPoint beginning;
    myPoint end;


    public boolean moved;

    public myPoint getBeginning() { return beginning; }

    public myPoint getEnd() { return end; }

    public double getStartX() { return beginning.getCenterX(); }
    public void setStartX(double value) { beginning.setCenterX(value); }

    public double getStartY() { return beginning.getCenterY(); }
    public void setStartY(double value) { beginning.setCenterY(value); }

    public double getEndX() { return end.getCenterY(); }
    public void setEndX(double value) { end.setCenterX(value); }

    public double getEndY() { return end.getCenterY(); }
    public void setEndY(double value) { end.setCenterY(value); }

    public Edge(double x1, double y1, double x2, double y2){
        beginning = new myPoint(x1, y1);
        end = new myPoint (x2, y2);
        moved = false;
    }

    public Edge(myPoint start, myPoint finish){

        beginning = start;
        end = finish;
        moved = false;
    }

    public void move(double x1, double y1, double x2, double y2){
        setStart(x1, y1);
        setFinish(x2, y2);
        moved = true;
    }

    public void move(double dx, double dy){
        setStart(getStartX() + dx, getStartY()+dy);
        setFinish(getEndX() + dx, getEndY() + dy);
        moved = true;
    }

    public void setStart(double x, double y){
        setStartX(x);
        setStartY(y);
        beginning.move(x, y);
    }

    public void setFinish(double x, double y){
        setEndX(x);
        setEndY(y);
        end.move(x, y);
    }

    public DoublePair getStart(){
        return new DoublePair(getStartX(), getStartY());
    }

    public DoublePair getFinish() {
        return new DoublePair(getEndX(), getEndY());
    }

    public boolean getMoved(){return moved; }

    public void setMoved(boolean p){moved = p;}

}