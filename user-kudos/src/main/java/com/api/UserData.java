package com.api;

import lombok.Data;

/**
 * Created by Brenda on 05/08/2018.
 */
@Data
public class UserData {
    private String userMongoId;
    private String nickName;
    private String userName;
    private int quantityKudo;

    public UserData() {
    }

    public UserData(String userMongoId, String nickName, String userName, int quantityKudo) {
        this.userMongoId = userMongoId;
        this.nickName = nickName;
        this.userName = userName;
        this.quantityKudo = quantityKudo;
    }

    public String getUserMongoId() {
        return userMongoId;
    }

    public void setUserMongoId(String userMongoId) {
        this.userMongoId = userMongoId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQuantityKudo() {
        return quantityKudo;
    }

    public void setQuantityKudo(int quantityKudo) {
        this.quantityKudo = quantityKudo;
    }
}