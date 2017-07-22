package com.ntt.smartglass.rest.service.impl;

import com.ntt.smartglass.common.consts.RestConst;
import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.model.Property;
import com.ntt.smartglass.common.service.impl.BaseService;
import com.ntt.smartglass.common.util.TokenUtils;
import com.ntt.smartglass.common.util.RSAUtils;
import com.ntt.smartglass.rest.domain.ManagerLoginResultData;
import com.ntt.smartglass.rest.domain.ServerCache;
import com.ntt.smartglass.rest.entity.User;
import com.ntt.smartglass.rest.mapper.UserMapper;
import com.ntt.smartglass.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

/**
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private Property property;
    private ServerCache serverCache = ServerCache.getInstance();

    @Override
    public DataResult<ManagerLoginResultData> getManagerLoginResult(String userName, String userPwd) throws Exception {
        User user = ((UserMapper) mapper).selectByUserName(userName);
        if (null == user) {
            return getLoginFailedInfo();
        }
        RSAPublicKey pubKey = RSAUtils.getPublicKey(property.getSecurityKey());
        String pwdRSA = RSAUtils.encryptByPublicKey(userPwd, pubKey);
        if (user.getPassword().equals(pwdRSA)) {
            String token = TokenUtils.createToken(userName, property.getTtlMillis(), property.getBase64Security());
            serverCache.addGlassToken(token, userName);
            ManagerLoginResultData data=new ManagerLoginResultData(user.getDivId(),user.getNickname(),token,user.getUsername(),user.getName());
            return new DataResult<ManagerLoginResultData>(data);
        } else {
            return getLoginFailedInfo();
        }
    }

    private DataResult<ManagerLoginResultData> getLoginFailedInfo() {
        return new DataResult<ManagerLoginResultData>(RestConst.ErrorCodeEnum.LOGIN_FAILED.getCode(), RestConst.ErrorCodeEnum.LOGIN_FAILED.getMessgae());
    }
}
