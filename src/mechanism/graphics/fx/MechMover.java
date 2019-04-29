package graphics.fx;

import javafx.application.Platform;
import javafx.scene.control.Button;

public class MechMover extends Thread{
    public  void run(Button b) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - 1000 < start){
            try {
                Platform.runLater(b::fire);
                sleep(100);

            } catch (InterruptedException e)
            {System.out.println("got interruptedException");}

        }
    }
}
