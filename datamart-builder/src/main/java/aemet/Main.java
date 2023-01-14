package aemet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {

        Timer timer = new Timer ();
        TimerTask dailyTask = new TimerTask () {
            @Override
            public void run () {
                Controller controller = new Controller();
                try {
                    controller.run();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.schedule (dailyTask, 0l, 1000*60*60*24);

    }
}
