public abstract class Person {
    private String firstName;
    private String lastName;
    private Affiliation affiliation;

    public Person(String firstName, String lastName, Affiliation affiliation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.affiliation = affiliation;
    }

    public Person() {
    }

    public Person(String firstName) {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }
}
