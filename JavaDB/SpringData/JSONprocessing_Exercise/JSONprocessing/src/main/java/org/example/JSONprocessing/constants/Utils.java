package org.example.JSONprocessing.constants;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Utils {


    public static void writeIntoJsonFile(String output, Path outputPath) throws IOException {

        FileWriter fileWriter = new FileWriter(outputPath.toFile());

        fileWriter.write(output);

        fileWriter.flush();
        fileWriter.close();

    }
}
