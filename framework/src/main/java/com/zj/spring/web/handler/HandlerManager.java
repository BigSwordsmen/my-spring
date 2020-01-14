package com.zj.spring.web.handler;

import com.zj.spring.web.mvc.Controller;
import com.zj.spring.web.mvc.RequestMapping;
import com.zj.spring.web.mvc.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoj
 * @version HandlerManager.java, v 0.1 2020-01-14 11:36
 */
public class HandlerManager {
    public static List<MappingHandler> mappingHandlerList = new ArrayList<>();

    public static void resolveMappingHandler(List<Class<?>> classList) {
        for (Class<?> cls : classList) {
            if (cls.isAnnotationPresent(Controller.class)) {
                parseHandlerFromController(cls);
            }
        }
    }

    private static void parseHandlerFromController(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method:methods){
            if (!method.isAnnotationPresent(RequestMapping.class)) {
                continue;
            }
            String uri = method.getDeclaredAnnotation(RequestMapping.class).value();
            List<String> paramNameList = new ArrayList<>();
            for (Parameter parameter : method.getParameters()) {
                if (parameter.isAnnotationPresent(RequestParam.class)) {
                    paramNameList.add(parameter.getDeclaredAnnotation(RequestParam.class).value());
                }
            }
            String[] params = paramNameList.toArray(new String[paramNameList.size()]);
            MappingHandler mappingHandler = new MappingHandler(uri, method, cls, params);
            HandlerManager.mappingHandlerList.add(mappingHandler);
        }
    }
}
