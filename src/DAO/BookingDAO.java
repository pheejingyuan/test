package DAO;

import java.util.*;
import entities.*;
/**
 * The BookingDAO is in charge of the Bookings objects in the system.
 */
public class BookingDAO {
    // instance variables required
    private ArrayList<Booking> bookingList;

    /**
     * Constructs a BookingDAO object.
     * 
     * @param studentDAO  the StudentDAO object
     * @param facilityDAO the FacilityDAO object private Student student; private
     *                    Facility facility; private BrosDate startDate; private
     *                    BrosDate bookingDate; private int duration;
     */
    public BookingDAO(StudentDAO studentDAO, FacilityDAO facilityDAO) {
        bookingList = new ArrayList<>();

        BrosDate firstBookingDate = new BrosDate("28/09/2016 16:05");
        BrosDate firstStartDate = new BrosDate("14/11/2016 15:00");
        Booking b1 = new Booking(studentDAO.retrieve("raini"), facilityDAO.retrieve("F005"), firstBookingDate,
                firstStartDate, 2);
        Booking b2 = new Booking(studentDAO.retrieve("hyun"), facilityDAO.retrieve("F006"), firstBookingDate,
                firstStartDate, 2);

        BrosDate thirdBookingDate = new BrosDate("29/09/2016 16:06");
        BrosDate thirdStartDate = new BrosDate("15/11/2016 13:00");
        Booking b3 = new Booking(studentDAO.retrieve("aaron"), facilityDAO.retrieve("F003"), thirdBookingDate,
                thirdStartDate, 1);

        BrosDate fourthStartDate = new BrosDate("18/11/2016 18:00");
        Booking b4 = new Booking(studentDAO.retrieve("aaron"), facilityDAO.retrieve("F003"), thirdBookingDate,
                fourthStartDate, 2);

        BrosDate fifthBookingDate = new BrosDate("30/09/2016 17:00");
        BrosDate fifthStartDate = new BrosDate("19/11/2016 10:00");
        Booking b5 = new Booking(studentDAO.retrieve("simi"), facilityDAO.retrieve("F003"), fifthBookingDate,
                fifthStartDate, 3);

        bookingList.add(b1);
        bookingList.add(b2);
        bookingList.add(b3);
        bookingList.add(b4);
        bookingList.add(b5);

    }

    /**
     * Retrieves all the Bookings in the system
     * 
     * @return the list of bookings
     */
    public ArrayList<Booking> retrieveAll() {
        return bookingList;
    }

    /**
     * Retrieves the list of bookings that belongs to a specific student
     * 
     * @param username the student username
     * @return the list of student's booking or an empty ArrayList if this student
     *         has no booking
     */
    public ArrayList<Booking> retrieve(String username) {
        // TODO
        ArrayList<Booking> newArrayList = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++) {
            if (bookingList.get(i).getStudent().getUsername().equals(username)) {
                newArrayList.add(bookingList.get(i));
            }
        }
        return newArrayList;
    }

    /**
     * Checks that this newBooking timing does not overlap with an existing booking.
     * Then it adds the new booking to the list managed by this DAO.
     * 
     * @param newBooking the new Booking object
     * @return true if this booking is added successfully (i.e. there are no overlap
     *         with an existing booking, false otherwise.
     */
    public boolean add(Booking newBooking) {
        // i should check if the facility that im booking matches the one that the user
        // is trying to book.
        // if my starting time is inbetween my start and end then i will reject.
        // And if my ending time is also inbetween my start and end, i will also reject
        for (int i = 0; i < bookingList.size(); i++) {
            Booking currBooking = bookingList.get(i);
            String currFacilityId = currBooking.getFacility().getId();
            String newFacilityId = newBooking.getFacility().getId();
            if (currFacilityId.equals(newFacilityId)) {
                if (newBooking.overlaps(currBooking)) {
                    System.out.println("Booking overlaps with another booking. Please try again.");
                    return false;
                }
            }
        }
        Student tempStudent = newBooking.getStudent();
        int duration = newBooking.getDuration();
        if (tempStudent.deduct(duration * 2)) {
            bookingList.add(newBooking);
            return true;
        }
        return false;
    }
}
