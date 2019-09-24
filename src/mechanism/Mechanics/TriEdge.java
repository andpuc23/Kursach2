package Mechanics;

import Helpers.DoublePair;
import Helpers.Utils;

public class TriEdge implements MechPart {
    private Edge first, second, main;
    private MyPoint beginning, center, finish;
    DoublePair[] possibleFinishPositions = new DoublePair[720];
    DoublePair[] possibleMiddlePositions = new DoublePair[720];

    TriEdge(MyPoint start, MyPoint mid, MyPoint end){
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

    public MyPoint getBeginning(){
        return beginning;
    }
    private MyPoint getMiddle() {return center; }
    public MyPoint getEnd(){
        return finish;
    }

    private void setCenter(double x, double y){
        center.setCenterX(x);
        center.setCenterY(y);
        recalculatePositions();
    }

    public double getCenterX() { return center.getCenterX(); }
    public double getCenterY() { return center.getCenterY(); }

    public DoublePair getCenter(){
        return new DoublePair(getCenterX() , getCenterY());
    }

    public void setStart(double x, double y){
        beginning.move(x, y);
        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndY()/2);
        recalculatePositions();
    }

    public void setFinish(double x, double y){
        finish.move(x, y);
        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndY()/2);
    }

    @Override
    public void setFinish(int k) {
        setFinish(possibleFinishPositions[k].first, possibleFinishPositions[k].second);
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

    private void inline(){
        setCenter(getStartX()/2 + getEndX()/2, getStartY()/2 + getEndX()/2);
    }

    void inlineFinish(){
        setFinish(getCenterX()*2 - getStartX(), getCenterY()*2 - getStartY());
    }

    @Override
    public double Angle(){
        return Math.atan2(getEndY()-getStartY(), getEndX()-getStartX());
    }

    @Override
    public double Length() {
        return Math.sqrt(
                (getEndX()-getStartX())*(getEndX()-getStartX())+
                (getEndY()-getStartY())*(getEndY()-getStartY())
                );
    }

    @Override
    public void setLength(double length) {
        double rads = Angle();
        if (getEnd().isConstant()){
            setStart(getEndX() - Math.cos(rads)*length,
                    getEndY() - Math.sin(rads)*length);
            inline();
        }
        else if (getMiddle().isConstant()) {
            setFinish(getCenterX() + Math.cos(rads) * length / 2,
                    getCenterY() + Math.sin(rads) * length / 2);
            setStart(getCenterX() - Math.cos(rads) * length / 2,
                    getCenterY() - Math.sin(rads) * length / 2);
        }
        else {
            setCenter(getStartX() + Math.cos(rads)*length/2,
                    getStartY() + Math.sin(rads)*length/2);
            setFinish(getStartX() + Math.cos(rads)*length,
                    getStartY() + Math.sin(rads)*length);
        }

    }

    @Override
    public void recalculatePositions() {
        double radius = Length();
        DoublePair startPosition = getStart();
        DoublePair finishPosition = new DoublePair(radius, 0); //finish is lower
        finishPosition = Utils.Pif2Pol(finishPosition);
        for (int i = 0; i < 720; i++){
            finishPosition.second += 0.5;
            possibleFinishPositions[i] = startPosition.add(Utils.Pol2Pif(finishPosition));
            possibleMiddlePositions[i] =
                    new DoublePair(startPosition.first/2 + possibleFinishPositions[i].first/2,
                    startPosition.second/2 + possibleFinishPositions[i].second/2);
        }
    }

    @Override
    public int getPointWithDistance(MechPart source, boolean finish, double distance) {
        int index = 0;
        if (!finish)
            distance /= 2;

        double minDist = Math.abs(Utils.distance(possibleFinishPositions[0], source.getFinish()) - distance);

        for (int i = 1; i < 360; i++)
            if (minDist > Math.abs(Utils.distance(possibleFinishPositions[i], source.getFinish()) - distance)) {
                index = i;
                minDist = Math.abs(Utils.distance(possibleFinishPositions[i], source.getFinish()) - distance);
            }
        return index;
    }
}
