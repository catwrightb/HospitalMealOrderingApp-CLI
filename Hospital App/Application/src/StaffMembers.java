public class StaffMembers extends Person{
    private String password;
    private String userName;

    public StaffMembers(String firstName, String lastName, Affiliation affiliation, String password, String userName) {
        super(firstName, lastName, affiliation);
        this.password = password;
        this.userName = userName;
    }

    public StaffMembers(String firstName, String lastName, Affiliation affiliation) {
        super(firstName, lastName, affiliation);
    }

    public StaffMembers() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return getUserName()+ "\n"+
                getPassword();
    }
}
