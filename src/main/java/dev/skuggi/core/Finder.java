package dev.skuggi.core;

import dev.skuggi.models.ExcelModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Finder {

    List<ExcelModel> orig;


    public List<ExcelModel> getCleanData() {

        List<ExcelModel> cleanData = new ArrayList<>();
        cleanData.addAll(findUniques(orig));
        cleanData.addAll(findUniquePNRs(findDuplicates(orig)));
        return cleanData;
    }

    private List<ExcelModel> findUniquePNRs(List<ExcelModel> excelModels) {
        Set<String> seen = new HashSet<>();
        return excelModels.stream()
                .filter(model -> seen.add(model.getPnr()))
                .collect(Collectors.toList());
    }

    private List<ExcelModel> findUniques(List<ExcelModel> excelModels) {
        return excelModels.stream()
                .filter(excelModel -> Objects.nonNull(excelModel.getPnr()))
                .collect(Collectors.groupingBy(ExcelModel::getPnr))
                .values()
                .stream()
                .filter(models -> models.size() == 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }

    private List<ExcelModel> findDuplicates(List<ExcelModel> excelModels) {
        return excelModels.stream()
                .filter(excelModel -> Objects.nonNull(excelModel.getPnr()))
                .collect(Collectors.groupingBy(ExcelModel::getPnr))
                .values()
                .stream()
                .filter(models -> models.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }
}
