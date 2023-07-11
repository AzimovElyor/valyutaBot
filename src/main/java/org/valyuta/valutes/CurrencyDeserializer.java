package org.valyuta.valutes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrencyDeserializer extends StdDeserializer<Currency> {
    public CurrencyDeserializer() {
        this(null);
    }

    public CurrencyDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Currency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        String local = treeNode.get("Date").toString().substring(1, treeNode.get("Date").toString().length() - 1);
        LocalDate localDate = LocalDate.parse(local, dateTimeFormatter);
        String rate = treeNode.get("Rate").toString().substring(1, treeNode.get("Rate").toString().length() - 1);
        String diff = treeNode.get("Diff").toString().substring(1, treeNode.get("Diff").toString().length() - 1);


        Currency currency = Currency.builder()
                .id(Integer.parseInt(treeNode.get("id").toString()))
                .Code(treeNode.get("Code").toString())
                .Ccy(treeNode.get("Ccy").toString())
                .CcyNm_RU(treeNode.get("CcyNm_RU").toString())
                .CcyNm_UZ(treeNode.get("CcyNm_UZ").toString())
                .CcyNm_UZC(treeNode.get("CcyNm_UZC").toString())
                .CcyNm_EN(treeNode.get("CcyNm_EN").toString())
                .Nominal(treeNode.get("Nominal").toString())
                .Rate(Double.parseDouble(rate))
                .Diff(Double.parseDouble(diff))
                .Date(localDate)
                .build();
        System.out.println(currency);

        return currency;
    }
}
