package com.hh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userAddress;
    private String userAvatar;
}
