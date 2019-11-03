package cn.chenzw.security.easy.client;

import cn.chenzw.security.easy.core.constants.EasySecurityConstants;
import cn.chenzw.security.easy.core.domain.entity.AccessTokenEntity;
import cn.chenzw.security.easy.core.domain.entity.TokenEntity;
import cn.chenzw.security.easy.core.exception.EasySecurityException;
import cn.chenzw.toolkit.codec.AESUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 认证客户端工具类（简化操作）
 *
 * @author chenzw
 */
public class EasySecurityClient {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 生成accessToken字符串
     *
     * @param privateKey 密钥
     * @param sysId      系统ID
     * @return
     */
    public static final String generateAccessToken(String privateKey, String sysId) {
        return generateAccessToken(privateKey, sysId, "");
    }

    /**
     * 生成accessToken字符串
     *
     * @param privateKey
     * @param sysId
     * @param userName
     * @return
     */
    public static final String generateAccessToken(String privateKey, String sysId, String userName) {
        try {
            AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
            accessTokenEntity.setSysId(sysId);
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUserName(userName);
            tokenEntity.setTimestamp(DateFormatUtils.format(new Date(), EasySecurityConstants.KEY_TIMESTAMP_FORMAT));
            accessTokenEntity.setToken(AESUtils.encryptAsHexString(objectMapper.writeValueAsString(tokenEntity), privateKey));
            return objectMapper.writeValueAsString(accessTokenEntity);
        } catch (NoSuchPaddingException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | JsonProcessingException e) {
            throw new EasySecurityException(500, "Generate access token with error!", e);
        }
    }


    /**
     * 在response中添加accessToken头
     *
     * @param response
     * @param privateKey
     * @param sysId
     * @param userName
     */
    public static final void setHeaderToken(HttpServletResponse response, String privateKey, String sysId, String userName) {
        String accessToken = generateAccessToken(privateKey, sysId, userName);
        response.setHeader(EasySecurityConstants.DEFAULT_TOKEN_HEADER_NAME, accessToken);
    }

}
