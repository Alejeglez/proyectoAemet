package aemet;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Datalake {

    void save(List<Weather> weathers) throws IOException;

    List<Weather> read(File datalake) throws IOException;
}
