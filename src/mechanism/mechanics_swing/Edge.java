package mechanics_swing;

import helpers.DoublePair;
import helpers.Utils;


public class Edge implements MechPart{
    myPoint beginning;
    myPoint end;
    double length;
    boolean gotPosition;

    private double Length(){
        return Math.sqrt((getEndX()-getStartX())*(getEndX()-getStartX()) + (getEndY()-getStartY())*(getEndY()-getStartY()));
    }

    public String toString(){
        return "(" + getStartX() + "; " + getStartY() + ") to (" + getEndX() + "; " + getEndY() + "); length:" + Length();
    }

    public myPoint getBeginning() { return beginning; }

    public myPoint getEnd() { return end; }

    public double getStartX() { return beginning.getCenterX(); }
    public void setStartX(double value) { beginning.setCenterX(value); }

    public double getStartY() { return beginning.getCenterY(); }
    public void setStartY(double value) { beginning.setCenterY(value); }

    public double getEndX() { return end.getCenterX(); }
    public void setEndX(double value) { end.setCenterX(value); }

    public double getEndY() { return end.getCenterY(); }
    public void setEndY(double value) { end.setCenterY(value); }

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

    public boolean getGotPosition() { return gotPosition; }

    public void setGotPosition(boolean p) { gotPosition = p;}

    Edge(myPoint start, myPoint finish){

        beginning = start;
        end = finish;
    }

    public void move(double x1, double y1, double x2, double y2){
        setStart(x1, y1);
        setFinish(x2, y2);
    }

    public void move(double dx, double dy){
        setStart(getStartX() + dx, getStartY()+dy);
        setFinish(getEndX() + dx, getEndY() + dy);
    }

    public void move (DoublePair d){
        setStart(getStartX() + d.first, getStartY() + d.second);
        setFinish(getEndX() + d.first, getEndY() + d.second);
    }

    public void rotate(double angle){
        DoublePair pif = new DoublePair(getEndX() - getStartX(),
                getEndY() - getStartY());
        DoublePair pol = Utils.Pif2Pol(pif);

        pol.second += Utils.asRadians(angle);

        DoublePair newPif = Utils.Pol2Pif(pol);

        setFinish(getStartX() + newPif.first,
                getStartY() + newPif.second);

        setGotPosition(true);
    }

    //TODO implement
    @Override
    public DoublePair DFS_Movement(DoublePair source, DoublePair distance, MechPart[] contents) {
        int movementType = 0;
        if (source.equals(getStart())){
            if (this.getEnd().isConstant())
                movementType = 1;
            else
                movementType = 2;
        }
        else if (source.equals(getFinish())){
            if (this.getBeginning().isConstant())
                movementType = 3;
            else
                movementType = 4;
        }

        switch (movementType){
            case 1: //source is start, end is fixed
                DoublePair bestPos = Utils.getBestPosition(source, this);
                DoublePair movement = bestPos.substract(getStart());
                setStart(bestPos.first, bestPos.second);

                return movement;
            case 2: //source is start, end is free
                setFinish(getEndX() + distance.first, getEndY() + distance.second);
                if (!getEnd().adjacent.isEmpty()){
                    DoublePair returnMovement = new DoublePair(0d,0d);
                    for (int i = 0; i < contents.length; i++){
                        if (getEnd().adjacent.contains(i))
                            returnMovement.add(contents[i].DFS_Movement(this.getFinish(), distance, contents));
                    }
                    setFinish(getEndX() + returnMovement.first, getEndY() + returnMovement.second);
                    return distance.add(returnMovement);
                }

                return distance;

            case 3: //source is finish, start is fixed
                bestPos = Utils.getBestPosition(source, this);
                movement = bestPos.substract(getFinish());
                setFinish(bestPos.first, bestPos.second);

                return movement;
            case 4: //source is finish, start is free
                setStart(getStartX() + distance.first, getStartY() + distance.second);
                if (!getBeginning().adjacent.isEmpty()){
                    DoublePair returnMovement = new DoublePair(0d,0d);
                    for (int i = 0; i < contents.length; i++){
                        if (getBeginning().adjacent.contains(i))
                            returnMovement.add(contents[i].DFS_Movement(this.getStart(), distance, contents));
                    }
                    setStart(getStartX() + returnMovement.first, getStartY() + returnMovement.second);
                    return distance.add(returnMovement);
                }
                return distance;

            default:
                return new DoublePair(0,0);
        }
    }
}