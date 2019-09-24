package Mechanics;

import Helpers.DoublePair;
import Helpers.Utils;

public class Edge implements MechPart{
    private MyPoint beginning;
    private MyPoint end;
    private DoublePair[] possiblePositions = new DoublePair[360];
    private double length = 0d;

    public double Length(){
        length = Math.sqrt((getEndX()-getStartX())*(getEndX()-getStartX()) + (getEndY()-getStartY())*(getEndY()-getStartY()));
        return length;
    }

    @Override
    public double Angle(){
        return Math.atan2(getEndY()-getStartY(), getEndX()-getStartX());
    }

    public void setLength(double length){
        double rads = Angle();
        if (getEnd().isConstant()){
            setStartX(getEndX() - Math.cos(rads)*length);
            setStartY(getEndY() - Math.sin(rads)*length);
        }
        else
            setEndX(getStartX() + Math.cos(rads)*length);
        setEndY(getStartY() + Math.sin(rads)*length);
    }

    public String toString(){
        String res =  "(" + getStartX() + "; " + getStartY() + ") to (" + getEndX() + "; " + getEndY() + "); length:" + Length();
        return res;
    }

    public MyPoint getBeginning() { return beginning; }

    public MyPoint getEnd() { return end; }

    public double getStartX() { return beginning.getCenterX(); }
    private void setStartX(double value) { beginning.setCenterX(value); }

    public double getStartY() { return beginning.getCenterY(); }
    private void setStartY(double value) { beginning.setCenterY(value); }

    public double getEndX() { return end.getCenterX(); }
    private void setEndX(double value) { end.setCenterX(value); }

    public double getEndY() { return end.getCenterY(); }
    private void setEndY(double value) { end.setCenterY(value); }

    public void setStart(double x, double y){
        setStartX(x);
        setStartY(y);
        beginning.move(x, y);
        recalculatePositions();
    }

    public void setFinish(double x, double y){
        setEndX(x);
        setEndY(y);
        end.move(x, y);
    }

    public void setFinish(int k){
        setEndX(possiblePositions[k].first);
        setEndY(possiblePositions[k].second);
    }

    public DoublePair getStart(){
        return new DoublePair(getStartX(), getStartY());
    }

    public DoublePair getFinish() {
        return new DoublePair(getEndX(), getEndY());
    }

    //thumb
    public DoublePair getCenter(){return getFinish();}

    Edge(MyPoint start, MyPoint finish){
        beginning = start;
        end = finish;
        recalculatePositions();
    }

    //get all possible positions of the edge
    public void recalculatePositions(){
        double radius = Length();
        DoublePair startPosition = getStart();
        DoublePair finishPosition = new DoublePair(radius, Utils.asRadians(180)); //finish is lower

        for (int i = 0; i < 360; i++){
            finishPosition.second += Utils.asRadians(0.5d); //rotate by 1/2 degree
            possiblePositions[i] = startPosition.add(Utils.Pol2Pif(finishPosition));
        }
    }

    @Override
    public int getPointWithDistance(MechPart source, boolean finish, double distance) {
        int index = 0;

        if(!finish)
            distance /= 2;

        double minDist = Math.abs(Utils.distance(possiblePositions[0], source.getFinish()) - distance);
        for (int i = 1; i < 360; i++)
            if (minDist > Math.abs(Utils.distance(possiblePositions[i], source.getFinish()) - distance)){
                index = i;
                minDist = Math.abs(Utils.distance(possiblePositions[i], source.getFinish()) - distance);
            }
        return index;
    }


    public void move(double x1, double y1, double x2, double y2){
        setStart(x1, y1);
        setFinish(x2, y2);
    }

    public void move(double dx, double dy){
        setStart(getStartX() + dx, getStartY() + dy);
        setFinish(getEndX() + dx, getEndY() + dy);
    }

    public void rotate(double angle){
        double length = Length();
        DoublePair pif = new DoublePair(getEndX() - getStartX(),
                getEndY() - getStartY());
        DoublePair pol = Utils.Pif2Pol(pif);

        pol.second += Utils.asRadians(angle);

        DoublePair newPif = Utils.Pol2Pif(pol);

        setFinish(getStartX() + newPif.first,
                getStartY() + newPif.second);

        setLength(length);
    }
}