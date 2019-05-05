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
        if (source.equals(getStart())){
            if (getEnd().isConstant()){
                DoublePair bestPosition = Utils.getBestPosition(source, this);
                setStart(bestPosition.first, bestPosition.second);
                this.gotPosition = true;

                System.out.println(this.toString());
                return new DoublePair(bestPosition.first - source.first, bestPosition.second - source.second);
            }
            else {
                setFinish(getEndX() + distance.first, getEndY() + distance.second);
                if (!getEnd().adjacent.isEmpty()) {
                    DoublePair returnMovement; // = new DoublePair(0d,0d);
                    for (int i = 0; i <  contents.length; i++){
                        if (getEnd().adjacent.contains(i)) {
                            returnMovement = contents[i].DFS_Movement(getFinish(), distance, contents);
                            returnMovement.reverse();
                            setFinish(getEndX() + returnMovement.first,
                                    getEndY() + returnMovement.second);
                        }
                    }
                    System.out.println(this.toString());
                    return new DoublePair(getStartX() - source.first,
                            getStartY() - source.second);
                }
            }
        }
        else { //source.equals(getFinish())
            if (getBeginning().isConstant()) {
                //calculating the distance of movement
                DoublePair bestPosition = Utils.getBestPosition(source, this);
                //moving point there
                setFinish(bestPosition.first, bestPosition.second);
                //so we got the point's optimal position, and the edge's position,
                //cuz other point is fixed
                this.gotPosition = true;
                System.out.println(this.toString());
                return new DoublePair(bestPosition.first - source.first, bestPosition.second - source.second);
            }
            else {
                //firstly, we move the edge cuz it's not fixed
                setStart(getStartX() + distance.first, getStartY() + distance.second);
                //then we try to move all the adjacent edges
                if (!getBeginning().adjacent.isEmpty()) {
                    DoublePair returnMovement;// = new DoublePair(0d,0d);
                    for (int i = 0; i <  contents.length; i++){
                        if (getBeginning().adjacent.contains(i)) {
                            returnMovement = contents[i].DFS_Movement(getStart(), distance, contents);
                            returnMovement.reverse();
                            setStart(getStartX() + returnMovement.first,
                                    getStartY() + returnMovement.second);
                        }
                    }
                    System.out.println(this.toString());
                    return new DoublePair(getStartX() - source.first,
                            getStartY() - source.second);
                }
            }
        }
        return new DoublePair(0,0);
    }


}