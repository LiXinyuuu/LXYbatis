package demo.mapper;

import demo.annotation.*;
import demo.entity.User;

import java.util.List;

@LMapper
public interface UserMapper {
     @Select("select * from user where id = 1")
     User select();
     @Select("select * from user")
     List<User> selectAll();
     @Update("update user set username = '是啥' where id = 1 ")
     int update();
     @Delete("delete from user where id = 2 ")
     int delete();
     @Insert("insert into user values(5,'水水水水','卡','2021-09-10 14:56:56',1,'啥的') ")
     int insert();
}
