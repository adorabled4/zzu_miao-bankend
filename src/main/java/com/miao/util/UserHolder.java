package com.miao.util;

import com.miao.DTO.UserDTO;

/**
 * @author Dhx_
 * @className UserHolder
 * @description TODO
 * @date 2022/10/22 16:44
 */


public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();

    public static void saveUser(UserDTO user){
        tl.set(user);
    }

    public static UserDTO getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}

