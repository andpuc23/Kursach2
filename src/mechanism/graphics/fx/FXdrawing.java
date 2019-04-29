package graphics.fx;

import helpers.DoublePair;
import helpers.Utils;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import mechanics_fx.*;

import java.util.LinkedList;

public class FXdrawing  extends Application {
    public static void main(String[] args){launch(args);}

    @Override
    public void start(Stage stage){
        Mechanism m = createMech();

        Group root = new Group();

        Scene scene = new Scene(root, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("fx drawing");

        Button btn = new Button("move");
        Button rotBut = new Button("rotate");
        rotBut.setTranslateX(50);

        Button autoRotater = new Button("start movement");
        autoRotater.setTranslateX(100);

        Button printer = new Button("print results");
        printer.setTranslateY(30);

        root.getChildren().addAll(Utils.toNode(m.contents));
        root.getChildren().addAll(m.joints);
        root.getChildren().add(btn);
        root.getChildren().add(rotBut);
        root.getChildren().add(autoRotater);
        root.getChildren().add(printer);

        LinkedList<DoublePair> positions = new LinkedList<>();

        btn.setOnAction(event ->{
        for (MechPart edge : m.contents) {
            edge.move(5, 5);
        positions.add(m.contents[3].getFinish());
        }});

        printer.setOnAction(evet ->{
            for (DoublePair d : positions) {
                root.getChildren().add(new Circle(d.first, d.second, 2, Color.ORANGE));
            }
        });

        rotBut.setOnAction(event ->
        {
            m.rotate(0,5);
            m.rotate(1,5);
            m.rotate(2,5);
            m.rotate(3,5);
//            notifyAll();
            positions.add(m.contents[3].getFinish());
        });

        autoRotater.setOnAction(event -> {
            MechMover t = new MechMover();
            t.run(rotBut);
        });

        stage.show();
    }

    Mechanism createMech(){
        myPoint A = new myPoint(100,400, true);
        myPoint B = new myPoint(50,400);
        myPoint C = new myPoint(100, 350);
        myPoint D = new myPoint(100, 450);
        myPoint E = new myPoint(150, 400);


        myPoint[] points = new myPoint[]{A, B, C, D, E};

        LinkedList<int[]> edges = new LinkedList<>();
        edges.add(new int[]{0, 1});
        edges.add(new int[]{0, 2});
        edges.add(new int[]{0, 3});
        edges.add(new int[]{0, 4});

        return new Mechanism(edges, points);
    }
}
