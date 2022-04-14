package demo.util;

import demo.annotation.LMapper;
import demo.proxy.MapperProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MapperProxyFactory {
    public static Object getMapper(Class mapper) {
        Annotation annotation = mapper.getAnnotation(LMapper.class);
        if(annotation==null){
            return null;
        }
        InvocationHandler invocationHandler = new MapperProxy();
        Object o = Proxy.newProxyInstance(mapper.getClassLoader(), new Class[]{mapper}, invocationHandler);
        return o;
    }
}
