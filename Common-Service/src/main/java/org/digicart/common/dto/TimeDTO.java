/*
 * #%L
 * BroadleafCommerce Common Libraries
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.digicart.common.dto;


import org.digicart.common.time.DayOfMonthType;
import org.digicart.common.time.DayOfWeekType;
import org.digicart.common.time.HourOfDayType;
import org.digicart.common.time.MinuteType;
import org.digicart.common.time.MonthType;
import org.digicart.common.time.SystemTime;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bpolster.
 */
public class TimeDTO {


    private Calendar cal;

    
    private Integer hour;

    
    private Integer dayOfWeek;

    
    private Integer month;

    
    private Integer dayOfMonth;

    
    private Integer minute;

    
    private Date date;

    public TimeDTO() {
        cal = SystemTime.asCalendar();
    }

    public TimeDTO(Calendar cal) {
        this.cal = cal;
    }


    /**
     * @return  int representing the hour of day as 0 - 23
     */
    public HourOfDayType getHour() {
        if (hour == null) {
            hour = cal.get(Calendar.HOUR_OF_DAY);
        }
        return HourOfDayType.getInstance(hour.toString());
    }

    /**
     * @return int representing the day of week using Calendar.DAY_OF_WEEK values.
     * 1 = Sunday, 7 = Saturday
     */
    public DayOfWeekType getDayOfWeek() {
        if (dayOfWeek == null) {
            dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
        return DayOfWeekType.getInstance(dayOfWeek.toString());
    }

    /**
     * @return the current day of the month (1-31).
     */
    public DayOfMonthType getDayOfMonth() {
        if (dayOfMonth == null) {
            dayOfMonth =  cal.get(Calendar.DAY_OF_MONTH);
        }
        return DayOfMonthType.getInstance(dayOfMonth.toString());
    }

    /**
     * @return int representing the current month (1-12)
     */
    public MonthType getMonth() {
        if (month == null) {
            month = cal.get(Calendar.MONTH);
        }
        return MonthType.getInstance(month.toString());
    }

    public MinuteType getMinute() {
        if (minute == null) {
            minute = cal.get(Calendar.MINUTE);
        }
        return MinuteType.getInstance(minute.toString());
    }

    public Date getDate() {
        if (date == null) {
            date = cal.getTime();
        }
        return date;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

    public void setHour(HourOfDayType hour) {
        this.hour = Integer.valueOf(hour.getType());
        ;
    }

    public void setDayOfWeek(DayOfWeekType dayOfWeek) {
        this.dayOfWeek = Integer.valueOf(dayOfWeek.getType());
    }

    public void setMonth(MonthType month) {
        this.month = Integer.valueOf(month.getType());
    }

    public void setDayOfMonth(DayOfMonthType dayOfMonth) {
        this.dayOfMonth = Integer.valueOf(dayOfMonth.getType());
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMinute(MinuteType minute) {
        this.minute = Integer.valueOf(minute.getType());
        ;
    }
}
