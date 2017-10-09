package com.cmy.bigsnow.utils.Event;

/**
 * @Author : mengyuan.cheng
 * @Version : 2017/8/10
 * @E-mail : mengyuan.cheng.mier@gmail.com
 * @Description :
 */

public class MessageEvent {
    private String publish;
    private String subscriber;
    private Object message;

    /**
     *
     * @param publish   事件的发布者
     * @param subscriber 事件的订阅者
     * @param message    发送的内容
     */
    public MessageEvent(String publish, String subscriber, Object message) {
        this.publish = publish;
        this.subscriber = subscriber;
        this.message = message;
    }

    public MessageEvent(Object message) {
        this.message = message;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
