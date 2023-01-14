package aemet;

import java.io.IOException;

public class Controller {

    private FeederTask feederTask;
    private Datalake datalake;
    private Sensor sensor;

    public Controller() {
    }

   public void run() throws IOException {
       sensor = new AemetSensor("");
       datalake = new DatalakeFile();
       feederTask = new FeederTask(sensor.getData());
       datalake.save(feederTask.filterToList());
    }
}
