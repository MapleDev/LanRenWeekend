package com.serena.www.lazyweekend.ask;

/**
 * @author MapleDev
 * @time 16/07/31  17:21
 * @desc ${TODD}
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
