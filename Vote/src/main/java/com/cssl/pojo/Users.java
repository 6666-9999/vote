package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Users {

    private Integer uid;
    private String username;
    private String password;
    private Integer isAdmin;
}
