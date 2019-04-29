package mechanics_swing;

import helpers.DoublePair;
import helpers.Utils;

public class TriEdge implements MechPart {
    private Edge first, second, main;
    myPoint beginning;
    myPoint finish;
    myPoint center;
    boolean gotPosition;

    public TriEdge(myPoint start, myPoint mid, myPoint end){
        beginning = start;
        center = mid;
        finish = end;
        first = new Edge(beginning, center);
        second = new Edge(center, finish);
        main = new Edge(beginning, finish);
    }

    @Override
    public double getStartX() {
        return beginning.getCenterX();
    }

    @Override
    public double getStartY() {
        return beginning.getCenterY();
    }

    @Override
    public double getEndX() {
        return finish.getCenterX();
    }

    @Override
    public double getEndY() {
        return finish.getCenterY();
    }

    public myPoint getBeginning(){
        return beginning;
    }

    public myPoint getEnd(){
        return finish;
    }

    public void setCenter(double x, double y){
        center.setCenterX(x);
        center.setCenterY(y);
    }

    public double getCenterX() { return center.getCenterX(); }
    public double getCenterY() { return center.getCenterY(); }

    public DoublePair getCenter(){
        return new DoublePair(getCenterX() , getCenterY());
    }

    public void setStart(double x, double y){
        beginning.move(x, y);
        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndY()/2);
    }

    public void setFinish(double x, double y){
        finish.move(x, y);
        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndY()/2);
    }

    @Override
    public boolean getGotPosition() {
        return gotPosition;
    }

    @Override
    public void setGotPosition(boolean p) { gotPosition = p; }

    public DoublePair getStart(){
        return new DoublePair(getStartX(), getStartY());
    }

    public DoublePair getFinish() {
        return new DoublePair(getEndX(), getEndY());
    }

    public void move(double x1, double y1, double x2, double y2){
        setStart(x1, y1);
        setFinish(x2, y2);
        setCenter(x1/2+x2/2, y1/2+y2/2);
    }

    @Override
    public void rotate(double angle) {
        DoublePair pif = new DoublePair(getEndX() - getStartX(),
                getEndY() - getStartY());
        DoublePair pol = Utils.Pif2Pol(pif);

        pol.second += Utils.asRadians(angle);

        DoublePair newPif = Utils.Pol2Pif(pol);

        setFinish(getStartX() + newPif.first,
                getStartY() + newPif.second);

        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndY()/2);
    }

    public void move(double dx, double dy){
        setCenter(getCenterX() + dx, getCenterY() + dy);
        setStart(getStartX() + dx, getStartY() + dy);
        setFinish(getEndX() + dx, getEndY() + dy);
    }


    public DoublePair DFS_Movement(DoublePair source, DoublePair distance, MechPart[] contents){
//        DoublePair[] positions = new DoublePair[31];
//        DoublePair newPoint, pointToMove;
//
//
//        if (this.getCenter().equals(source)){
//            move(distance.first, distance.second);
//        }
//
//        if (this.getStart().equals(source)){
//            pointToMove = getFinish();
//            newPoint = Utils.getDistance(pointToMove, positions);
//            setFinish(newPoint.first, newPoint.second);
//            moved = true;
//
//            for (int adj : end.adjacent){
//                if (!contents[adj].getMoved())
//                    contents[adj].DFS_Movement(getFinish(), contents);
//            }
//        }
//
//        else if (this.getFinish().equals(movedPoint)){
//            pointToMove = getStart();
//            newPoint = Utils.getDistance(pointToMove, positions);
//            setStart(newPoint.first, newPoint.second);
//            moved = true;
//
//            for (int adj : beginning.adjacent){
//                if (!contents[adj].getMoved())
//                    contents[adj].DFS_Movement(getFinish(), contents);
//            }
//        }
//
//        System.out.println("moved edge " + this.toString() + "\nSource: " + source.toString()+ "\n\n");
//        this.moved = true;
//        for (int i = 0; i < contents.length; i++){
//            if (getBeginning().adjacent.contains(i) && !contents[i].getMoved())
//                DFS_Movement(getStart(), new DoublePair(0.0,0.0), contents );
//            if (getEnd().adjacent.contains(i) && !contents[i].getMoved())
//                DFS_Movement(getFinish(), new DoublePair(0.0,0.0), contents );
//        }
        return new DoublePair(0,0);
    }

}
