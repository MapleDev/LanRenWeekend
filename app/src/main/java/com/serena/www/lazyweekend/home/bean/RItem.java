package com.serena.www.lazyweekend.home.bean;

import java.util.List;

/**
 * @author Rocky
 * @time 16/7/30  20:20
 */
public class RItem {
    /**
     * collected_num : 60
     * time_info : 截止至10月31日
     * title : 私人定制录音体验 | 你的歌手梦从未止步于方寸之间
     * poi_name : 壹首音乐工作室
     * price_info : 138
     * front_cover_image_list : ["http://image.lanrenzhoumo.com/leo/img/20160718165422_4d8e07731f6e2acd4de405e86b055519.jpg","http://image.lanrenzhoumo.com/leo/img/20160713165637_817c907d7b307ccc833f64351a57d693.jpg","http://image.lanrenzhoumo.com/leo/img/20160713170709_93afcd5a2321e9bd1f8e5e4bb39b6ae8.jpg","http://image.lanrenzhoumo.com/leo/img/20160713172057_1f646b9810c6b4528befd298a19480d2.jpg","http://image.lanrenzhoumo.com/leo/img/20160713165518_27e5fbe99f4fcb5594574fcdfc40ee35.jpg"]
     * category : 文艺生活
     */
    public int collected_num;
    public String time_info;
    public String title;
    public String poi_name;
    public String price_info;
    public List<String> front_cover_image_list;
    public String category;
    public long leo_id;

    public int getCollected_num() {
        return collected_num;
    }

    public void setCollected_num(int collected_num) {
        this.collected_num = collected_num;
    }

    public String getTime_info() {
        return time_info;
    }

    public void setTime_info(String time_info) {
        this.time_info = time_info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoi_name() {
        return poi_name;
    }

    public void setPoi_name(String poi_name) {
        this.poi_name = poi_name;
    }

    public String getPrice_info() {
        return price_info;
    }

    public void setPrice_info(String price_info) {
        this.price_info = price_info;
    }

    public List<String> getFront_cover_image_list() {
        return front_cover_image_list;
    }

    public void setFront_cover_image_list(List<String> front_cover_image_list) {
        this.front_cover_image_list = front_cover_image_list;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "RItem{" +
                "collected_num=" + collected_num +
                ", time_info='" + time_info + '\'' +
                ", title='" + title + '\'' +
                ", poi_name='" + poi_name + '\'' +
                ", price_info='" + price_info + '\'' +
                ", front_cover_image_list=" + front_cover_image_list +
                ", category='" + category + '\'' +
                '}';
    }
}
