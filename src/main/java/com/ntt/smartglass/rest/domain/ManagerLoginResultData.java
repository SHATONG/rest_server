package com.ntt.smartglass.rest.domain;

/**
 * Created by liuqi on 2017/06/23.
 */
public class ManagerLoginResultData {
    private Integer divId;

    public Integer getDivId() {
        return divId;
    }

    public void setDivId(Integer divId) {
        this.divId = divId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String nickname;
    private String token;

    public ManagerLoginResultData(Integer divId, String nickname, String token, String username  , String name) {
        this.divId = divId;
        this.nickname = nickname;
        this.token = token;
        this.name=name;
        this.username = username;
    }

    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
