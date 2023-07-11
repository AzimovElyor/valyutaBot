package org.valyuta.valyutaBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;


import org.valyuta.user.User;
import org.valyuta.user.UserService;
import org.valyuta.user.UserState;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class UserHandler {
    private final UserService userService = UserService.getInstance();

    private final Messages messages = new Messages();


    public List<Object> handler(Update update) {
        User user = userService.getUser(update);
        String chatId = user.getChatId();
        List<Object> responses = new ArrayList<>();

        String unSuccessText = "Iltimos togri xabar jonating!";
        if(update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
         user.setUserState(UserState.REGISTER);
         user.setDailyCurrencyNews(false);
        }
        System.out.println(user.isDailyCurrencyNews());

        switch (user.getUserState()) {
            case REGISTER -> {
                user.setUserState(UserState.MAIN_MENU);
                userService.updateUser(user);
               responses.add(messages.registerMessage(chatId,"Assalomu alaykum hurmatli " + user.getFirstname() +
                       " bizning valyuta kurslari haqida ma'lumot beruvchi botimizga hush kelibsiz"));
                return responses;
            }
            case MAIN_MENU -> {
                if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83D\uDCB5 Valyuta kurslari")) {
                    user.setUserState(UserState.CURRENCIES);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz valyutalar bolimini tanladingiz"));
                    responses.add(messages.getMainMenuCurrenciesMessage(chatId,"Menyulardan birini tanlang: "));
                    return responses;
                }
                else if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83D\uDD04 Konvertatsiya kalkulyator")) {
                    user.setUserState(UserState.CALCULATOR);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz konvertatsiya kalkulyator bolimiga o'tdingiz"));

                    responses.add(messages.calculatorMenuMessage(chatId,"Iltimos menyulardan birini tanlang : "));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83D\uDD52 Valyuta kurslari arxivi")) {
                    user.setUserState(UserState.ARCHIVE_CURRENCY);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz valyuta kurslari arxivi bo'limiga o'tdingiz"));
                    responses.add(messages.archiveCurrencyMessage(chatId,"Valyutalarning biror sanadagi kursini" +
                            " ko'rish uchun \n sanani kiriting. Misol uchun (yil-oy-kun)): 2023-07-06"));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("✉️ Bot haqida takliflar")) {
                    responses.add(messages.myAccountChat(chatId,"Ushbu bot haqida takliflaringiz va bot tayyorlash bo'yicha " +
                            "\n murojaatlar yuborishingiz mumkin."));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83C\uDFE6 Mahalliy " +
                        "banklardagi valyuta kurslari")) {
                    responses.add(messages.allBankNews(chatId,"Siz bu yerdan yurtimizdagi banklardagi valyuta kursali" +
                            " haqida \n saytlardan to'liq ma'lumot olishingiz mumkin"));
                    return responses;
                } else {
                    responses.add(messages.unSuccessesMessage(chatId, unSuccessText));
                    return responses;
                }
            }
            case CURRENCIES -> {


                if (update.getMessage().hasText() && update.getMessage().getText().equals("Asosiy valyutalar")) {
                    responses.add(messages.mainCurrenciesMessage(chatId));
                    return responses;
                }
                 else if (update.getMessage().hasText() && update.getMessage().getText().equals("Barcha valyutalar")) {
                    responses.add(messages.allCurrenciesMessage(chatId));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("🏛 Asosiy menu")) {
                    user.setUserState(UserState.MAIN_MENU);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz asosiy menyuga qaytdingiz!"));
                    responses.add(messages.registerMessage(chatId,"Tanlang : "));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83D\uDCB1 Kunlik valyuta xabarlari")) {
                     user.setUserState(UserState.DAILY_EXCHANGE_RATES);
                    userService.updateUser(user);
                    String dailyMessage = user.isDailyCurrencyNews() ? "✅Kunlik xabarlar yoqilgan" : "❌Kunlik xabarlar o'chirilgan.";
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz bu bo'limdan kunlik valyuta kurslari " +
                            "haqidagi xabarlarni har kun Toshkent vaqti" +
                            " bilan soat 09:00 da olishni yoqishingiz yoki o'chirishingiz mumkin.\n\n" + dailyMessage));

                    responses.add(messages.dailyExchanges(chatId,"Tanlang : "));
                    return responses;
                } else {
                    responses.add(messages.unSuccessesMessage(chatId, unSuccessText));
                    return responses;
                }

            }
            case DAILY_EXCHANGE_RATES -> {
                SendMessage sendMessage;
                if (update.getMessage().hasText() && update.getMessage().getText().equals("Yoqish")) {

                    if (!user.isDailyCurrencyNews()) {
                        sendMessage = new SendMessage(user.getChatId(), "✅Kunlik valyuta kurslari xabari yoqildi");
                        user.setDailyCurrencyNews(true);
                        userService.updateUser(user);

                    } else {
                        sendMessage = messages.unSuccessesMessage(chatId,"Sizda allaqachon kunlik valyuta xabarlari yoqilgan");
                    }
                    responses.add(sendMessage);
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("O'chirish")) {

                    if (user.isDailyCurrencyNews()) {
                        sendMessage = new SendMessage(user.getChatId(), "❌Kunlik valyuta kurslari xabari o'chirildi");
                        user.setDailyCurrencyNews(false);
                        userService.updateUser(user);

                    } else {
                        sendMessage = messages.unSuccessesMessage(chatId, "Sizda allaqachon kunlik valyuta xabarlari o'chirilgan.");
                    }
                    responses.add(sendMessage);
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("⬅️Orqaga")) {
                    user.setUserState(UserState.CURRENCIES);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Valyutalar kursi bolimiga qaytdingiz"));

                    responses.add(messages.getMainMenuCurrenciesMessage(chatId,"Menyulardan birini tanlang"));
                    return responses;

                } else {
                    responses.add(messages.unSuccessesMessage(chatId, unSuccessText));
                    return responses;
                }

            }
            case CALCULATOR -> {
                if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83C\uDDFA\uD83C\uDDFFO'zbek so'mida")) {
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz o'zbek so'mini tanladingiz"));
                    responses.add(messages.uzbekCurrenciesCalculatorMenu(chatId,"Pul miqdorini kiriting : "));

                    user.setUserState(UserState.CURRENT_CURRENCY);
                    userService.updateUser(user);
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("\uD83C\uDF0EChet valyutasida")) {

                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz chet el valyutasini tanladingiz"));

                    responses.add(messages.allCountriesCurrenciesCalculatorMenu(chatId,"Pul miqdorini kiriting : "));

                    user.setUserState(UserState.FOREIGN_CURRENCY);
                    userService.updateUser(user);
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("🏛 Asosiy menu")) {
                    user.setUserState(UserState.MAIN_MENU);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz asosiy menyuga qaytdingiz!"));
                    responses.add(messages.registerMessage(chatId,"Tanlang : "));
                    return responses;
                } else {
                    responses.add(messages.unSuccessesMessage(chatId, unSuccessText));
                    return responses;
                }

            }
            case CURRENT_CURRENCY -> {
                if (update.getMessage().hasText() && Validators.moneyValidator(update.getMessage().getText())) {

                    responses.add(messages.currentCurrenciesCalculator(chatId,update.getMessage().getText()));
                    return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("« Orqaga")) {
                    user.setUserState(UserState.CALCULATOR);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz konvertatsiya kalkulator bolimiga qaytdingiz"));
                    responses.add(messages.calculatorMenuMessage(chatId,"Tanlang :"));
                    return responses;
                } else {
                    responses.add(messages.unSuccessesMessage(chatId,"Iltimos, raqamli malumot kiritng\n Masalan--> 1000000(nuqtalarsiz)"));
                    return responses;
                }
            }
            case FOREIGN_CURRENCY -> {
                if (update.getMessage().hasText() && Validators.moneyValidator(update.getMessage().getText())) {
                    responses.add(messages.allCurrenciesCalculator(chatId,update.getMessage().getText()));
                    return responses;
                }
                else if(update.getMessage().hasText() && update.getMessage().getText().equals("« Orqaga")) {
                    user.setUserState(UserState.CALCULATOR);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId,"Siz konvertatsiya kalkulator bolimiga qaytdingiz"));
                    responses.add(messages.calculatorMenuMessage(chatId,"Tanlang :"));
                    return responses;
                }
                else {
                    responses.add(messages.unSuccessesMessage(chatId,"Iltimos, raqamli malumot kiritng\n Masalan--> 1000000(nuqtalarsiz)"));
                    return responses;
                }
            }
            case ARCHIVE_CURRENCY ->{
                if(update.getMessage().hasText() && Validators.dateValidator(update.getMessage().getText())){
                       responses.add(messages.archiveResultMessage(update.getMessage().getChatId().toString(),update.getMessage().getText()));
                       return responses;
                } else if (update.getMessage().hasText() && update.getMessage().getText().equals("🏛 Asosiy menu")) {
                    user.setUserState(UserState.MAIN_MENU);
                    userService.updateUser(user);
                    responses.add(messages.deleteMessageMainMenu(chatId, "Siz  asosiy menyuga qautdingiz."));
                    responses.add(messages.registerMessage(chatId,"Tanlang : "));
                    return responses;
                } else {
                    responses.add(messages.unSuccessesMessage(chatId,"❗ Iltimos sanani togri kiriting.\n" +
                            "Misol uchun (2023-07-06) formatda"));
                    return responses;
                }
            }

        }
        return new ArrayList<>()    ;
    }


    public Object handlerCallbackQuery(Update update){
        User user = userService.getUser(update);
        if(update.getCallbackQuery().getMessage().getText().equals("Murojat")){
            System.out.println("Salom");
            return new SendMessage(user.getChatId(), "Salom");
        }
        return new ArrayList<>();
    }



    }


