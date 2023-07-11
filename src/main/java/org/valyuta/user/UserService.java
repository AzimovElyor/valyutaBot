package org.valyuta.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static UserService userService = null;
    private UserRepository userRepository = UserRepository.getInstance();
  public List<User> getUsers(){
      return userRepository.getAll();
  }
public User getUser(Update update){
    String chatId = "";
      if (update.hasMessage()) {
          chatId = update.getMessage().getChatId().toString();
      }else if(update.hasCallbackQuery()){
          chatId = update.getCallbackQuery().getMessage().getChatId().toString();
      }

    Optional<User> optionalUser = userRepository.findByChatId(chatId);
    String firstname = update.getMessage().getChat().getFirstName();
    String username = update.getMessage().getChat().getUserName();
    String lastname = update.getMessage().getChat().getLastName();

    User user = optionalUser.orElse(User.builder()
            .uuid(UUID.randomUUID())
            .chatId(chatId)
            .created(LocalDateTime.now())
            .updated(LocalDateTime.now())
            .firstname(firstname)
            .lastname(lastname)
            .username(username)
            .userState(UserState.REGISTER)
                    .dailyCurrencyNews(false)
            .build());

    if (optionalUser.isEmpty()) {

         userRepository.add(user);
    }
    return user;
}
public List<User> getUsersByChatId(){
    ArrayList<User> users = new ArrayList<>();
    for (User user : userRepository.getAll()) {
        if(user.isDailyCurrencyNews())users.add(user);
    }
    System.out.println(users);
    System.out.println("---------------------------------------");
    return users;

}
    public void updateUser( User user )
    {
         userRepository.save( user );
    }

    public static UserService getInstance() {
      if(userService == null){
          userService = new UserService();
      }
        return userService;
    }
}
