package com.serena.www.lazyweekend.home.bean;

import java.util.List;

/**
 * @author Serena
 * @time 2016/8/3  19:50
 * @desc ${TODD}
 */
public class RBooking {

    public String content;
    public String type;
    public List<Integer> size;

    public List<Integer> getSize() {
        return size;
    }

    public void setSize(List<Integer> size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RBooking{" +
                "content='" + content + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
