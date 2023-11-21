package org.valyuta.valyutaBot;


import org.valyuta.valutes.Currency;

import java.text.DecimalFormat;
import java.util.List;


public class CurrenciesValues {
    public String mainCurrenciesValues(List<Currency> mainCurrency, List<String> flags){
        return getString(mainCurrency, flags);
    }



    public String allCurrenciesValues(List<Currency> allCurrency,List<String> flags){
        return getString(allCurrency, flags);
    }
    public String currentCurrenciesCalculatorValue(String value,List<Currency> main, List<String> flags){
        final DecimalFormat df = new DecimalFormat("0.00");
        double money = Double.parseDouble(value); // 100

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < main.size(); i++) {
            result.append(flags.get(i)).append(main.get(i).getCcyNm_UZ()).append("\n");
            double moneyRes = money / main.get(i).getRate();
            String format = df.format(moneyRes);
            result.append(money).append("UZS = ").append(format).append(" ").append(main.get(i).getCcy()).append(" \n");
            result.append("\n");
        }
        result.append(" \uD83D\uDD52 Sana: ").append(main.get(0).getDate()).append("\n");
        result.append("https://t.me/UZB_DUNYO_VALYUTA_BOT");

        return result.toString();
    }
    public String allCountriesCurrenciesCalculatorValues(String value,List<Currency> all,List<String> flags){
        final DecimalFormat df = new DecimalFormat("0.00");
        double money = Double.parseDouble(value); // 100

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < all.size(); i++) {
            result.append(flags.get(i)).append(all.get(i).getCcyNm_UZ()).append("\n");
            double moneyRes = money * all.get(i).getRate();
            String format = df.format(moneyRes);
            result.append(money).append(" ").append(all.get(i).getCcyNm_UZ()).append(" = ").append(format).append(" UZS ").append(" \n");
            result.append("\n");
        }
        result.append(" \uD83D\uDD52 Sana: ").append(all.get(0).getDate()).append("\n");
        result.append("https://t.me/UZB_DUNYO_VALYUTA_BOT");
        return result.toString();
    }
    private String getString(List<Currency> mainCurrency, List<String> flags) {
        System.out.println("Main" +mainCurrency );
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < mainCurrency.size(); i++) {
            String icon = ( mainCurrency.get(i).getDiff() > 0 ) ? "🔺" : ( mainCurrency.get(i).getDiff() < 0 ) ? "🔻" : "0️⃣";
            response.append(flags.get(i)).append(mainCurrency.get(i).getCcyNm_UZ()).append("\n");
            response.append("1 ").append(mainCurrency.get(i).getCcy()).append(" = ").append(mainCurrency.get(i).getRate())
                    .append(" UZS (").append(icon).append(mainCurrency.get(i).getDiff()).append(" )\n");
            response.append("\n");
        }
        response.append(" \uD83D\uDD52 Sana: ").append(mainCurrency.get(0).getDate()).append("\n");
        response.append("https://t.me/UZB_DUNYO_VALYUTA_BOT");
        return response.toString();
    }
}
