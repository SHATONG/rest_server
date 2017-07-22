package com.ntt.smartglass.rest.service;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.service.IService;
import com.ntt.smartglass.rest.domain.GlassAuthResultData;
import com.ntt.smartglass.rest.domain.SmartGlassListResultData;
import com.ntt.smartglass.rest.entity.SmartGlass;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

/**
 * ガラスのサービス クラス
 */

@Service
public interface GlassService extends IService<SmartGlass> {
    /**
     * @param CertificationKey 認証キー
     * @return 認証API レスポンスデータ
     * @throws AuthenticationException 　認証失敗
     */
    DataResult<GlassAuthResultData> auth(String CertificationKey) throws AuthenticationException;

    /**
     * @param token  　トークン
     * @param peerId 　コンピュータID
     * @return オンライン情報
     * @throws AuthenticationException 　認証失敗
     */
    DataResult<String> online(String token, String peerId) throws AuthenticationException;

    /**
     * @param token トークン
     * @return オフライン情報
     */
    DataResult<String> offline(String token);

    /**
     * @param divId 拠点情報ID
     * @return SmartGlass一覧
     */
    DataResult<List<SmartGlassListResultData>> getGlassList(String divId) throws AuthenticationException;
}
