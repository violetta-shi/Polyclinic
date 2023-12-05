package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin")
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;
    @Column(name = "block")
    private String block;
    @Column(name = "rights")
    private String rights;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Admin(int adminId, String block, String rights, User user) {
        this.adminId = adminId;
        this.block = block;
        this.rights = rights;
        this.user = user;
    }

    public Admin(){
        this.adminId = -1;
        this.block = "";
        this.rights = "";
        this.user = null;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
