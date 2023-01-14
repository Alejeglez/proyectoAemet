package aemet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private Datalake file;
    private CreateConnection connDatamart;
    private Connection connection;
    private DdlTranslator tableCreator;
    private DatamartInsert inserter;

    public Controller(){
        file = new DatalakeFile();
        connDatamart = new CreateConnection("datamart");
        connection = connDatamart.connect();
        tableCreator= new DdlTranslator(connection);
        inserter = new DatamartInsert(connection);
    }

    public void run() throws SQLException, IOException {
        String path = System.getProperty("user.dir") + "\\datalake";
        File folder = new File(path);
        tableCreator.createTableMaxTemperatures();
        tableCreator.createTableMinTemperatures();

        for (File fileInFolder : folder.listFiles()){
            List<Weather> weatherList = file.read(fileInFolder);
            FilterWeather filter = new FilterWeather(weatherList);
            inserter.insertStatementOfMax(filter.maxWeather());
            inserter.insertStatementOfMin(filter.minWeather());
        }
        connection.close();
    }
}
