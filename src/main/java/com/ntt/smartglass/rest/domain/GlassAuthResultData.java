package com.ntt.smartglass.rest.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *の
 */
@JsonPropertyOrder(value = {"token","api_key","div_id","nickname","width","height","frame_rate"})
public class GlassAuthResultData {
    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "api_key")
    private String apiKey;
    @JsonProperty(value = "div_id")
    private Integer divId;
    @JsonProperty(value = "nickname")
    private String nickname;
    @JsonProperty(value = "width")
    private Short width;
    @JsonProperty(value = "height")
    private Short height;
    @JsonProperty(value = "frame_rate")
    private Short frameRate;

    public String getToken() {
        return token;
    }
    public String getApi_key() {
        return apiKey;
    }
    public Integer getDiv_id() {
        return divId;
    }
    public String getNickname() {
        return nickname;
    }
    public Short getWidth() {
        return width;
    }
    public Short getHeight() {
        return height;
    }
    public Short getFrame_rate() {
        return frameRate;
    }

    /**
     *
     * @param glassData ガラス情報
     * @param token トークン
     * @param apiKey APIキー
     */
    public GlassAuthResultData(SmartGlassData glassData, String token, String apiKey) {
        this.token = token;
        this.apiKey = apiKey;
        this.nickname = glassData.getNickname();
        this.width = glassData.getWidth();
        this.height = glassData.getHeight();
        this.divId = glassData.getDivId();
        this.frameRate = glassData.getFrameRate();

    }

}
