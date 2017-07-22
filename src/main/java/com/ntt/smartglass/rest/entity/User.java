package com.ntt.smartglass.rest.entity;

import java.util.Date;

public class User {
    /**
     * user.id
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private Integer id;

    /**
     * user.username
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private String username;

    /**
     * user.password
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public Integer getDivId() {
        return divId;
    }

    /**
     * user.div_id
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private Integer divId;

    public String getNickname() {
        return nickname;
    }

    /**
     * user.nickname
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private String nickname;

    /**
     * user.create_time
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private Date createTime;

    /**
     * user.is_deleted
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    private Byte isDeleted;



    /**
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    /**
     * This method sets the value of the database column user.username
     *
     * @param username the value for user.username
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setPassword(String password) {
        this.password = password;
    }



    /**
     * This method sets the value of the database column user.div_id
     *
     * @param divId the value for user.div_id
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setDivId(Integer divId) {
        this.divId = divId;
    }



    /**
     * This method sets the value of the database column user.nickname
     *
     * @param nickname the value for user.nickname
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    /**
     * This method sets the value of the database column user.create_time
     *
     * @param createTime the value for user.create_time
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    /**
     * This method sets the value of the database column user.is_deleted
     *
     * @param isDeleted the value for user.is_deleted
     *
     * @mbggenerated Fri Jun 16 14:51:18 JST 2017
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}