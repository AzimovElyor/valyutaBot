package org.valyuta.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.valyuta.common.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRepository extends BaseRepository<UUID, User> {
    private static UserRepository userRepository = new UserRepository();
    @Override
    public String getFilePath() {
        return "src/main/java/org/valyuta/user/User.txt";
    }
    public Optional<User> findByChatId(String chatId){
        List<User> all = getAll();
        for (User user : all) {
            if(user.getChatId().equals(chatId)) return Optional.of(user);
        }
        return Optional.empty();

    }

    public static UserRepository getInstance() {
        return userRepository;
    }

}
