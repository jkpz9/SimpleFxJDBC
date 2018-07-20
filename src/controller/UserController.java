package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import dao.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Student;

public class UserController {
	
	public boolean insert(Student s)
	{
		boolean success = false;
		String sql = "INSERT INTO students(name,age,year) VALUES(?,?,?)";
		try(Connection con = (Connection) DBConnection.getConnect();
			PreparedStatement pstm = con.prepareStatement(sql)
		) {
			pstm.setString(1, s.getName());
			pstm.setInt(2, s.getAge());
			pstm.setInt(3, s.getYear());
			
			pstm.execute();
			
			success = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public boolean delete(int Id)
	{
		boolean success = false;
		String sql = "delete from students where id = ?";
		try(Connection con = (Connection) DBConnection.getConnect();
			PreparedStatement pstm = con.prepareStatement(sql)
		) {
			pstm.setInt(1, Id);
			
			pstm.execute();
			
			success = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public Student fetchSingle(int id) throws ClassNotFoundException, SQLException
	{
		Student s = null;
		String query = "SELECT * FROM students WHERE  id = ?";
		try(Connection con = (Connection) DBConnection.getConnect();
				PreparedStatement pstm = con.prepareStatement(query);
			) {
				pstm.setInt(1, id);
				ResultSet result = pstm.executeQuery();
				// Move cursor to the beginning, before the first row.
			    // cursor position is 0.
				result.beforeFirst();
				while(result.next())
				{
					s = new Student(result.getInt("id"),
							 result.getString("name"),
							 result.getInt("age"),
							 result.getInt("year"));
				}
				
			} 
		return s;
	}
	
	public boolean update(Student s)
	{
		boolean success = false;
		String sql = "UPDATE students SET age=?,year=? WHERE id = ?";
		try(Connection con = (Connection) DBConnection.getConnect();
			PreparedStatement pstm = con.prepareStatement(sql)
		) {
			pstm.setInt(1, s.getAge());
			pstm.setInt(2, s.getYear());
			pstm.setInt(3, s.getId());
			
			pstm.execute();
			
			success = true;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public ObservableList<Student> fetchAll() throws ClassNotFoundException, SQLException
	{
		ObservableList<Student> students = FXCollections.observableArrayList();
		String query = "SELECT * FROM students";
		
		try(Connection con = (Connection) DBConnection.getConnect();
				Statement stm = con.createStatement();
			) {
				ResultSet result = stm.executeQuery(query);
				// Move cursor to the beginning, before the first row.
			    // cursor position is 0.
				result.beforeFirst();
				while(result.next())
				{
					students.add(new Student(result.getInt("id"),
											 result.getString("name"),
											 result.getInt("age"),
											 result.getInt("year")));
				}
				
			} 
		return students;
	}
	
	public ObservableList<Student> search(String name) throws ClassNotFoundException, SQLException
	{
		ObservableList<Student> students = FXCollections.observableArrayList();
		String query = "SELECT * FROM students WHERE name LIKE ?";
		
		try(Connection con = (Connection) DBConnection.getConnect();
				PreparedStatement pstm = con.prepareStatement(query);
			) {
				pstm.setString(1, "%" + name);
				ResultSet result = pstm.executeQuery();
				// Move cursor to the beginning, before the first row.
			    // cursor position is 0.
				result.beforeFirst();
				while(result.next())
				{
					students.add(new Student(result.getInt("id"),
											 result.getString("name"),
											 result.getInt("age"),
											 result.getInt("year")));
				}
				
			} 
		return students;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		
		// INSERT
//		if (new UserController().insert(new Student("Scarlett", 21, 3))) {
//			System.out.println("INSERTED");
//		}
//		else {
//			System.out.println("FAILED");
//		}
		
		// DELETE
//		if (new UserController().delete(1)) {
//			System.out.println("DELETED");
//		}
//		else {
//			System.out.println("FAILED");
//		}
		
//		List<Student> students = new UserController().fetchAll();
//		for(int i=0;i<students.size(); i++)
//			System.out.println(students.get(i));
		int id = 5;
		Student s = new UserController().fetchSingle(id);
		if (s != null)
		{
			System.out.println(s.toString());
		}
		else {
			System.out.println("Student with id="+id + " doesn't exists");
		}
	}

}
