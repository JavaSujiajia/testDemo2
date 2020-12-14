package com.example.demo.core.lang;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 校验异常类，如果业务校验失败，就抛出此异常。
 * <p>
 * 该异常会被{@link ControllerAdvice}统一处理并打印日志
 * </p>
 *
 * @author maheng
 */
public class MessageException extends RuntimeException implements HttpCode {

    private static final long serialVersionUID = 4850280120926507608L;

    /**
     * @param code 错误信息
     */
    public MessageException(String message) {
        super(message);
    }

    /**
     * @param cause 异常
     * @Title: MessageException
     */
    public MessageException(Throwable cause) {
        super(cause);
    }

    /**
     * @param code    错误编码
     * @param message 错误提示信息
     */
    public MessageException(String code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param code 错误编码
     * @param data 要返回的数据
     */
    public MessageException(String code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    private String code = DEFAULT_ERROR_CODE;

    private Object data;

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
