package graphics.swing;

import mechanics_swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class DrawPanel extends JPanel {
    boolean drawPath;
    Mechanism mech;
    LinkedList<Point2D> positions;
    public DrawPanel(Mechanism m, LinkedList<Point2D> poss)
    {
        mech = m;
        setBackground(Color.lightGray);
        positions = poss;
    }

    @Override
    synchronized public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        if (drawPath)
            drawPath(g2, positions);

        drawMech(g2, mech);
//        drawPath(g2, posits);
        drawNet(g2);
    }

    void drawMech(Graphics2D g2, Mechanism m){
        for (myPoint p : m.joints) {
            Ellipse2D point = new Ellipse2D.Double(p.getCenterX() - 5,
                    p.getCenterY() - 5, 10, 10);

            if (p.isConstant())
                g2.setColor(Color.RED);
            else
                g2.setColor(Color.BLUE);

            g2.draw(point);
            g2.fill(point);
        }

        for (MechPart edge : m.contents) {
            if (edge instanceof Edge){
                Line2D line = new Line2D.Double(edge.getStartX(), edge.getStartY(),
                        edge.getEndX(), edge.getEndY());
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));

                g2.draw(line);
            }

            else if (edge instanceof TriEdge){
                Line2D line1 = new Line2D.Double(edge.getStartX(), edge.getStartY(),
                        ((TriEdge)edge).getCenterX(), ((TriEdge)edge).getCenterY());
                Line2D line2 = new Line2D.Double(((TriEdge)edge).getCenterX(),
                        ((TriEdge)edge).getCenterY(), edge.getEndX(), edge.getEndY());
                Line2D line3 = new Line2D.Double(edge.getStartX(), edge.getStartY(),
                        edge.getEndX(), edge.getEndY());

                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));

                g2.draw(line1);
                g2.draw(line2);
                g2.draw(line3);
            }
        }
    }

    synchronized void drawPath(Graphics2D g2, LinkedList<Point2D> positions) {
        g2.setColor(Color.orange);
        for (Point2D p : positions){
            g2.draw(new Ellipse2D.Double(p.getX() - 2.5, p.getY() - 2.5, 5,5));
            g2.fill(new Ellipse2D.Double(p.getX() - 2.5, p.getY() - 2.5, 5,5));
        }
    }

    synchronized void drawNet(Graphics2D g2){
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(0.02f));
        for (int i = 25; i < 2000; i += 25){
            g2.draw(new Line2D.Double(0,i,2000,i));
        }
        for (int i = 25; i < 2000; i += 25){
            g2.draw(new Line2D.Double(i,0,i,2000));
        }
    }
}
