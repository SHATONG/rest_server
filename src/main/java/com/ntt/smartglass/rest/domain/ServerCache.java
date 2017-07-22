package com.ntt.smartglass.rest.domain;

import java.util.Hashtable;

/**
 * ServerCache
 */
public class ServerCache {

    private static ServerCache serverCache = new ServerCache();

    private Hashtable<String, String> glassOnlineCache = new Hashtable<String, String>();
    private Hashtable<String, String> glassTokenCache = new Hashtable<String, String>();
    //for web use
    private Hashtable<String, String> peerIDCache = new Hashtable<String, String>();

    private ServerCache() {

    }

    /**
     * コンストラクト
     *
     * @return ServerCache
     */
    public static ServerCache getInstance() {
        return serverCache;
    }

    /**
     * @param token  トークン
     * @param peerId コンピュータID
     */
    public void addOnlineGlass(String token, String peerId) {
        String certificationKey = glassTokenCache.get(token);
        peerIDCache.put(certificationKey, peerId);
        glassOnlineCache.put(token, peerId);
    }

    /**
     * @param token トークン
     */
    public void removeOnlineGlass(String token) {
        String certificationKey = glassTokenCache.get(token);
        peerIDCache.remove(certificationKey);
        glassOnlineCache.remove(token);
    }

    public boolean isGlassOnline(String token) {
        return glassOnlineCache.containsKey(token);
    }

    /**
     * @param token            トークン
     * @param certificationKey 認証キー
     */
    public void addGlassToken(String token, String certificationKey) {
        glassTokenCache.put(token, certificationKey);
    }


    /**
     * トークンの存在を確認
     *
     * @param token トークン
     * @return
     */
    public boolean isGlassTokenExist(String token) {
        return glassTokenCache.containsKey(token);
    }


    public String getPeerID(String certificationKey) {
        return peerIDCache.get(certificationKey);

    }

}
