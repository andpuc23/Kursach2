package Graphics;

import Mechanics.Mechanism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.LinkedList;

class MechControls extends JPanel {

    private Mechanism mech;
    private DrawPanel drawPanel;

    private boolean rotateOn = false;

    private JLabel sliderLabel;
    private JSlider slider;
    private JLabel rightLabel;
    private JButton rotBut;
    private JButton showPath;
    private JButton deletePath;
    private JButton startRotating;
    private JButton stopRotating;

    MechControls(Mechanism m, DrawPanel dp){
        mech = m;
        drawPanel = dp;
        Font font = new Font("Default", Font.PLAIN, 14);

        sliderLabel = new JLabel("angle of single rotation: -2");
        sliderLabel.setFont(font);
        add(sliderLabel);

        slider = new JSlider(JSlider.HORIZONTAL, -2,10,2);
        add(slider);

        rightLabel = new JLabel("10");
        rightLabel.setFont(font);
        add(rightLabel);

        rotBut = new JButton("rotate once");
        rotBut.setFont(font);
        add(rotBut);
        rotBut.addActionListener((ActionEvent event) -> {
            dp.mech.moveMech(slider.getValue());

            addPoint(4, dp.positions);
            dp.repaint();
        });

        showPath = new JButton("show point's path");
        showPath.setFont(font);
        add(showPath);
        showPath.addActionListener(event -> {
            dp.drawPath = true;
            dp.repaint();
        });

        deletePath = new JButton("hide path");
        deletePath.setFont(font);
        add(deletePath);
        deletePath.addActionListener(event -> {
            dp.drawPath = false;
            dp.repaint();
        });

        Thread rotater = new Thread(() -> {
            while (true){
                if (rotateOn) {
                    dp.mech.moveMech(slider.getValue());

                    addPoint(4, dp.positions);
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

        startRotating = new JButton("start rotation");
        startRotating.setFont(font);
        add(startRotating);
        startRotating.addActionListener(event ->{
            rotateOn = true;
            if (!rotater.isAlive())
                rotater.start();
        });

        stopRotating = new JButton("stop rotation");
        stopRotating.setFont(font);
        add(stopRotating);
        stopRotating.addActionListener(event -> rotateOn = false);
    }

    synchronized void addPoint(int pointNum, LinkedList<Point2D> positions){
        int index = positions.size()%360;
        if (positions.size() >= 360)
            positions.set(index, new Point2D.Double(
                drawPanel.mech.joints[pointNum].getCenterX(), drawPanel.mech.joints[pointNum].getCenterY()));
        else
            positions.add(index, new Point2D.Double(
                    drawPanel.mech.joints[pointNum].getCenterX(), drawPanel.mech.joints[pointNum].getCenterY()));
    }
}
