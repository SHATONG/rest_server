package com.ntt.smartglass.rest.service;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.rest.domain.ManagerLoginResultData;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * ユーザーののサービス クラス
 */
@Service
public interface UserService {
    /**
     * @param userName ユーザー名
     * @param userPwd  ユーザーのパスワード
     * @return トークン情報
     * @throws Exception
     */
    DataResult<ManagerLoginResultData> getManagerLoginResult(String userName, String userPwd) throws Exception;
}
