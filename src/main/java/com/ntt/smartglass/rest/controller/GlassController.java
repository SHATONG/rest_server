package com.ntt.smartglass.rest.controller;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.rest.domain.*;
import com.ntt.smartglass.rest.service.GlassService;
import com.ntt.smartglass.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

/**
 * SmartGlass のAPI
 */
@RestController
@RequestMapping(value = "smartglass")
public class GlassController {

    @Autowired
    private GlassService service;




    /**
     * 認証API
     *
     * @param data 認証キー
     * @return グラス情報 と token
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public DataResult<GlassAuthResultData> auth(@RequestBody CertificationData data) throws AuthenticationException {
        return service.auth(data.getCertificationKey());

    }

    /**
     * オンラインAPI
     *
     * @param token トークン
     * @param data  peerId情報
     * @return オンライン情報
     */
    @RequestMapping(value = "/online", method = RequestMethod.POST)
    public DataResult<String> online(@RequestHeader(value = "token") String token, @RequestBody PeerIdData data) throws AuthenticationException {
        return service.online(token,data.getPeerID());
    }

    /**
     * オフライン API
     *
     * @param token トークン
     * @return オフライン情報
     */
    @RequestMapping(value = "/offline", method = RequestMethod.POST)
    public DataResult<String> offline(@RequestHeader(value = "token") String token) {

        return service.offline(token);
    }


}
