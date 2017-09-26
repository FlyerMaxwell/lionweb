package com.lion.web.entity;

/**
 * @author DJ
 * @date 2017/9/26.
 * 用户实体
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String salt;
    private String headUrl;

    public User(){

    }

    //TODO
    //public User(int id,String name)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
