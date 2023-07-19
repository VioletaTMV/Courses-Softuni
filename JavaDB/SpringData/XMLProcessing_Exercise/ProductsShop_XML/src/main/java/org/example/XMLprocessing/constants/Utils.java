package org.example.XMLprocessing.constants;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Utils {


    public static void writeIntoJsonFile(String output, Path outputPath) throws IOException {

        FileWriter fileWriter = new FileWriter(outputPath.toFile());

        fileWriter.write(output);

        fileWriter.flush();
        fileWriter.close();

    }
}
