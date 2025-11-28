package museum;

import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String id;
    protected String username;
    protected String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public boolean checkPassword(String pwd) { return password.equals(pwd); }

    // For simplicity, allow changing password (not used heavily here)
    public void setPassword(String newPwd) { this.password = newPwd; }
}
