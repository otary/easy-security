package cn.chenzw.security.easy.core.domain.entity;

import cn.chenzw.security.easy.core.exception.EasySecurityException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenzw
 */
public class SecurityResultEntity {

    private int code;

    private String message;

    public static void error(EasySecurityException e, HttpServletResponse response) throws IOException {
        SecurityResultEntity securityResultEntity = new SecurityResultEntity(e);
        response.setStatus(e.getHttpCode());
        response.getWriter().write(securityResultEntity.toString());
    }

    public SecurityResultEntity() {
    }

    public SecurityResultEntity(EasySecurityException e) {
        this.code = e.getHttpCode();
        this.message = e.getMessage();
    }

    public SecurityResultEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SecurityResultEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
