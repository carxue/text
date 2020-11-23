package com.annotation.fieldconver;


/**
 * @author CaoYunfei
 * @Discription 运行平台搜索活动类型
 */
public enum UserType {
    SMS("平台"),
    UMS("商家");

    private String text;

    UserType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
