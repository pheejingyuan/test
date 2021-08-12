package DAO;

import java.util.ArrayList;
import entities.*;
/**
 * The FacilityDAO is in charge of the Facility objects in the system.
 */
public class FacilityDAO {
    private ArrayList<Facility> facilityList;

    /**
     * Constructs a FacilityDAO with the initial list of Facility (refer to project
     * writeup)
     */
    public FacilityDAO() {
        // TODO
        // initialize the sample data
        facilityList = new ArrayList<>();
        Facility F1 = new Facility("F001", "Room 2-1", 4);
        Facility F2 = new Facility("F002", "Room 2-2", 6);
        Facility F3 = new Facility("F003", "Room 2-3", 8);
        Facility F4 = new Facility("F004", "Room 2-4", 10);
        Facility F5 = new Facility("F005", "Room 2-5", 12);
        Facility F6 = new Facility("F006", "Room 2-6", 14);
        Facility F7 = new Facility("F007", "Room 2-7", 16);
        facilityList.add(F1);
        facilityList.add(F2);
        facilityList.add(F3);
        facilityList.add(F4);
        facilityList.add(F5);
        facilityList.add(F6);
        facilityList.add(F7);
    }

    /**
     * Retrieves all the Facility objects currently available in the System. This
     * should only be used for the "List all facility" functionality.
     * 
     * @return list of all Facility objects
     */
    public ArrayList<Facility> retrieveAll() {
        return facilityList;
    }
    /**
     * Retrieves a facility with the specified facilityID
     * 
     * @param facilityId the unique identifier of the facility
     * @return the Facility object, returns null if the facility does not exist.
     */
    public Facility retrieve(String facilityID) {
        // TODO
        for (int i = 0; i < facilityList.size(); i++) {
            if(facilityList.get(i).getId().equals(facilityID)){
                return facilityList.get(i);
            }
        }
        return null;
    }


}
