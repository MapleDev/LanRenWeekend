package com.serena.www.lazyweekend.home.bean;

/**
 * @author Serena
 * @time 2016/8/3  19:56
 * @desc ${TODD}
 */
public class RRepresentData {

    public String title;
    public String price_info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice_info() {
        return price_info;
    }

    public void setPrice_info(String price_info) {
        this.price_info = price_info;
    }

    @Override
    public String toString() {
        return "RRepresentData{" +
                "title='" + title + '\'' +
                ", price_info='" + price_info + '\'' +
                '}';
    }
}
