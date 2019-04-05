package com.itdan.manager.error;

/**
 * 异常枚举类
 */
public enum ErrorEnum {
    ID_IN_NULL("F001","编号不能为空",false),
    UNKNOWN("999","未知错误",false);

    private String code;
    private String message;
    private boolean canRetry;

    /**
     * 构造器
     * @param code
     * @param message
     * @param canRetry
     */
    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    /**
     * 根据编号获取相关的异常
     * @param code
     * @return
     */
    public static ErrorEnum getErroeByCode(String code){
        for (ErrorEnum errorEnum:ErrorEnum.values()){
            //当异常编号相等时返回相应的错误
            if(errorEnum.code.equals(code)){
                return errorEnum;
            }
        }
        return UNKNOWN;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}
