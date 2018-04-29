package com.kangde.collection.model;

import java.io.Serializable;
/**节假日*/
public class HolidayModel implements Serializable{
	private static final long serialVersionUID = -4124917550196586883L;

	private Integer id;

	/**日期*/
    private String holiday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday == null ? null : holiday.trim();
    }
}