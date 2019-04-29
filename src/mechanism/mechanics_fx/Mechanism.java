package mechanics_fx;

import helpers.DoublePair;
import helpers.Utils;

import java.util.LinkedList;

public class Mechanism {
    public MechPart[] contents;
    public myPoint[] joints;

    public Mechanism(LinkedList<int[]> edges, myPoint[] points) {
        joints = points;
        contents = new MechPart[edges.size()];
        for (int i = 0; i < edges.size(); i++){
            if(edges.get(i).length == 2) {
                contents[i] = new Edge(
                        points[edges.get(i)[0]],
                        points[edges.get(i)[1]]);
                points[edges.get(i)[0]].adjacent.add(edges.get(i)[1]);
                points[edges.get(i)[1]].adjacent.add(edges.get(i)[0]);
            }
            else {
                contents[i] = new TriEdge(
                        points[edges.get(i)[0]],
                        points[edges.get(i)[1]],
                        points[edges.get(i)[2]]);
                points[edges.get(i)[0]].adjacent.add(edges.get(i)[1]);
                points[edges.get(i)[0]].adjacent.add(edges.get(i)[2]);
                points[edges.get(i)[1]].adjacent.add(edges.get(i)[0]);
                points[edges.get(i)[1]].adjacent.add(edges.get(i)[2]);
                points[edges.get(i)[2]].adjacent.add(edges.get(i)[0]);
                points[edges.get(i)[2]].adjacent.add(edges.get(i)[1]);
            }

        }
    }

    void setNotMoved(){
        for (MechPart e : contents){
            e.setMoved(false);
        }
    }


    public void rotate(int i, double angle){

        DoublePair pif = new DoublePair(contents[i].getEndX() - contents[i].getStartX(),
                contents[i].getEndY()-contents[i].getStartY());
        DoublePair pol = Utils.Pif2Pol(pif);

        pol.second += Utils.asRadians(angle);

        DoublePair newPif = Utils.Pol2Pif(pol);

        contents[i].setFinish(contents[i].getStartX() + newPif.first,
                contents[i].getStartY() + newPif.second);

        contents[i].setMoved(true);

        /*for (int j = 0; j < contents.length; j++) {
            if (contents[i].getEnd().adjacent.contains(j)) {
                if (!contents[j].getMoved()) {
                    contents[j].DFS_Movement(contents[i].getFinish(), pif, contents);
                }
            }
        }*/
        setNotMoved();
    }
}