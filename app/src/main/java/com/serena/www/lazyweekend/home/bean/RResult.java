package com.serena.www.lazyweekend.home.bean;

/**
 * @author Rocky
 * @time 16/7/30  19:55
 */
public class RResult<T> {

    public int status;
    public String msg;
    //public String append_info;
    public T result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /*public String getAppend_info() {
        return append_info;
    }

    public void setAppend_info(String append_info) {
        this.append_info = append_info;
    }*/

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
