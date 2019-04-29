package mechanics_fx;

import helpers.DoublePair;
import javafx.scene.shape.Line;

public class TriEdge extends Line implements MechPart {
    private Edge first, second;
    myPoint beginning;
    myPoint end;
    myPoint center;
    boolean moved;

    public TriEdge(myPoint start, myPoint mid, myPoint end){
        first = new Edge(start, mid);
        second = new Edge(mid, end);
        center = mid;
    }

    public myPoint getBeginning(){
        return beginning;
    }

    public myPoint getEnd(){
        return end;
    }

    public void setCenter(double x, double y){
        center.setCenterX(x);
        center.setCenterY(y);
    }

    double getCenterX(){ return center.getCenterX(); }

    double getCenterY() {return center.getCenterY(); }

    public DoublePair getCenter(){
        return new DoublePair(getCenterX() , getCenterY());
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

    public void move(double x1, double y1, double x2, double y2){
        setStart(x1, y1);
        setFinish(x2, y2);
        setCenter(x1/2+x2/2, y1/2+y2/2);

        moved = true;
    }

    public void move(double dx, double dy){
        setCenter(getCenterX() + dx, getCenterY() + dy);
        setStart(getStartX() + dx, getStartY() + dy);
        setFinish(getEndX() + dx, getEndY() + dy);

        moved = true;
    }

    public boolean getMoved(){return moved;}

    public void setMoved(boolean p){moved = p;}


   /* public void DFS_Movement(DoublePair source, DoublePair distance, MechPart[] contents){
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
        System.out.println("moved edge " + this.toString() + "\nSource: " + source.toString()+ "\n\n");
        this.moved = true;
        for (int i = 0; i < contents.length; i++){
            if (getBeginning().adjacent.contains(i) && !contents[i].getMoved())
                DFS_Movement(getStart(), new DoublePair(0.0,0.0), contents );
            if (getEnd().adjacent.contains(i) && !contents[i].getMoved())
                DFS_Movement(getFinish(), new DoublePair(0.0,0.0), contents );
        }
    }*/

}
