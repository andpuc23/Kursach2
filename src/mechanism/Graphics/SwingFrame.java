package Graphics;

import Mechanics.*;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.LinkedList;

public class SwingFrame extends JFrame {
    private DrawPanel canvas;
    private MechControls gui;
    private OptimisationControls opt;
    private Mechanism mech;

    private Mechanism createMech(){
        MyPoint A = new MyPoint(400,420, true);
        MyPoint B = new MyPoint(600,270);
        MyPoint C = new MyPoint(700, 420, true);
        MyPoint D = new MyPoint(800, 420);
        MyPoint E = new MyPoint(400, 120);


        MyPoint[] points = new MyPoint[]{A, B, C, D, E};

        LinkedList<int[]> edges = new LinkedList<>();
        edges.add(new int[]{2, 3}); //rotater
        edges.add(new int[]{3, 1, 4}); //longest

        edges.add(new int[]{0, 1}); //short left

        return new Mechanism(edges, points);
    }

    public static void main(String[] args){
        new SwingFrame();
    }

    private SwingFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        String lastPath = Paths.get(".").toAbsolutePath().normalize().toString();
        lastPath = lastPath.substring(lastPath.length()-11);
        String path = "";
        if (lastPath.equals("Project_jar"))
            path += "..\\..\\..\\";

        setIconImage(new ImageIcon(path + "icon.png").getImage());

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)(dimension.getWidth() / 3);
        int y = (int)(dimension.getHeight() / 3);
        setLocation(x/2, y/2);
        setSize(x*2,y*2);

        mech = createMech();

        canvas = new DrawPanel(mech);
        gui = new MechControls(mech, canvas);
        opt = new OptimisationControls(canvas);

        gui.setBackground(Color.gray);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        getContentPane().add(gui, BorderLayout.NORTH);
        getContentPane().add(opt, BorderLayout.SOUTH);

        setVisible(true);
    }
}