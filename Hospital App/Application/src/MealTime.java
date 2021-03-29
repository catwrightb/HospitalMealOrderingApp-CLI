public enum MealTime {
    BREAKFAST ("Breakfast",1),
    LUNCH ("Lunch", 2),
    DINNER("Dinner", 3),
    ;

    private final String textRepresentation;


    MealTime(String textRepresentation, int order) {
        this.textRepresentation = textRepresentation;
    }


    @Override public String toString() {
        return textRepresentation;
    }
}
