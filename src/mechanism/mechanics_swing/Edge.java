package mechanics_swing;

import helpers.DoublePair;
import helpers.Utils;


public class Edge implements MechPart{
    myPoint beginning;
    myPoint end;

    boolean gotPosition;

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
            if (getEnd().isConstant()) {
                this.gotPosition = true;
            }
            else {
                setFinish(getEndX() + distance.first, getEndY() + distance.second);
                if (!getEnd().adjacent.isEmpty()) {
                    for (int i = 0; i <  contents.length; i++){
                        if (getEnd().adjacent.contains(i))
                            contents[i].DFS_Movement(getFinish(), distance, contents);
                    }
                    DoublePair returnMovement = DFS_Movement(getFinish(), distance, contents);
                    setFinish(getEndX() + returnMovement.first,
                            getEndY() + returnMovement.second);

                }
            }
        }
        else if (source.equals(getFinish())){

        }
        return new DoublePair(0,0);
    }


}