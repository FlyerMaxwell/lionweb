//由mybatis-generator生成并手动修改而成
package com.lion.entity;

import java.util.Date;

public class User {

    // 0代表管理员,  1代表普通用户
    private static final int USER_ADMIN = 0;
    private static final int USER_NORMAL = 1;
    // 0代表用户未被锁定, 1代表用户锁定(毕业或其他情况不再出现在author选择列表中）
    private static final int USER_UNLOCK = 0;
    private static final int USER_LOCK = 1;

    //用户属性（与数据库字段对应）
    private Long id;

    private Long rank;

    private String userName;

    private String realName;

    private Long adminId;

    private String adminName;

    private String password;

    private String imageUrl;

    private String webUrl;

    private String prospect;

    private String cvUrl;

    private String description;

    private String detail;

    private String userEmail;

    private Integer userSex;

    private String userPhone;

    private Date createTime;

    private Integer userType;

    private Integer userState;

    private Integer userRole;

    private Date lastLoginTime;

    private String lastIp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getProspect() {
        return prospect;
    }

    public void setProspect(String prospect) {
        this.prospect = prospect;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl == null ? null : cvUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
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

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
                "userId=" + id +
                "rank" + rank +
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