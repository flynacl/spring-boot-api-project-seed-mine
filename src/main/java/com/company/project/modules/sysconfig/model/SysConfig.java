package com.company.project.modules.sysconfig.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_config")
public class SysConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String variable;

    private String value;

    @Column(name = "set_time")
    private Date setTime;

    @Column(name = "set_by")
    private String setBy;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return variable
     */
    public String getVariable() {
        return variable;
    }

    /**
     * @param variable
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return set_time
     */
    public Date getSetTime() {
        return setTime;
    }

    /**
     * @param setTime
     */
    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    /**
     * @return set_by
     */
    public String getSetBy() {
        return setBy;
    }

    /**
     * @param setBy
     */
    public void setSetBy(String setBy) {
        this.setBy = setBy;
    }
}