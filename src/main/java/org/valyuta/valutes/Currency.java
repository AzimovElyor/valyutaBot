package org.valyuta.valutes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Currency {

   /* "id": 69,
            "Code": "840",
            "Ccy": "USD",
            "CcyNm_RU": "Доллар США",
            "CcyNm_UZ": "AQSH dollari",
            "CcyNm_UZC": "АҚШ доллари",
            "CcyNm_EN": "US Dollar",
            "Nominal": "1",
            "Rate": "11488.12",
            "Diff": "18.55",
            "Date": "27.06.2023"*/

    private int id;
    private String Code;
    private String Ccy;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String CcyNm_EN;
    private String Nominal;
    private double Rate;
    private double Diff ;
   LocalDate Date;



}
