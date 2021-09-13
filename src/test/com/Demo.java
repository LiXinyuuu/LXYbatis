import demo.entity.User;
import demo.mapper.UserMapper;
import demo.util.MapperProxyFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class Demo {
    @Test
    public  void demo() throws ClassNotFoundException, SQLException {
        UserMapper mapper = MapperProxyFactory.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        User select = mapper.select();
        int update = mapper.update();
        int delete = mapper.delete();
        int insert = mapper.insert();
    }



}
