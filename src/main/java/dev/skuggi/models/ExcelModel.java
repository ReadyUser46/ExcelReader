package dev.skuggi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExcelModel {

    private String pnr;
    private String surname;
    private String name;
    private String type;
    private String fare;
    private String seat;
    private String phone;
    private String email;
    private String culture;
    private String status;
    private String checkedBugs;
    private String SSRcode;

}
