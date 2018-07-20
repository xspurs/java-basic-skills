package com.brctl.effectivejava.chapter11;

import java.util.Date;

/**
 * Period class, consists of start time and end time
 * @author duanxiaoxing
 * @created 2017/9/12
 */
public final class Period implements java.io.Serializable {

    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());
        if (this.start.compareTo(this.end) > 0) {
            throw new IllegalArgumentException("start before end!");
        }
    }

    public Date getStart() {
        return new Date(this.start.getTime());
    }

    public Date getEnd() {
        return new Date(this.end.getTime());
    }

    @Override
    public String toString() {
        return this.start + " - " + this.end;
    }

}
