package com.ntt.smartglass.rest.domain;

import com.ntt.smartglass.common.consts.RestConst;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class UserData {
    @NotNull(message = RestConst.ErrorCode.PARAMETER_LOST+","+"ユーザ名")
    @Length(min = 1, message = RestConst.ErrorCode.INPUT_ERROR+","+"ユーザ名")
    private String username;

    @NotNull(message = RestConst.ErrorCode.PARAMETER_LOST+","+"パスワード")
    @Length(min = 1, message = RestConst.ErrorCode.INPUT_ERROR+","+"パスワード")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
