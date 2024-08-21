/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acv
 */
public class SemesterQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addSemester;
    private static PreparedStatement getSemesterList;
    private static ResultSet resultSet;
    
    public static boolean addStudent(String studentID, String firstName, String lastName) {
    Connection conn = DBConnection.getConnection();
    try {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO students (studentID, firstName, lastName) VALUES (?, ?, ?)");
        ps.setString(1, studentID);
        ps.setString(2, firstName);
        ps.setString(3, lastName);
        int result = ps.executeUpdate();
        return result > 0;
    } catch (SQLException e) {
        System.out.println("SQL Exception: " + e.getMessage());
        return false;
    }
}

    
    
    
    public static void addSemester(String name)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSemester = connection.prepareStatement("insert into app.semester (semester) values (?)");
            addSemester.setString(1, name);
            addSemester.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getSemesterList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<String>();
        try
        {
            getSemesterList = connection.prepareStatement("select semester from app.semester order by semester");
            resultSet = getSemesterList.executeQuery();
            
            while(resultSet.next())
            {
                semester.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return semester;
        
    }
    public static List<ClassDescription> getAllClassDescriptions(String semester) {
        List<ClassDescription> classDescriptions = new ArrayList<>();
        connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT app.class.courseCode, app.course.description, app.class.seats " +
                "FROM app.class JOIN app.course ON app.class.courseCode = app.course.courseCode " +
                "WHERE app.class.semester = ? " +
                "ORDER BY app.class.courseCode"
            );
            statement.setString(1, semester);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String description = resultSet.getString("description");
                int seats = resultSet.getInt("seats");
                classDescriptions.add(new ClassDescription(courseCode, description, seats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classDescriptions;
    }
    
    public static List<StudentSchedule> getStudentSchedule(String studentID, String semester) {
    List<StudentSchedule> schedules = new ArrayList<>();
    connection = DBConnection.getConnection();
    try {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT courseCode, status FROM schedule WHERE studentID = ? AND semester = ?");
        ps.setString(1, studentID);
        ps.setString(2, semester);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            schedules.add(new StudentSchedule(rs.getString("courseCode"), rs.getString("status")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return schedules;
}
    
    public static void addCourse(String courseCode, String description) {
        connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (courseCode, description) VALUES (?, ?)");
            preparedStatement.setString(1, courseCode);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    public static List<String> getAllCourseCodes() {
    List<String> courseCodes = new ArrayList<>();
    connection = DBConnection.getConnection();
    try {
        PreparedStatement ps = connection.prepareStatement("SELECT courseCode FROM courses ORDER BY courseCode");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            courseCodes.add(rs.getString("courseCode"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return courseCodes;
}

}

    

