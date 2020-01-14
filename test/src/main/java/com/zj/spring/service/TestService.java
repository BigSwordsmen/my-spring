/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.zj.spring.service;

import com.zj.spring.beans.annotations.Bean;

/**
 * @author zhaoj
 * @version TestService.java, v 0.1 2020-01-13 10:55
 */
@Bean
public class TestService {

    public Integer calSalary(Integer experience) {
        return experience * 5000;
    }
}
