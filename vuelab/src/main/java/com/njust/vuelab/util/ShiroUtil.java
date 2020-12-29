package com.njust.vuelab.util;

import com.njust.vuelab.shiro.Accountprofile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static Accountprofile getProfile() {
        return (Accountprofile) SecurityUtils.getSubject().getPrincipal();
    }

}
