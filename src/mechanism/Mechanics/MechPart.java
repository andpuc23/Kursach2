package Mechanics;

import Helpers.DoublePair;

public interface MechPart {
    double getStartX();
    double getStartY();
    double getEndX();
    double getEndY();

    MyPoint getBeginning();
    MyPoint getEnd();

    DoublePair getStart();
    DoublePair getFinish();
    DoublePair getCenter();

    void setStart(double x, double y);
    void setFinish(double x, double y);
    void setFinish(int k);

    void move(double dx, double dy);
    void move(double x1, double y1, double x2, double y2);

    void rotate(double angle);
    double Length();

    double Angle();

    void setLength(double length);

    void recalculatePositions();

    int getPointWithDistance(MechPart source, boolean finish, double dist);
}
