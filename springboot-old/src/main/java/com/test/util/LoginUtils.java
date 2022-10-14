
package com.test.util;

import com.test.model.entity.UserSessionInfo;
import com.test.util.constant.CookieConstant;
import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**登录工具
 * @className: LoginUtils
 * @description:
 * @author: wwb
 * @date: 2018-08-06 09:10:44
 * @version: ver 1.0
 */
public class LoginUtils {

    /** session中保存的用户信息key */
    public static final String LOGIN_USER_SESSION_KEY = "_login_user";

    /**获取登录用户信息
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-06 09:16:49
     * @param: session
     * @return: com.chaoxing.basicedu.readactivity.platform.web.bean.LoginUserBean
     */
    public static UserSessionInfo getLoginUser(HttpSession session) {
        UserSessionInfo userSessionInfo = (UserSessionInfo)session.getAttribute(LOGIN_USER_SESSION_KEY);
        return userSessionInfo;
    }
    /**登录
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-06 09:18:26
     * @param: loginUserBean
     * @param: session
     * @return: void
     */
    public static void login(UserSessionInfo userSessionInfo, HttpSession session) {
        Assert.notNull(userSessionInfo, "登录用户信息不能为空");
        session.setAttribute(LOGIN_USER_SESSION_KEY, userSessionInfo);
    }
    /**退出登录
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-06 09:19:07
     * @param: session
     * @return: void
     */
    public static void logout(HttpSession session) {
        session.invalidate();
    }
    /**获取uid
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-06 09:29:22
     * @param: cookies
     * @return: java.lang.String
     */
    public static String getUid(Cookie[] cookies) {
        return CookieUtils.getValue(cookies, CookieConstant.UID);
    }
}
