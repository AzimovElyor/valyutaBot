package org.valyuta.valyutaBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.valyuta.buttons.Keyboards;

import java.util.List;


public class Messages {
   private final CurrenciesValues currenciesValues = new CurrenciesValues();
    private final Keyboards keyboards = new Keyboards();
    private final ListCurrenciesAndFlags list = new ListCurrenciesAndFlags();
    public SendMessage registerMessage(String chatId,String title ){
        SendMessage register = new SendMessage(chatId, title);
        register.setReplyMarkup(keyboards.getMainState());
        return register;
    }
 public SendMessage getMainMenuCurrenciesMessage( String chatId,String title){
     SendMessage keyboard = new SendMessage();
     keyboard.setChatId(chatId);
     keyboard.setText(title);
     keyboard.setReplyMarkup(keyboards.getCurrenciesState());
    return keyboard;
 }
 public SendMessage deleteMessageMainMenu(String chatId,String title){
     SendMessage deleteMessage = new SendMessage(chatId, title);
     deleteMessage.setReplyMarkup(keyboards.deleteReplayKeyboards());
     return deleteMessage;
 }
 public SendMessage calculatorMenuMessage(String chatId, String title){
     SendMessage messageCalculatorMenu = new SendMessage(chatId, title);
     messageCalculatorMenu.setReplyMarkup(keyboards.calculatorMenu());
     return messageCalculatorMenu;
 }
 public SendMessage unSuccessesMessage(String chatId, String text){
     return new SendMessage(chatId, text);
 }
 public SendMessage mainCurrenciesMessage(String chatId){
        return new SendMessage(chatId,currenciesValues.mainCurrenciesValues(list.mainCurrenciesList(),list.mainFlags()));
 }
 public SendMessage allCurrenciesMessage(String chatId){
        return new SendMessage(chatId,currenciesValues.allCurrenciesValues(list.allCurrencies(),list.allFlags()));
 }
 public SendMessage dailyExchanges(String chatId, String text){
     SendMessage sendMessage = new SendMessage(chatId, text);
     sendMessage.setReplyMarkup(keyboards.dailyExchangeMessageButtons());
     return sendMessage;

 }
 public SendMessage uzbekCurrenciesCalculatorMenu(String chatId, String text){
     SendMessage moneyValue = new SendMessage(chatId, text);
     moneyValue.setReplyMarkup(keyboards.back());
     return moneyValue;
 }
 public SendMessage allCountriesCurrenciesCalculatorMenu(String chatId, String text){
     SendMessage moneyValue = new SendMessage(chatId, text);
     moneyValue.setReplyMarkup(keyboards.back());
     return moneyValue;
 }
 public SendMessage currentCurrenciesCalculator(String chatId,String value){
        return new SendMessage(chatId,currenciesValues.currentCurrenciesCalculatorValue(value,list.mainCurrenciesList(),list.mainFlags()));
 }
 public SendMessage allCurrenciesCalculator(String chatId,String value){
        return new SendMessage(chatId,currenciesValues.allCountriesCurrenciesCalculatorValues(value,list.mainCurrenciesList(),list.mainFlags()));
 }
 public SendMessage archiveCurrencyMessage(String chatId, String text){
     SendMessage archive = new SendMessage(chatId, text);
     ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup(List.of(keyboards.backMaimMenu()));
     replyMarkup.setResizeKeyboard(true);
     archive.setReplyMarkup(replyMarkup);
     return archive;
 }
 public SendMessage archiveResultMessage(String chatId, String value){
       return new SendMessage(chatId,currenciesValues.mainCurrenciesValues
               (list.archiveCurList(value),list.mainFlags()));
 }
 public SendMessage myAccountChat(String chatId, String text){
     SendMessage suggestions = new SendMessage(chatId, text);
     suggestions.setReplyMarkup(keyboards.botSuggestions());
     return suggestions;
 }
 public SendMessage allBankNews(String chatId, String text){
     SendMessage sendMessage = new SendMessage(chatId, text);
     sendMessage.setReplyMarkup(keyboards.localBanksCurrency());
     return sendMessage;
 }



}
