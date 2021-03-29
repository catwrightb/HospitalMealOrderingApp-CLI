public enum Affiliation {
    NURSE ("Nurse"),
    KITCHEN_STAFF ("Kitchen Staff"),
    PATIENT("Patient"),
    ;

    private final String textRepresentation;

    Affiliation(String textRepresentation) {
        this.textRepresentation = textRepresentation;
    }


    @Override public String toString() {
        return textRepresentation;
    }
}
