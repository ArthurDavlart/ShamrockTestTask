package com.example.shamrocktesttask.dto;

import java.util.Date;

public class IntervalDto {
    private Date startDate;
    private Date finishDate;

    public IntervalDto(Date starDate, Date finishDate) {
        this.startDate = starDate;
        this.finishDate = finishDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date starDate) {
        this.startDate = starDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
