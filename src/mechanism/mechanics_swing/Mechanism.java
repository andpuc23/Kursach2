package mechanics_swing;

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


    public void rotate(int i, double angle){

        contents[i].rotate(angle);

        /*for (int j = 0; j < contents.length; j++) {
            if (contents[i].getEnd().adjacent.contains(j)) {
                if (!contents[j].getMoved()) {
                    contents[j].DFS_Movement(contents[i].getFinish(), pif, contents);
                }
            }
        }*/
    }
}