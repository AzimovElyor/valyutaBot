package org.valyuta.valyutaBot;

import java.util.regex.Pattern;

public class Validators {
    private static final Pattern moneyValidatiorPattern = Pattern.compile("\\d+$");
    private static final Pattern dateValidatorPattern = Pattern.compile("(\\d{4}-\\d{1,2}-\\d{1,2})");
    public static boolean moneyValidator(String text){
        return moneyValidatiorPattern.matcher(text).matches();
    }
    public static boolean dateValidator(String text){
        return dateValidatorPattern.matcher(text).matches();
    }
}
