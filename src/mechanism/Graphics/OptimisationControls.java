package Graphics;

import Helpers.WriterReader;
import Mechanics.Mechanism;
import Optimization.LegionCreator;
import Optimization.OutsourceAttractor;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;

class OptimisationControls extends JPanel {
    private DrawPanel drawPanel;
    private JLabel sliderLabel;
    private JSlider slider;
    private JLabel rightLabel;
    private JButton createTrainSamples;
    private JTextField num;
    private JButton loadMech;
    private JButton train;
    OptimisationControls(DrawPanel dp){
        drawPanel = dp;
        setBackground(Color.gray);

        Font font = new Font("Default", Font.PLAIN, 14);


        sliderLabel = new JLabel("number of samples in training set: 100");
        sliderLabel.setFont(font);
        add(sliderLabel);

        slider = new JSlider(JSlider.HORIZONTAL, 100,500,200);
        slider.setPreferredSize(new Dimension(150, 25));
        add(slider);

        rightLabel = new JLabel("500");
        rightLabel.setFont(font);
        add(rightLabel);

        createTrainSamples = new JButton("create training samples");
        createTrainSamples.setFont(font);
        add(createTrainSamples);
        createTrainSamples.addActionListener(event -> {
            String lastPath = Paths.get(".").toAbsolutePath().normalize().toString();
            lastPath = lastPath.substring(lastPath.length()-11);
            String path = "";
            if (lastPath.equals("Project_jar"))
                path += "..\\..\\..\\";
            path += "data\\mechs.csv";
            if (new File(path).delete())
                System.out.println("Deleted mechs.csv, writing new one");
            new LegionCreator(path, slider.getValue());
        });

        num = new JTextField();
        num.setPreferredSize(new Dimension(30, 25));
        num.setFont(font);
        add(num);

        loadMech = new JButton("load created mech by №");
        loadMech.setFont(font);
        add(loadMech);

        loadMech.addActionListener(event -> loadMech());

        train = new JButton("find optimal mechanism");
        train.setFont(font);
        add(train);
        train.addActionListener( event ->{
            OutsourceAttractor linReg = new OutsourceAttractor();
            linReg.runPython();
        });
    }

    private void loadMech(){
        if (num.getText().equals("")) {
            System.out.println("ERROR: no number entered");
            return;
        }
        int mechNo = -1;
        try {
            mechNo = Integer.parseInt(num.getText());
        } catch (NumberFormatException nfe){
            System.out.println("ERROR: no parsable number found");
            return;
        }
        if (mechNo < 0){
            System.out.println("ERROR: number is negative");
            return;
        }

        String lastPath = Paths.get(".").toAbsolutePath().normalize().toString();
        lastPath = lastPath.substring(lastPath.length()-11);
        String path = "";
        if (lastPath.equals("Project_jar"))
            path += "..\\..\\..\\";

        WriterReader mechReader = new WriterReader(path+"data\\mechs.csv");
        mechReader.readSingle();

        if (mechNo >= mechReader.features.length){
            System.out.println("ERROR: no mech with that number found");
            return;
        }
        double[] m = new double[4];
        System.arraycopy(mechReader.features[mechNo], 0, m, 0, 4);
        Mechanism mech = Mechanism.createMech(m);

        drawPanel.updateMech(mech);
        drawPanel.repaint();
    }
}