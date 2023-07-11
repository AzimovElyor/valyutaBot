package org.valyuta.valyutaBot;


import lombok.SneakyThrows;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.valyuta.buttons.Keyboards;
import org.valyuta.user.User;
import org.valyuta.user.UserRepository;
import org.valyuta.user.UserService;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BotCreated extends TelegramLongPollingBot {
    private UserRepository userRepository = UserRepository.getInstance();
    UserHandler userHandler = new UserHandler();
    private Set<String> set = new HashSet<>();
    public void dailyMessage() throws TelegramApiException {
        Messages messages = new Messages();
         UserService userService = UserService.getInstance();
        List<User> users ;

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        LocalTime localTime = LocalTime.now();
        long currentTime = localTime.getHour() * 3600 + localTime.getMinute() * 60 + localTime.getSecond();
        long dailyTime = 9 * 3600 ;
        long dayTime = 24 * 3600;
        long res = 0;
        if(dailyTime < currentTime) {
            res = dailyTime + ( dayTime - currentTime );
        }
        else res = dailyTime - currentTime;
        System.out.println(res);// 3700
        long hour = res / 3600;
        long minut = (res - (hour * 3600)) / 60;
        System.out.println( "Hour " + hour);
        System.out.println("Minut + " + minut);
        System.out.println(res % 60);

      users = userService.getUsersByChatId();
        for (int i = 0; i < users.size(); i++) {
         String chatId = users.get(i).getChatId();
            System.out.println(users.get(i));
            Runnable runnable = ()->{
                try {
                    execute(messages.mainCurrenciesMessage(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            };
            executor.scheduleAtFixedRate(runnable,res,dayTime, TimeUnit.SECONDS);

        }






    }





    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage()) {
           set.add(update.getMessage().getChatId().toString());
            List<Object> handler = userHandler.handler(update);
            execute(handler);
        }
        else if(update.hasCallbackQuery()){
            userHandler.handlerCallbackQuery(update);
        }

    }

    @Override
    public String getBotUsername() {
        return "t.me/valyuta_UZS_USD_EUR_RUB_Bot";
    }

    @Override
    public String getBotToken() {
        return "6343941673:AAG8NyTniGKJ6sxQJfYDywNp2ZmjS-ls7aE";
    }
    private void execute( List<Object> methods )
    {
        for( Object method : methods )
        {
            execute( method );
        }
    }

    private void execute( Object sendMethod )
    {
        if( sendMethod == null )
        {
            return;
        }
        try
        {
            if( sendMethod instanceof BotApiMethod method )
            {
                execute( method );
            }

            if( sendMethod instanceof SendDocument method )
            {
                super.execute( method );
            }

            if( sendMethod instanceof SendPhoto method )
            {
                super.execute( method );
            }

        }
        catch( TelegramApiException e )
        {
            e.printStackTrace();
        }
    }




}
