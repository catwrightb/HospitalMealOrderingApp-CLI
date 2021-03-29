import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Patient extends Person implements Comparable<Patient>{
    private int roomNumber;
    private double totalCost;
    private int totalCalories;

    public Patient(String firstName, String lastName, Affiliation affiliation, int roomNumber, double totalCost, int totalCalories) {
        super(firstName, lastName, affiliation);
        this.roomNumber = roomNumber;
        this.totalCost = totalCost;
        this.totalCalories = totalCalories;
    }

    public Patient(String firstName, int roomNumber) {
        super(firstName);
        this.roomNumber = roomNumber;

    }

    public Patient(int roomNumber, double totalCost, int totalCalories) {
        this.roomNumber = roomNumber;
        this.totalCost = totalCost;
        this.totalCalories = totalCalories;
    }


    public Patient(String firstName, String lastName) {
        super(firstName, lastName, Affiliation.PATIENT);
    }

    public Patient(String firstName, String lastName, int roomNumber) {
        super(firstName, lastName, Affiliation.PATIENT);
        this.roomNumber = roomNumber;
    }

    public Patient(String firstName, String lastName, Affiliation affiliation, int roomNumber) {
        super(firstName, lastName, Affiliation.PATIENT);
        this.roomNumber = roomNumber;
    }

    public Patient() {

    }

    public double getTotalCost() {
        return totalCost;
    }

    //reads txt file patientCal&Cost line by line and saves lines into string array seperate by commas
    public double setTotalCost(Patient patient, int timeLine) {
        String lastName = patient.getLastName();
        double total =0;
        String date= "";
        PlacedOrder test = new PlacedOrder();
        date = test.getNextDay();

        try(
            BufferedReader in = new BufferedReader(new FileReader("patientCal&Cost.txt"))) {
            String str;

            switch (timeLine){
                case 1:
                    while ((str = in.readLine()) != null) {
                        String[] ar=str.split(", ");
                        for (int i = 0; i < ar.length; i++) {
                            if(i == 0){
                                String checkedLastName = ar[i];

                                if (checkedLastName.equals(lastName)){
                                    for (int j = 0; j < ar.length; j++) {
                                        if (j == 3){
                                            String cost = ar[j];
                                            double convertedNumber = Double.parseDouble(cost);
                                            total += convertedNumber;
                                        }
                                    }
                                }
                            }
                        }
                    }
                case 2:
                    while ((str = in.readLine()) != null) {
                        String[] ar=str.split(", ");
                        for (int i = 0; i < ar.length; i++) {
                            if(i == 0){
                                String checkedLastName = ar[i];

                                if (checkedLastName.equals(lastName) && str.contains(date)){
                                    for (int j = 0; j < ar.length; j++) {
                                        if (j == 3){
                                            String cost = ar[j];
                                            double convertedNumber = Double.parseDouble(cost);
                                            total += convertedNumber;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }


        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

       return total;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int setTotalCalories(Patient patient) {
        String lastName = patient.getLastName();
        int total =0;
        String date= "";
        PlacedOrder test = new PlacedOrder();
        date = test.getNextDay();

        try(
                BufferedReader in = new BufferedReader(new FileReader("patientCal&Cost.txt"))) {
            String str;

            while ((str = in.readLine()) != null) {
                String[] ar=str.split(", ");
                for (int i = 0; i < ar.length; i++) {
                    if(i == 0){
                        String checkedLastName = ar[i];

                        if (checkedLastName.equals(lastName) && str.contains(date)){
                            for (int j = 0; j < ar.length; j++) {
                                if (j == 2){
                                    String cost = ar[j];
                                    int convertedNumber = Integer.parseInt(cost);
                                    total += convertedNumber;
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("File Read Error");
        }

        return total;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //instantiates the patient object
    public ArrayList<Patient> instantiatePatients(){
        ArrayList<Patient> patientList = new ArrayList<>();

        patientList.add(new Patient("Sam", "Bridges", Affiliation.PATIENT, 101));
        patientList.add(new Patient("Randle", "McMurphy", Affiliation.PATIENT, 102));
        patientList.add(new Patient("Hideo", "Kojima", Affiliation.PATIENT, 103));
        patientList.add(new Patient("Jonas", "Kahnwald", Affiliation.PATIENT, 104));
        patientList.add(new Patient("Gordon", "Freeman", Affiliation.PATIENT, 105));
        patientList.add(new Patient("Tom", "Nook", Affiliation.PATIENT, 106));

        return patientList;
    }


    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() +", room #"+getRoomNumber();
    }

    //Orders ArrayList by ascending first name
    @Override
    public int compareTo(Patient o) {
        return this.getFirstName().compareTo(((Patient) o).getFirstName());
    }

}
