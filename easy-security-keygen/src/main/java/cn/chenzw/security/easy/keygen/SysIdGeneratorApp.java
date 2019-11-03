package cn.chenzw.security.easy.keygen;

import cn.chenzw.security.easy.core.util.SecurityUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Console;

/**
 * 系统密钥生成器
 *
 * @author chenzw
 */
public class SysIdGeneratorApp {

    public static void main(String[] args) {
        String sysId = "", privateKey = "";
        if (ArrayUtils.isEmpty(args)) {
            Console console = System.console();
            if (console == null) {
                throw new IllegalStateException("不能使用控制台");
            }
            while (StringUtils.isBlank(sysId) || StringUtils.isBlank(privateKey)) {
                privateKey = console.readLine("请输入私钥: ");
                sysId = console.readLine("请输入系统(source)标识符：");
            }
        }
        System.out.println("sysId:" + sysId);
        System.out.println("密钥:" + SecurityUtils.getSysPrivateKey(sysId, privateKey));
    }
}
