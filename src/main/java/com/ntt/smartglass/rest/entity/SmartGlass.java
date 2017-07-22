package com.ntt.smartglass.rest.entity;

import java.util.Date;

public class SmartGlass {
    /**
     * smartglass.id
     *
     *
     */
    private Integer id;

    public String getCertificationKey() {
        return certificationKey;
    }

    /**
     * smartglass.certification_key
     *
     * 
     */
    private String certificationKey;

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

    /**
     * smartglass.frame_rate
     *
     * 
     */
    private Short frameRate;

    /**
     * smartglass.create_time
     *
     * 
     */
    private Date createTime;

    /**
     * smartglass.update_time
     *
     * 
     */
    private Date updateTime;

    /**
     * smartglass.is_deleted
     *
     * 
     */
    private Boolean isDeleted;



    /**
     * This method sets the value of the database column smartglass.id
     *
     * @param id the value for smartglass.id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }



    /**
     * This method sets the value of the database column smartglass.certification_key
     *
     * @param certificationKey the value for smartglass.certification_key
     *
     * 
     */
    public void setCertificationKey(String certificationKey) {
        this.certificationKey = certificationKey;
    }

    /**
     * This method returns the value of the database column smartglass.div_id
     *
     * @return the value of smartglass.div_id
     *
     * 
     */
    public Integer getDivId() {
        return divId;
    }

    /**
     * This method sets the value of the database column smartglass.div_id
     *
     * @param divId the value for smartglass.div_id
     *
     * 
     */
    public void setDivId(Integer divId) {
        this.divId = divId;
    }

    /**
     * This method returns the value of the database column smartglass.nickname
     *
     * @return the value of smartglass.nickname
     *
     * 
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method sets the value of the database column smartglass.nickname
     *
     * @param nickname the value for smartglass.nickname
     *
     * 
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method returns the value of the database column smartglass.width
     *
     * @return the value of smartglass.width
     *
     * 
     */
    public Short getWidth() {
        return width;
    }

    /**
     * This method sets the value of the database column smartglass.width
     *
     * @param width the value for smartglass.width
     *
     * 
     */
    public void setWidth(Short width) {
        this.width = width;
    }

    /**
     * This method returns the value of the database column smartglass.height
     *
     * @return the value of smartglass.height
     *
     * 
     */
    public Short getHeight() {
        return height;
    }

    /**
     * This method sets the value of the database column smartglass.height
     *
     * @param height the value for smartglass.height
     *
     * 
     */
    public void setHeight(Short height) {
        this.height = height;
    }

    /**
     * This method returns the value of the database column smartglass.frame_rate
     *
     * @return the value of smartglass.frame_rate
     *
     * 
     */
    public Short getFrameRate() {
        return frameRate;
    }

    /**
     * This method sets the value of the database column smartglass.frame_rate
     *
     * @param frameRate the value for smartglass.frame_rate
     *
     * 
     */
    public void setFrameRate(Short frameRate) {
        this.frameRate = frameRate;
    }



    /**
     * This method sets the value of the database column smartglass.create_time
     *
     * @param createTime the value for smartglass.create_time
     *
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    /**
     * This method sets the value of the database column smartglass.update_time
     *
     * @param updateTime the value for smartglass.update_time
     *
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }



    /**
     * This method sets the value of the database column smartglass.is_deleted
     *
     * @param isDeleted the value for smartglass.is_deleted
     *
     * 
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}