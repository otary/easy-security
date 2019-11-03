package cn.chenzw.security.easy.core.util;

import cn.chenzw.security.easy.core.constants.EasySecurityConstants;
import cn.chenzw.security.easy.core.domain.entity.AccessTokenEntity;
import cn.chenzw.security.easy.core.domain.entity.TokenEntity;
import cn.chenzw.security.easy.core.exception.EasySecurityException;
import cn.chenzw.toolkit.codec.AESUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author chenzw
 */
public class SecurityUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    private SecurityUtils() {
    }

    /**
     * 生成系统密钥
     *
     * @param sysId
     * @return
     */
    public static final String getSysPrivateKey(String sysId) {
        return DigestUtils.md5DigestAsHex((sysId + EasySecurityConstants.PRIVATE_KEY).getBytes());
    }

    /**
     * 生成系统密钥
     *
     * @param sysId
     * @param privateKey
     * @return
     */
    public static final String getSysPrivateKey(String sysId, String privateKey) {
        return DigestUtils.md5DigestAsHex((sysId + privateKey).getBytes());
    }

    public static final AccessTokenEntity parseAccessToken(final String accessTokenStr) throws IOException, NoSuchPaddingException, DecoderException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        AccessTokenEntity accessTokenEntity = objectMapper.readValue(accessTokenStr, AccessTokenEntity.class);

        // 获取sysId和加密串
        String sysId = accessTokenEntity.getSysId();

        if (StringUtils.isEmpty(sysId)) {
            throw new EasySecurityException(401, "sysId is null.");
        }

        // 生成系统密钥
        String sysPrivateKey = getSysPrivateKey(sysId);

        // 解密token
        String tokenPlainText = new String(AESUtils.decryptHexString(accessTokenEntity.getToken(), sysPrivateKey), StandardCharsets.UTF_8);

        // 用户名 + 时间戳 + extData
        TokenEntity tokenEntity = objectMapper.readValue(tokenPlainText, TokenEntity.class);

        if (StringUtils.isEmpty(tokenEntity.getTimestamp())) {
            throw new EasySecurityException(401, "Token timestamp is null.");
        }
        accessTokenEntity.setPlainToken(tokenEntity);

        return accessTokenEntity;

    }

    public static final AccessTokenEntity parseAccessToken(HttpServletRequest request) {
        String accessTokenStr = request.getHeader(EasySecurityConstants.DEFAULT_TOKEN_HEADER_NAME);

        if (StringUtils.isEmpty(accessTokenStr)) {
            // 未携带认证token
            throw new EasySecurityException(401, "AccessToken is null!");
        }

        try {
            return SecurityUtils.parseAccessToken(accessTokenStr);
        } catch (IOException | NoSuchPaddingException | DecoderException | InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            throw new EasySecurityException(403, "Token is invaild!");
        }
    }
}
