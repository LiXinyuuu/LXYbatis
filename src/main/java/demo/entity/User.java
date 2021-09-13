package demo.entity;

import java.util.Date;

public class User {
    Integer id;
    String username;
    String nick_name;
    Date birthday;
    Integer sex;
    String address;

    public String getUsername() {
        return username;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public void setNickName(String nickName) {
        nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }
}
