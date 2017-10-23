//由mybatis-generator生成并手动修改而成
package com.lion.entity;

import java.util.Date;

public class User {

    // 0代表管理员,  1代表普通用户
    private static final int USER_ADMIN = 0;
    private static final int USER_NORMAL = 1;
    // 0代表用户未被锁定, 1代表用户锁定
    private static final int USER_UNLOCK = 0;
    private static final int USER_LOCK = 1;

    //用户属性（与数据库字段对应）
    private Long userId;

    private String userName;

    private String password;

    private String userEmail;

    private String userSex;

    private String userPhone;

    private Date createTime;

    private Integer userType;

    private Integer userState;

    private Date lastLoginTime;

    private String lastIp;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName=" + userName +
                ", password=" + password +
                ", userEmail=" + userEmail +
                ", userPhone=" + userPhone +
                ", userSex=" + userSex +
                ", createTime=" + createTime +
                ", userType=" + userType +
                ", userState=" + userState +
                ", lastLoginTime=" + lastLoginTime +
                ", lastIp=" + lastIp + "}";
    }

}