package com.liyunkun.lrcview.Bean;

/**
 * Created by liyunkun on 2016/10/21 0021.
 */
public class LrcBean {

    private long start;
    private long end;
    private String text;

    public LrcBean() {
    }

    public LrcBean(long start, long end, String text) {
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
