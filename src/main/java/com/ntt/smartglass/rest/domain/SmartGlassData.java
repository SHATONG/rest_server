package com.ntt.smartglass.rest.domain;

/**
 *
 */
public class SmartGlassData {

    /**
     * smartglass.div_id
     *
     * 
     */
    private Integer divId;

    /**
     * smartglass.nickname
     *
     * 
     */
    private String nickname;

    /**
     * smartglass.width
     *
     * 
     */
    private Short width;

    /**
     * smartglass.height
     *
     * 
     */
    private Short height;

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

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Short frameRate) {
        this.frameRate = frameRate;
    }

    /**
     * smartglass.frame_rate
     *
     * 
     */
    private Short frameRate;

}
