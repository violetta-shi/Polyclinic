package model;

import java.io.Serializable;

public class Admin implements Serializable {
    private int adminId;
    private String block;
    private String rights;
    private int userId;

    public Admin(int adminId, String block, String rights, int userId) {
        this.adminId = adminId;
        this.block = block;
        this.rights = rights;
        this.userId = userId;
    }

    public Admin(){
        this.adminId = -1;
        this.block = "";
        this.rights = "";
        this.userId = -1;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
