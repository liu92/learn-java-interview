package com.learn.interview.chapter6;

/**
 * @ClassName: StateEnum
 * @Description:
 * @Author: lin
 * @Date: 2020/9/10 9:21
 * History:
 * @<version> 1.0
 */
public enum  StateEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"韩"),
    SIX(6,"魏");
    private int code;
    private String retMessage;

    StateEnum(int code, String retMessage) {
        this.code = code;
        this.retMessage = retMessage;
    }

    public int getCode() {
        return code;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public static StateEnum getFor(int index){
        StateEnum[] values = StateEnum.values();
        for (StateEnum value : values) {
            if(index == value.code){
                return  value;
            }
        }
        return  null;
    }
}
