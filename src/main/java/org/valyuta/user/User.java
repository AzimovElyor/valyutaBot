package org.valyuta.user;

import lombok.*;
import org.valyuta.common.BaseEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
@Data

@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<UUID> implements Serializable {
    private String  chatId;
    private String firstname;
    private String username;
    private String lastname;
    private UserState userState;
    private boolean dailyCurrencyNews;

    @Builder
    public User(UUID uuid, LocalDateTime created, LocalDateTime updated, String  chatId, String firstname, String username, String lastname, UserState userState,boolean dailyCurrencyNews) {
        super(uuid, created, updated);
        this.chatId = chatId;
        this.firstname = firstname;
        this.username = username;
        this.lastname = lastname;
        this.userState = userState;
        this.dailyCurrencyNews = dailyCurrencyNews;
    }




}
