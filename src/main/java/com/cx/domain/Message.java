package com.cx.domain;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 9:53
 */
public class Message {
    private Integer status; // 1.成功 ,0.失败
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
