package graphics.swing;

import mechanics_swing.Mechanism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class GuiPanel extends JPanel {

    private Mechanism m;
    private LinkedList<Point2D> positions;
    private DrawPanel drawPanel;

    boolean drawPath = false;
    boolean rotateOn = false;

    public GuiPanel(Mechanism m, LinkedList<Point2D> pos, DrawPanel dp){
        this.m = m;
        positions = pos;
        drawPanel = dp;
        Font font = new Font("Default", Font.PLAIN, 20);

        JLabel sliderLabel = new JLabel("angle of single rotation: -10");
        sliderLabel.setFont(font);
//        sliderLabel.setForeground(Color.blue); - text color
        add(sliderLabel);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, -10,10,2);
        add(slider);

        JLabel rightLabel = new JLabel("10");
        rightLabel.setFont(font);
        add(rightLabel);

        JButton rotBut = new JButton("rotate");
        rotBut.setPreferredSize(new Dimension(100, 50));
        rotBut.setFont(font);
        add(rotBut);
        rotBut.addActionListener((ActionEvent event) -> {
            m.rotate(0, slider.getValue());

            addPoint(1, positions);
            dp.repaint();
        });

        JButton showPath = new JButton("show point's path");
        showPath.setPreferredSize(new Dimension(200, 50));
        showPath.setFont(font);
        add(showPath);
        showPath.addActionListener(event -> {
            dp.drawPath = true;
            dp.repaint();
        });

        JButton deletePath = new JButton("hide path");
        deletePath.setPreferredSize(new Dimension(200, 50));
        deletePath.setFont(font);
        add(deletePath);
        deletePath.addActionListener(event -> {
            dp.drawPath = false;
            dp.repaint();
        });

        Thread rotater = new Thread(() -> {
            while (true){
                if (rotateOn) {
                    m.rotate(0, slider.getValue());

                    addPoint(1, positions);
                    dp.repaint();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    notify();
                    e.printStackTrace();
                }
            }
        });
        rotater.setDaemon(true);

        JButton startRotating = new JButton("start rotation");
        startRotating.setPreferredSize(new Dimension(200, 50));
        startRotating.setFont(font);
        add(startRotating);
        startRotating.addActionListener(event ->{
            rotateOn = true;
            if (!rotater.isAlive())
                rotater.start();
        });

        JButton stopRotating = new JButton("stop rotation");
        stopRotating.setPreferredSize(new Dimension(200, 50));
        stopRotating.setFont(font);
        add(stopRotating);
        stopRotating.addActionListener(event -> rotateOn = false);
    }

    synchronized void addPoint(int pointNum, LinkedList<Point2D> positions){
        int index = positions.size()%360;
            positions.add(index, new Point2D.Double(
                m.joints[pointNum].getCenterX(), m.joints[pointNum].getCenterY()));
    }
}
