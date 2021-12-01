//Entrega Final Miguel Lezama
//Calidad y Pruebas de Software

//TEST Integracion con DBUnit

package com.anahuac.calidad.dbunit;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.anahuac.calidad.DoublesDAO.Alumno;

public class AlumnoDAOMySQL {

	
	//Conection 33060
	
	public Connection getConnectionMySQL() {

		Connection con = null;
		try {
			// Establish the driver connector
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Set the URI for connecting the MySql database
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pruebas_db", "root", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	
	
	
	
	//CREATE
	
	public boolean addAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;
		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection
					.prepareStatement("insert INTO alumnos_tbl(id,nombre,email,edad) values(?,?,?,?)");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, a.getId());
			preparedStatement.setString(2, a.getNombre());
			preparedStatement.setString(3, a.getEmail());
			preparedStatement.setInt(4, a.getEdad());

			// Return the result of connection nad statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Se ha añadido un alumno");
			System.out.println("-> Return: " + result + "\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}
	
	
	
	
	
	//DELETE
	
	public boolean deleteAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Delete from alumnos_tbl WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, a.getId());

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Se ha eliminado un Alumno");
			System.out.println("-> Return: " + result + "\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}

	
	
	
	
	//UPDATE
	
	public boolean updateEmail(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("UPDATE alumnos_tbl SET email = ? WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, a.getEmail());
			preparedStatement.setString(2, a.getId());

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Correo de alumno con ID: " + a.getId() + " actualizado");
			System.out.println("-> Return: " + result + "\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}

	
	
	
	
	//RETRIEVE
	
	public Alumno searchAlumno(String id) {
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		ResultSet rs;
		boolean result = false;

		Alumno retrieved = null;

		try {
			// Declare statement query to run
			preparedStatement = connection.prepareStatement("SELECT * from alumnos_tbl WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();


			// Obtain the pointer to the data in generated table
			rs.next();


			String retrivedId = rs.getString(1);
			String retrivedName = rs.getString(2);
			String retrivedEmail = rs.getString(3);
			int retrivedAge = rs.getInt(4);


			retrieved = new Alumno(retrivedId, retrivedName, retrivedEmail, retrivedAge);


			// Return the values of the search
			System.out.println("\n");
			System.out.println("******Alumno******");
			System.out.println("ID: " + retrieved.getId());
			System.out.println("Name: " + retrieved.getNombre());
			System.out.println("Age: " + retrieved.getEdad());
			System.out.println("Email: " + retrieved.getEmail() + "\n");
			// Close connection with the database
			connection.close();
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return retrieved;
	}

}
