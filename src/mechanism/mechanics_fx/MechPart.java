package mechanics_fx;

import helpers.DoublePair;

public interface MechPart {
    double getStartX();
    double getStartY();
    double getEndX();
    double getEndY();

    myPoint getBeginning();
    myPoint getEnd();

    DoublePair getStart();
    DoublePair getFinish();

    void setStart(double x, double y);
    void setFinish(double x, double y);

    boolean getMoved();
    void setMoved (boolean p);

    void move (double dx, double dy);
    void move (double x1, double y1, double x2, double y2);

   /* /**
     * iterates through mechanism as a graph via DFS
     * @param source of movement, graph node's parent
     * @param distance of parent's movement
     * @param contents all graph's nodes
     *
    void DFS_Movement(DoublePair source,DoublePair distance, MechPart[] contents);
    */
}
