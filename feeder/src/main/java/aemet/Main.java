package aemet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Main {

    public static void main(String[] args) throws IOException {
        Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
            @Override
            public void run () {
                Controller controller = new Controller();
                try {
                    controller.run();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule (hourlyTask, 0l, 1000*60*60);
        }

}
