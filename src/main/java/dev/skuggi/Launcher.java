package dev.skuggi;

import dev.skuggi.core.Finder;
import dev.skuggi.core.ReadExcel;
import dev.skuggi.models.ExcelModel;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {

    private static final String FOLDER = "c:/automation/data/";

    public static void main(String[] args) {

        for (String filename : getAllExcelsFiles().stream().toList()){
            if (!filename.endsWith("xlsx")) continue;


            List<ExcelModel> originalData =
                    ReadExcel.getInstance()
                            .loadExcel(FOLDER + filename)
                            .mapColumns()
                            .closeExcel()
                            .getExcelModels();

            Finder finder = new Finder(originalData);
            writeOutput(finder.getCleanData(), filename.replace(".xlsx",""));

        }




    }

    @SneakyThrows
    private static void writeOutput(List<ExcelModel> list, String fileName) {

        FileWriter fileWriter = new FileWriter(FOLDER + fileName + ".txt");
        for (ExcelModel model : list) {
            if (model.getPnr().equalsIgnoreCase("Pnr")) continue;
            fileWriter.write(model.getPnr() + System.lineSeparator());
        }
        fileWriter.close();
    }

    @SneakyThrows
    public static Set<String> getAllExcelsFiles() {
        try (Stream<Path> stream = Files.list(Paths.get(FOLDER))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

}

