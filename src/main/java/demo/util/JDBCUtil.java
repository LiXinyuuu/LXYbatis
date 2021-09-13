package demo.util;

import demo.configuration.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCUtil {
    public static Connection getConnection() throws SQLException {
        ResourceBundle pro = ResourceBundle.getBundle("application");
        String url = pro.getString("url");
        String username = pro.getString("username");
        String pwd = pro.getString("password");
        if (pro.containsKey("LineToHump")){
            if (Boolean.parseBoolean( pro.getString("LineToHump")) == false){
                Configuration.setLineToHump(false);
            }
        }
        Connection connection = DriverManager.getConnection(url, username, pwd);
        return connection;
    }

    public static List select(String sql, Method method) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> returnType = method.getReturnType();
        if (returnType.equals(List.class)) {
            Type genericReturnType = method.getGenericReturnType();
            if (null != genericReturnType && genericReturnType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericReturnType;
                Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                returnType = actualTypeArgument;
            }
        }
        Method[] methods = returnType.getMethods();
        Object o = returnType.newInstance();
        Connection connection =null;
        try {
            connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();
            List list = new ArrayList<>();
            while (resultSet.next()) {
                for (int i = 0; i < count; i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    Object object = resultSet.getObject(columnName);
                    if(Configuration.LineToHump){
                        columnName = LineToHump.lineToHump(columnName);
                    }
                    for (Method method1 : methods) {
                        String name = method1.getName();
                        if (name.equals("set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1))) {
                            method1.invoke(o, object);
                            break;
                        }
                    }
                }
                list.add(o);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    public static int update(String sql) throws SQLException {

        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connection.close();
        }

       return 0;
    }


}
