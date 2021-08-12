package entities;
/**
 * An abstraction of a real-life student
 */
public class Student {
    private String username;
    private String name;
    private int eDollars;

    /**
     * Constructs a specified Student object
     * 
     * @param username the username of the Student
     * @param name     the full name of the Student
     * @param eDollars the initial balance of the student
     */
    public Student(String username, String name, int eDollars) {
        this.username = username;
        this.name = name;
        this.eDollars = eDollars;
    }

    /**
     * Gets the username
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the name
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the balance
     * 
     * @return the balance
     */
    public int getBalance() {
        return eDollars;
    }

    /**
     * reduce the eDollars balance by the specified amt
     * 
     * @param amt the amount of money(e$) to deduct from the balance
     */
    public boolean deduct(int amt) {
        int remaining = this.eDollars - amt;
        if(remaining < 0){
            System.out.println("You have E$0 left");
            System.out.println("You do not have enough E$ to book this facility");
            return false;
        }else{
            eDollars -= amt;
        }
        return true;

    }
}
