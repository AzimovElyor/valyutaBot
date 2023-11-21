package org.valyuta.buttons;

import org.telegram.telegrambots.meta.api.objects.LoginUrl;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class Keyboards {


    public ReplyKeyboardMarkup getMainState(){
        KeyboardButton mainCurrency = new KeyboardButton("\uD83D\uDCB5 Valyuta kurslari");
        KeyboardButton calculator = new KeyboardButton("\uD83D\uDD04 Konvertatsiya kalkulyator");
        KeyboardButton archive = new KeyboardButton("\uD83D\uDD52 Valyuta kurslari arxivi");
        KeyboardButton aboutBot = new KeyboardButton("✉️ Bot haqida takliflar");
        KeyboardButton newBankCurrency = new KeyboardButton("\uD83C\uDFE6 Mahalliy banklardagi valyuta kurslari");


        KeyboardRow keyboardRow = new KeyboardRow(List.of(mainCurrency));
        KeyboardRow keyboardRow1 = new KeyboardRow(List.of(calculator));
        KeyboardRow keyboardRow2 = new KeyboardRow(List.of(archive, aboutBot));
        KeyboardRow keyboardRow3 = new KeyboardRow(List.of(newBankCurrency));


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(keyboardRow, keyboardRow1, keyboardRow2,keyboardRow3));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup getCurrenciesState(){
        KeyboardRow mainCurrencies = new KeyboardRow(List.of(new KeyboardButton("Asosiy valyutalar")));
        KeyboardRow allCurrencies = new KeyboardRow(List.of(new KeyboardButton("Barcha valyutalar"), new KeyboardButton("\uD83D\uDCB1 Kunlik valyuta xabarlari")));
        KeyboardRow mainMenu = new KeyboardRow(List.of(new KeyboardButton("🏛 Asosiy menu")));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(mainCurrencies, allCurrencies, mainMenu));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardRemove deleteReplayKeyboards(){
        return  new ReplyKeyboardRemove(true);
    }
    public ReplyKeyboardMarkup calculatorMenu(){
        KeyboardButton buttonCalc1 = new KeyboardButton("\uD83C\uDDFA\uD83C\uDDFFO'zbek so'mida");
        KeyboardButton buttonCalc2 = new KeyboardButton("\uD83C\uDF0EChet valyutasida");
        KeyboardRow uzbekCurrency = new KeyboardRow(List.of(buttonCalc1));
        KeyboardRow allC = new KeyboardRow(List.of(buttonCalc2));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(uzbekCurrency,allC,backMaimMenu()));
       replyKeyboardMarkup.setResizeKeyboard(true);
       return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup back(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(new KeyboardButton("« Orqaga")))));
        markup.setResizeKeyboard(true);
        return markup;
    }
    public KeyboardRow backMaimMenu(){
        return new KeyboardRow(List.of(new KeyboardButton("🏛 Asosiy menu")));
    }
    public ReplyKeyboardMarkup dailyExchangeMessageButtons(){
        KeyboardRow keyboardRow = new KeyboardRow(List.of(new KeyboardButton("Yoqish"), new KeyboardButton("O'chirish")));
        KeyboardRow back = new KeyboardRow(List.of(new KeyboardButton("⬅️Orqaga")));
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(List.of(keyboardRow, back));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public InlineKeyboardMarkup botSuggestions(){
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton("\uD83D\uDD8D Murojat yozish");

        inlineKeyboardButton.setCallbackData("Murojat");

        inlineKeyboardButton.setUrl("https://t.me/elyorazimov");


        return new InlineKeyboardMarkup(List.of(List.of(inlineKeyboardButton)));
    }
    public InlineKeyboardMarkup localBanksCurrency(){
        InlineKeyboardButton nbu = new InlineKeyboardButton("Markaziy Bank");
        nbu.setUrl("https://nbu.uz/uz/exchange-rates/");
        InlineKeyboardButton hamkorBank = new InlineKeyboardButton("HamkorBank");
        hamkorBank.setUrl("https://www.hamkorbank.uz/uz/exchange-rate/");
        InlineKeyboardButton infinBank = new InlineKeyboardButton("InfinBank");
        infinBank.setUrl("https://www.infinbank.com/uz/private/exchange-rates/");
        InlineKeyboardButton kapitalBank = new InlineKeyboardButton("KAPITALBANk");
        kapitalBank.setUrl("https://kapitalbank.uz/uz/services/exchange-rates/");
        InlineKeyboardButton davrBank = new InlineKeyboardButton("Davr Bank");
        davrBank.setUrl("https://davrbank.uz/uz/currency/");
        InlineKeyboardButton transtBank = new InlineKeyboardButton("TranstBank");
        transtBank.setUrl("https://trustbank.uz/uz/services/exchange-rates-in-branches/");
        InlineKeyboardButton anorBank = new InlineKeyboardButton("ANORBANK");
        anorBank.setUrl("https://anorbank.uz/uz/about/exchange-rates/");
        InlineKeyboardButton microBank = new InlineKeyboardButton("Mikrokreditbank");
        microBank.setUrl("https://mkbank.uz/uz/services/exchange-rates/");
        InlineKeyboardButton aloqabank = new InlineKeyboardButton("Aloqabank");
        aloqabank.setUrl("https://aloqabank.uz/uz/services/exchange-rates/");
        InlineKeyboardButton ipotekaBank = new InlineKeyboardButton("Aloqabank");
        ipotekaBank.setUrl("https://www.ipotekabank.uz/uz/currency/");


        return new InlineKeyboardMarkup(List.of(List.of(nbu,hamkorBank),
                List.of(infinBank,kapitalBank), List.of(davrBank,transtBank), List.of(anorBank,microBank),
                List.of(aloqabank,ipotekaBank)));

    }
}
