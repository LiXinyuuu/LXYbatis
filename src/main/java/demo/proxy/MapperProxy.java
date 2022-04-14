package demo.proxy;

import demo.annotation.Delete;
import demo.annotation.Insert;
import demo.annotation.Select;
import demo.annotation.Update;
import demo.util.JDBCUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class MapperProxy implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Annotation[] annotations = method.getAnnotations();
        Annotation annotation = annotations[0];
        if (annotation instanceof Select) {
            String sql = ((Select) annotation).value();
            List list = JDBCUtil.select(sql, method);
            Class<?> type = method.getReturnType();
            if (!type.equals(List.class)) {
                if(list.size()>0)
                return list.get(0);
                return null;
            }
            return list;
        } else if (annotation instanceof Update) {
            String sql = ((Update) annotation).value();
            int result = JDBCUtil.update(sql);
            return result;
        } else if (annotation instanceof Delete) {
            String sql = ((Delete) annotation).value();
            int result = JDBCUtil.update(sql);
            return result;
        }else if(annotation instanceof Insert){
            String sql = ((Insert) annotation).value();
            int result = JDBCUtil.update(sql);
            return result;
        }

        return null;
    }
}
