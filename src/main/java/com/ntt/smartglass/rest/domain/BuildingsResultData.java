package com.ntt.smartglass.rest.domain;

import com.ntt.smartglass.common.consts.RestConst;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 */
public class BuildingsResultData {
    /**
     * smartglass.id
     *
     *
     */
    private Integer id;
    /**
     * smartglass.building_name
     *
     *
     */
    private String building_name;
    /**
     * smartglass.building_address
     *
     *
     */
    private String building_address;
    /**
     * smartglass.floors
     *
     *
     */
    private Integer floors;
    /**
     * smartglass.rooms
     *
     *
     */
    private Integer rooms;
    /**
     * smartglass.project_type
     *
     *
     */
    private Integer project_type;
    /**
     * smartglass.project_type
     *
     *
     */
    private Integer project_number;
    /**
     * smartglass.project_number
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

}
