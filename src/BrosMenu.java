
import DAO.*;
import java.util.*;

/**
 * This is in charge of the user interface of the booking system. You should
 * write all the println and sc.nextXX() methods here (single responsibility).
 *
 */
public class BrosMenu {
    private StudentDAO studentDAO;
    private FacilityDAO facilityDAO;
    private BookingDAO bookingDAO;

    /**
     * Creates the BrosMenu object. The DAO objects (StudentDAO, FacilityDAO and
     * BookingDAO) are initialized here.
     */
    Scanner sc = new Scanner(System.in);

    public BrosMenu() {
        // TODO
        // initialize the DAO objects
        studentDAO = new StudentDAO();
        facilityDAO = new FacilityDAO();
        bookingDAO = new BookingDAO(studentDAO, facilityDAO);

    }

    /**
     * Displays the menu
     */
    public void displayMenu() {
        System.out.println();
        System.out.println("== BROS :: Main Menu ==");
        System.out.println("1. List all Students");
        System.out.println("2. List all Facilities");
        System.out.println("3. List all Bookings");
        System.out.println("4. List all Bookings by a student");
        System.out.println("5. Add a Student");
        System.out.println("6. Book a Facility");
        System.out.println("7. Quit Application");
        System.out.print("Enter your choice >");
    }

    /**
     * This is the method that kick starts the whole application. It runs in a loop
     * (until the user selects to quit). In each iteration, it displays the menu by
     * calling displayMenu(), prompts the user to select a choice, and then invoke
     * the processXX method to process the request.
     */
    public void execute() {
        // TODO
        // should continue to loop until the user chooses to quit

        displayMenu();
        int choice = sc.nextInt();
        sc.nextLine();

        while (choice > 0 && choice <= 6) {

            switch (choice) {
                case 1:
                    processListAllStudent();
                    break;
                case 2:
                    processListAllFacilities();
                    break;
                case 3:
                    processListAllBookings();
                    break;
                case 4:
                    processListAllBookingByAStudent();
                    break;
                case 5:
                    processAddAStudent();
                    break;
                case 6:
                    processBookAFacility();
                    break;
            }

            displayMenu();
            choice = sc.nextInt();
            sc.nextLine();
        }

        // accoutn for choice error.

    }

    /**
     * Process the request of listing all students in the system.
     */
    public void processListAllStudent() {
        System.out.println("== BROS :: List all Students ==");
        System.out.println();
        ArrayList<Student> arrayList = studentDAO.retrieveAll();
        if (arrayList.size() == 0) {
            System.out.println("There is no students");
            return;
        }

        // System.out.printf("S/N\tUsername\tName\t\tE$%n");
        // for (int i = 0; i < arrayList.size(); i++) {
        //     System.out.printf("|%d |\t|%s|\t|%-20s|\t|%d|%n", i + 1, arrayList.get(i).getUsername(),
        //             arrayList.get(i).getName(), arrayList.get(i).getBalance());
        // }
        System.out.printf("%-5s%-15s%-20s%-20s%n" , "S/N" ,"Username" , "Name" , "E$");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.printf("%-5d%-15s%-20s%-20d%n", i + 1, arrayList.get(i).getUsername(),
                    arrayList.get(i).getName(), arrayList.get(i).getBalance());
        }

    }

    /**
     * Process the request of listing all facilities in the system.
     */
    public void processListAllFacilities() {
        System.out.println("== BROS :: List all Facilities ==");
        System.out.println();

        ArrayList<Facility> facilityList = facilityDAO.retrieveAll();
        if (facilityList.size() == 0) {
            System.out.println("There is no facilities");
            return;
        }
        System.out.printf("S/N\tID\tDescription\tCapacity%n");
        for (int i = 0; i < facilityList.size(); i++) {
            System.out.printf("%d.\t%s\t%s\t%d%n", i + 1, facilityList.get(i).getId(),
                    facilityList.get(i).getDescription(), facilityList.get(i).getCapacity());
        }
    }

    /**
     * Process the request of listing all bookings in the system.
     */
    public void processListAllBookings() {
        System.out.println("== BROS :: List all Bookings ==");
        System.out.println();

        ArrayList<Booking> bookingList = bookingDAO.retrieveAll(); // consists of studentDAO and facilityDAO
        if (bookingList.size() == 0) {
            System.out.println("There is no bookings");
            return;
        }
        System.out.printf("Facility\tBooking DateTime\tStart DateTime\t\tDuration\tStudent%n");
        for (int i = 0; i < bookingList.size(); i++) {
            System.out.print(bookingList.get(i).getFacility().getId());
            System.out.printf("\t\t");
            System.out.print(bookingList.get(i).getStartDate().toString());
            System.out.printf("\t");
            System.out.print(bookingList.get(i).getBookingDate().toString());
            System.out.printf("\t");
            System.out.print(bookingList.get(i).getDuration());
            System.out.printf("\t\t");
            System.out.print(bookingList.get(i).getStudent().getName());
            System.out.printf("%n");
        }
    }

    /**
     * Process the request of adding a student in the system. 1. Prompts the user
     * for the username, full name and initial balance of the student. 2. Adds the
     * student object to the list managed by the StudentDAO.
     */
    public void processAddAStudent() {
        System.out.print("Enter a username: ");
        String userName = sc.nextLine();
        if (studentDAO.retrieve(userName) != null) {
            System.out.println("username is not available");
        } else {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter the E$: ");
            int eCredits = sc.nextInt();
            sc.nextLine();
            studentDAO.add(userName, name, eCredits);
            System.out.println(name + " was added");
        }
        // TODO
    }

    /**
     * Process the request of listing all the bookings by a specific user. 1.
     * Prompts the user for the username 2. Displays the list of bookings by the
     * student
     */
    public void processListAllBookingByAStudent() {
        System.out.print("Enter username:");
        String username = sc.nextLine();
        Student s1 = studentDAO.retrieve(username);
        if (s1 == null) {
            System.out.println("The username is invalid");
        } else {
            ArrayList<Booking> bookingList = bookingDAO.retrieve(username);// consists of studentDAO and facilityDAO
            if (bookingList.size() == 0) {
                System.out.println("This student has not made any booking");
                return;
            }
            System.out.printf("Facility\tBooking DateTime\tStart DateTime\t\tDuration%n");
            for (int i = 0; i < bookingList.size(); i++) {
                System.out.print(bookingList.get(i).getFacility().getDescription());
                System.out.printf("\t");
                System.out.print(bookingList.get(i).getStartDate().toString());
                System.out.printf("\t");
                System.out.print(bookingList.get(i).getBookingDate().toString());
                System.out.printf("\t");
                System.out.print(bookingList.get(i).getDuration());
                System.out.printf("\t\t%n");
            }
        }
    }

    /**
     * Process the request of booking a facility. 1. Prompt the user for username,
     * facility ID, start date and time, and duration 2. Perform the validations (in
     * the writeup). For example, insufficient balance etc 3. Adds the booking
     * object to the list managed by the BookingDAO
     */
    public void processBookAFacility() {
        // TODO
        /**
         * Enter username >raini Enter facility ID >F001 Enter start date (DD/MM/YYYY)
         * 22/11/2016 Enter start time (HH:00) >15:00 Enter number of hours >2
         */

        System.out.print("Enter username: ");
        String userName = sc.nextLine();
        while (studentDAO.retrieve(userName) == null) {
            System.out.println("username is not available");
            System.out.print("Enter username: ");
            userName = sc.nextLine();
        }
        System.out.print("Enter facility ID: ");
        String facilityID = sc.nextLine();
        while (facilityDAO.retrieve(facilityID) == null) {
            System.out.println("Facility is not available for booking. Please try again.");
            System.out.print("Enter facility ID: ");
            facilityID = sc.nextLine();
        }
        System.out.print("Enter start date (DD/MM/YYYY): ");
        String startDate = sc.nextLine();
        System.out.print("Enter start time: ");
        String startTime = sc.nextLine();
        System.out.print("Enter number of hours: ");
        int numHours = sc.nextInt();
        sc.nextLine();
        BrosDate dateTime = new BrosDate(startDate + " " + startTime);
        Booking newBooking = new Booking(studentDAO.retrieve(userName), facilityDAO.retrieve(facilityID), dateTime,
                dateTime, numHours);
        if (bookingDAO.add(newBooking)) {
            System.out.print("You have successfully booked this facility");
        }
    }
}
