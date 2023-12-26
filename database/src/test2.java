/*
 *
 The purpose of Exercise 2 is to show how to execute a SELECT/INSERT statement on a table located at a local database server. 
    1) Download, install, configure and run MySQL database server locally; 
    2) Learn to use MySQL workbench to run SQL statements over a local MySQL server;
    3) Understand how two methods, writeMetaData and writeResultSet, work to display the result from a SELECT statement. 
 *
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class test2  {
  private static Connection connection = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;

 public static void connect() throws SQLException
 {
          connection = DriverManager
          .getConnection("jdbc:mysql://127.0.0.1:3306/testdb?"
              + "useSSL=false&user=root&password=test1234"); 
 }

 public static void close() throws SQLException
 {
         connection.close();
 }

 public static void insertStudent(int id, String name, String address, String status) throws SQLException
 {
     String stmt = "INSERT INTO Student VALUES(?, ? , ?, ?)";
     preparedStatement = connection.prepareStatement(stmt);
     preparedStatement.setInt(1, id);
     preparedStatement.setString(2, name);
     preparedStatement.setString(3, address);
     preparedStatement.setString(4, status);     
     preparedStatement.executeUpdate();
 }


 public static void updateAddressById(int id, String newName) throws SQLException
 { 
     String stmt = "UPDATE Student SET name = ? WHERE id = ?";
     preparedStatement = connection.prepareStatement(stmt);
     preparedStatement.setString(1, newName);
     preparedStatement.setInt(2, id);
     preparedStatement.executeUpdate();
 }

 public static void deleteStudentByName(String name) throws SQLException
 {
     String stmt = "DELETE FROM Student WHERE name = ?";
     preparedStatement = connection.prepareStatement(stmt);
     preparedStatement.setString(1, name);
     preparedStatement.executeUpdate();
 }

 
 public static ResultSet selectStudentByName(String name) throws SQLException
 {
     String stmt = "SELECT * FROM Student WHERE name = ?";
     preparedStatement = connection.prepareStatement(stmt);
     preparedStatement.setString(1, name);
     ResultSet r = preparedStatement.executeQuery();
     return r;
 }

 public static ResultSet selectAllStudents() throws SQLException
 {
     statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery("select * from student");
     return resultSet;
 }

 public static void main(String[] args) {
    try {
       connect();

       insertStudent(5, "Frank", "123 Success Road", "Junior");
       deleteStudentByName("Frank");
       updateAddressById(0, "123 New Address");

       // ResultSet r = selectAllStudents();
       ResultSet r = selectStudentByName("John Smith");
       printResultSet(r);

       close();
    }catch(SQLException e){
        System.out.println("Error:" + e);
    }
     
 }

 
  private static void printResultSet(ResultSet resultSet) throws SQLException {
   while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      String address = resultSet.getString("address");
      String status = resultSet.getString("status");
    
      System.out.println("id: " + id);
      System.out.println("name: " + name);
      System.out.println("address: " + address);
      System.out.println("status: " + status);
      System.out.println("");
    }
  }
} 
