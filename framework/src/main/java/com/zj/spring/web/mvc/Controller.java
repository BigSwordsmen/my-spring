/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.zj.spring.web.mvc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author zhaoj
 * @version Controller.java, v 0.1 2020-01-14 11:37
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
