package config;

public class EmployeeModel {
    private String firstname;
    private String lastname;
    private String userid;
    private String username;
    private String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public EmployeeModel(){

    }

    public EmployeeModel(String firstname, String lastname, String userid, String username, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.userid=userid;
        this.username=username;
        this.password=password;

    }
}
