package cn.chenzw.security.easy.core.exception;

/**
 * 认证异常类
 *
 * @author chenzw
 */
public class EasySecurityException extends RuntimeException {

    private Integer httpCode;

    public EasySecurityException(String message) {
        super(message);
    }

    public EasySecurityException(int httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public EasySecurityException(int httpCode, String message, Throwable cause) {
        super(message, cause);
        this.httpCode = httpCode;
    }

    public EasySecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EasySecurityException(Throwable cause) {
        super(cause);
    }

    public Integer getHttpCode() {
        return httpCode;
    }
}
