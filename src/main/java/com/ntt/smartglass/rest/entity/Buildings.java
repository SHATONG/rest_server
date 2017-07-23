package com.ntt.smartglass.rest.entity;

import java.util.Date;
import java.util.List;

/**
 * buildings entity
 */
public class Buildings {
    /**
     * Buildings.id
     *
     *
     */
    private Integer id;
    /**
     * Buildings.building_name
     *
     *
     */
    private String building_name;
    /**
     * Buildings.building_address
     *
     *
     */
    private String building_address;
    /**
     * Buildings.floors
     *
     *
     */
    private Integer floors;
    /**
     * Buildings.rooms
     *
     *
     */
    private Integer rooms;
    /**
     * Buildings.project_type
     *
     *
     */
    private Integer project_type;
    /**
     * Buildings.project_type
     *
     *
     */
    private Integer project_number;
    /**
     * Buildings.project_number
     *
     *
     */
    private Date createTime;

    /**
     * Buildings.update_time
     *
     *
     */
    private Date updateTime;

    /**
     * Buildings.is_deleted
     *
     *
     */
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getBuilding_address() {
        return building_address;
    }

    public void setBuilding_address(String building_address) {
        this.building_address = building_address;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setFloors(Integer floors) {
        this.floors = floors;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getProject_type() {
        return project_type;
    }

    public void setProject_type(Integer project_type) {
        this.project_type = project_type;
    }

    public Integer getProject_number() {
        return project_number;
    }

    public void setProject_number(Integer project_number) {
        this.project_number = project_number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }



    @Override
    public String toString() {
        return "Buildings{" +
                "id=" + id +
                ", building_name='" + building_name + '\'' +
                ", building_address='" + building_address + '\'' +
                ", floors=" + floors +
                ", rooms=" + rooms +
                ", project_type=" + project_type +
                ", project_number=" + project_number +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }

    /**
     * construct method
     * @param id
     * @param building_name
     * @param building_address
     * @param floors
     * @param rooms
     * @param project_type
     * @param project_number
     * @param createTime
     * @param updateTime
     * @param isDeleted
     */
    public Buildings(Integer id, String building_name, String building_address, Integer floors, Integer rooms, Integer project_type, Integer project_number, Date createTime, Date updateTime, Boolean isDeleted) {
        this.id = id;
        this.building_name = building_name;
        this.building_address = building_address;
        this.floors = floors;
        this.rooms = rooms;
        this.project_type = project_type;
        this.project_number = project_number;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
    }
}
