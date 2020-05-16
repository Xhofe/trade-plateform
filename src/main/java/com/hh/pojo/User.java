package com.hh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userAvatar;

    public User(String userName, String userPassword, String userEmail, String userPhone, String userAddress, String userAvatar) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userAvatar = userAvatar;
    }
}
