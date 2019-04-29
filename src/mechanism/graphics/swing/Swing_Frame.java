package graphics.swing;

import mechanics_swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Swing_Frame extends JFrame {
    private DrawPanel canvas;
    private GuiPanel gui;
    private Mechanism mech;
    private LinkedList<Point2D> positions;

    private Mechanism createMech(){
        myPoint A = new myPoint(300,400, true);
        myPoint B = new myPoint(375,300);
        myPoint C = new myPoint(500, 400, true);
        myPoint D = new myPoint(450, 400);
        myPoint E = new myPoint(300, 200);


        myPoint[] points = new myPoint[]{A, B, C, D, E};

        LinkedList<int[]> edges = new LinkedList<>();
        edges.add(new int[]{2, 3}); //rotater
        edges.add(new int[]{3, 1, 4}); //longest
        edges.add(new int[]{0, 1}); //short left

        return new Mechanism(edges, points);
    }

    public static void main(String[] args){
        new Swing_Frame();
    }

    public Swing_Frame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("512px-Steam_icon_logo.svg.png").getImage());

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)(dimension.getWidth() / 2);
        int y = (int)(dimension.getHeight() / 2);
        setLocation(x/2, y/2);
        setSize(x,y);

        mech = createMech();
        positions = new LinkedList<>();


        canvas = new DrawPanel(mech, positions);
        gui = new GuiPanel(mech, positions, canvas);

        gui.setBackground(Color.gray);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(gui, BorderLayout.NORTH);

        setVisible(true);
    }
}