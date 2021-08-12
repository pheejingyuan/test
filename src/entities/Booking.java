package entities;
/**
 * An abstraction of a Booking object made by a student
 */
public class Booking {
    private Student student;
    private Facility facility;
    private BrosDate startDate;
    private BrosDate bookingDate;
    private int duration;

    /**
     * Constructs a specific Booking object
     * 
     * @param student   the student making the booking
     * @param facility  the facility being booked
     * @param startDate the booking date of the facility
     * @param duration  the duration of the booking
     */
    public Booking(Student student, Facility facility, BrosDate startDate, int duration) {
        this.student = student;
        this.facility = facility;
        this.startDate = startDate;
        this.duration = duration;
        this.bookingDate = new BrosDate();
    }

    /**
     * Constructs a specific Booking object. This should only be used by the
     * BookingDAO to create the initial list of bookings
     * 
     * @param student     the student making the booking
     * @param facility    the facility being booked
     * @param bookingDate the date when the booking is made by the student.
     * @param startDate   the booking date of the facility
     * @param duration    the duration of the booking
     */
    public Booking(Student student, Facility facility, BrosDate bookingDate, BrosDate startDate, int duration) {
        this.student = student;
        this.facility = facility;
        this.startDate = startDate;
        this.bookingDate = bookingDate;
        this.duration = duration;
    }

    
    /** 
     * @return String
     */
    public String toString() {
        return student.getName() + " " + facility.getId() +" "+ bookingDate.toString() + " " + startDate.toString() + " " + duration;
    }

    /**
     * Gets the duration of this booking
     * 
     * @return the booking's duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the student who made the booking
     * 
     * @return the student object
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Gets the facility being booked
     * 
     * @return the facility object
     */
    public Facility getFacility() {
        return facility;
    }

    /**
     * Gets the start date of the booking
     * 
     * @return the booking start date
     */
    public BrosDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the booking
     * 
     * @return the booking end date
     */
    public BrosDate getEndDate() {
        return startDate.computeDate(getDuration());
    }

    /**
     * gets the price of this booking
     * 
     * @return the price that the booking costs (taking into consideration the rate
     *         of the facility and the duration)
     */
    public int getPrice() {
        // retrieve the facility rate from facility class
        // retrieve the duration from this class
        return (facility.getPrice() * getDuration());
    }

    /**
     * Gets the booking date
     * 
     * @return the date where this booking is made
     */
    public BrosDate getBookingDate() {
        return bookingDate;
    }

    /**
     * checks whether this booking overlaps with another booking
     * 
     * @param another the other booking object
     * @return true if this booking overlaps the other. Otherwise, return false
     */
    public boolean overlaps(Booking another) {
        // Pseudocode
        /**
         * Firstly, get start date of another.
         * Get number of hours for "another" booking
         * call the computeDate api to retrieve the end date of "another"
         * Now you start + end of another
         * 
         * perform validation checks
         * 1) If this.startDate is before endDate of another and after startDate of another, return false  
         * 2) 
         * 3) return true;
         */

        //inside DB
        BrosDate oldStartDate = another.getStartDate();
        int duration = another.getDuration();
        BrosDate oldEndDate = oldStartDate.computeDate(duration);

        //user input
        BrosDate userStartDate = this.getStartDate();
        int userDuration = this.getDuration();
        BrosDate userEndDate = userStartDate.computeDate(userDuration);

        if(userStartDate.after(oldStartDate) && userStartDate.before(oldEndDate)){
            return true;
            // System.out.print("The current date is after another date");
        }else if(userEndDate.after(oldStartDate) && userEndDate.before(oldEndDate)){
            return true;
            // System.out.print("The current date is after another date");s
        }
        return false;
    }
}
