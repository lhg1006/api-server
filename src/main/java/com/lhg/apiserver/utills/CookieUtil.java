package com.lhg.apiserver.utills;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
public class CookieUtil {

    public void setCookie(HttpServletResponse res, Map<String,Object> param){
        Cookie cookieLogin = new Cookie("isLogin", "true");
        cookieLogin.setPath("/");
        Cookie cookieEmail = new Cookie("userId", (String) param.get("userId"));
        cookieEmail.setPath("/");
        Cookie cookieMemNo = new Cookie("userNo", (String) param.get("userNo"));
        cookieMemNo.setPath("/");

        if(Objects.equals(param.get("isAdmin"), "1")){
            Cookie cookieIsAdmin = new Cookie("isAdmin", "leehyogyu");
            cookieIsAdmin.setPath("/");
            res.addCookie(cookieIsAdmin);
        }
        res.addCookie(cookieLogin);
        res.addCookie(cookieEmail);
        res.addCookie(cookieMemNo);
    }

    public String getCookie(HttpServletRequest req, String cookieName){
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName();
                String value = c.getValue();
                if (name.equals(cookieName)) {
                    return value;
                }
            }
        }
        return null;
    }

    public void deleteCookie(HttpServletResponse res, String cookieId){
        Cookie cookie = new Cookie(cookieId, null);
        cookie.setMaxAge(0);
        res.addCookie(cookie);
    }

    public void deleteAllCookies(HttpServletRequest req,HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                cookie.setPath("/");
                cookie.setMaxAge(0);
                res.addCookie(cookie);
            }
        }
    }

}
