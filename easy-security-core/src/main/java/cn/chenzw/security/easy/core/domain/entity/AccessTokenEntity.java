package cn.chenzw.security.easy.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccessTokenEntity<T> {
    private String sysId;

    private String token;

    @JsonIgnore
    private TokenEntity<T> plainToken;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenEntity getPlainToken() {
        return plainToken;
    }

    public void setPlainToken(TokenEntity plainToken) {
        this.plainToken = plainToken;
    }

    @Override
    public String toString() {
        return "AccessTokenEntity{" +
                "sysId='" + sysId + '\'' +
                ", token='" + token + '\'' +
                ", plainToken=" + plainToken +
                '}';
    }
}
