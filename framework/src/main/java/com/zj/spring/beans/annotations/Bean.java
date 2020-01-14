/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.zj.spring.beans.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author zhaoj
 * @version Bean.java, v 0.1 2020-01-13 13:26
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {
}
