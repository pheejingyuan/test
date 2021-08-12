package DAO;

import java.util.ArrayList;
import entities.*;
/**
 * The StudentDAO is in charge of the Student objects in the system.
 */
public class StudentDAO {
    private ArrayList<Student> studentList;

    /**
     * Constructs a StudentDAO object. with the initial list of Students (refer to
     * project writeup)
     *
     */
    public StudentDAO() {
        studentList = new ArrayList<>();
        Student s1 = new Student("raini", "Rainie Yang", 20);
        Student s2 = new Student("hyun", "Hyun Bin", 30);
        Student s3 = new Student("aaron", "Aaron Yang", 40);
        Student s4 = new Student("simi", "Shiela Sim", 50);
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
    }

    /**
     * Retrieves all the student objects. <br />
     * This method should only be used to list all students. If you need to retrieve
     * a specific student, use the retrieve(String) method.
     * 
     * @return the list of students
     */
    public ArrayList<Student> retrieveAll() {
        return studentList;
    }

    /**
     * Adds a specific Student into the system
     * 
     * @param username the username of the new user
     * @param name     the full name of the new user
     * @param eDollars the initial balance of the new user
     */
    public void add(String username, String name, int eDollars) {
        // Data Validation on the username
        if (retrieve(username) == null) {
            Student newStudent = new Student(username, name, eDollars);
            studentList.add(newStudent);
        } else {
            System.out.println("The username already exists, kindly check again.");
        }
    }

    /**
     * retrieves a student with the specified username
     * 
     * @param username the username of the student that you are searching for
     * @return the Student object if found. Otherwise, return null.
     */
    public Student retrieve(String username) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getUsername().equals(username)) {
                return studentList.get(i);
            }
        }
        return null;
    }
}
