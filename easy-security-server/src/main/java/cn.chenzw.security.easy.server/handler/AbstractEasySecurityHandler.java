package cn.chenzw.security.easy.server.handler;


import cn.chenzw.security.easy.core.constants.EasySecurityConstants;
import cn.chenzw.security.easy.core.exception.EasySecurityException;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenzw
 */
public abstract class AbstractEasySecurityHandler implements EasySecurityHandler {

    @Override
    public boolean checkTokenExpired(String timestamp) {

        Date keyDate;
        try {
            keyDate = DateUtils.parseDate(timestamp, EasySecurityConstants.KEY_TIMESTAMP_FORMAT);
        } catch (ParseException e) {
            throw new EasySecurityException(403, "Token timestampe is invalid.");
        }
        Date today = Calendar.getInstance().getTime();
        if (Math.abs(keyDate.getTime() - today.getTime()) >= EasySecurityConstants.LIMIT_MILLI_SECOND) {
            return false;
        }
        return true;
    }


}
