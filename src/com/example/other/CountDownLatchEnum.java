package com.example.other;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum CountDownLatchEnum {

    ONE(1 , "齐国"),
    TWO(2 , "楚国"),
    THREE(3 , "燕国"),
    FOUR(4 , "韩国"),
    FIVE(5 , "赵国"),
    SIX(6 , "魏国");

    private Integer code ;
    private String message ;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CountDownLatchEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CountDownLatchEnum foreach(int index){
        CountDownLatchEnum[] enums = CountDownLatchEnum.values();
        for (CountDownLatchEnum e :enums) {
                if (index == e.getCode()) {
                    return e;
                }
        }
        return null;
    }
}
