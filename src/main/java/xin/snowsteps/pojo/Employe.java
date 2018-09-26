package xin.snowsteps.pojo;

public class Employe {
    private Integer userId;

    private String name;

    private String pwd;

    private String email;

    private String role;

    private Integer managerId;

    public Employe(Integer userId, String name, String pwd, String email, String role, Integer managerId) {
        this.userId = userId;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.role = role;
        this.managerId = managerId;
    }

    public Employe() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
}