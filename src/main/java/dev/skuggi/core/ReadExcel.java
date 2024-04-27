package dev.skuggi.core;

import dev.skuggi.models.ExcelModel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {

    private XSSFWorkbook workbook;
    private Sheet sheet;
    @Getter
    private List<ExcelModel> excelModels;
    private InputStream inputStream;


    public static ReadExcel getInstance() {
        return new ReadExcel();
    }

    @SneakyThrows
    public ReadExcel loadExcel(String path) {

        excelModels = new ArrayList<>();
        inputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
        return this;
    }

    public ReadExcel mapColumns() {

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {

            ExcelModel model = new ExcelModel();

            Row row = rowIterator.next();

            Iterator<Cell> iterator = row.cellIterator();

            while (iterator.hasNext()) {
                Cell cell = iterator.next();
                int colIndex = cell.getColumnIndex();

                switch (colIndex) {
                    case 0:
                        model.setPnr(cell.getStringCellValue());
                        break;
                    case 1:
                        model.setSurname(cell.getStringCellValue());
                        break;
                    case 2:
                        model.setName(cell.getStringCellValue());
                        break;
                    case 3:
                        model.setType(cell.getStringCellValue());
                        break;
                    case 4:
                        model.setFare(cell.getStringCellValue());
                        break;
                    case 5:
                        model.setSeat(cell.getStringCellValue());
                        break;
                    case 6:
                        model.setPhone(cell.getStringCellValue());
                        break;
                    case 7:
                        model.setEmail(cell.getStringCellValue());
                        break;
                    case 8:
                        model.setCulture(cell.getStringCellValue());
                        break;
                    case 9:
                        model.setStatus(cell.getStringCellValue());
                        break;
                    case 10:
                        model.setCheckedBugs(cell.getStringCellValue());
                        break;
                    case 11:
                        model.setSSRcode(cell.getStringCellValue());
                        break;
                }
            }

            excelModels.add(model);
        }

        return this;
    }


    @SneakyThrows
    public ReadExcel closeExcel() {
        workbook.close();
        inputStream.close();
        return this;
    }


}
