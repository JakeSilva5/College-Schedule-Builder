/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jakesilva
 */
public class StudentSchedule {
    private String courseCode;
    private String status;

    public StudentSchedule(String courseCode, String status) {
        this.courseCode = courseCode;
        this.status = status;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStatus() {
        return status;
    }
    
}
