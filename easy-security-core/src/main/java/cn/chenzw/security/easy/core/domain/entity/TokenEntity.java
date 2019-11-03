package cn.chenzw.security.easy.core.domain.entity;

/**
 * Token实体类
 *
 * @param <T>
 * @author chenzw
 */
public class TokenEntity<T> {

    private String userName;

    private String timestamp;

    private T extData;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getExtData() {
        return extData;
    }

    public void setExtData(T extData) {
        this.extData = extData;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "userName='" + userName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", extData=" + extData +
                '}';
    }
}
