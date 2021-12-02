//Entrega Final Miguel Lezama
//Calidad y Pruebas de Software

//TEST Integracion con DBUnit

package com.anahuac.calidad.dbunit;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.anahuac.calidad.mockito.Alumno;

public class DBUnit {

	
	//Se tuvo que cambiar a 127.0.0.1:3306 ya que no se esta usando en docker dentro de travis
	
	public Connection getConnectionMySQL() {

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("insert INTO Alumnia(id,nombre,email,edad) values(?,?,?,?)");

			preparedStatement.setString(1, a.getId());
			preparedStatement.setString(2, a.getNombre());
			preparedStatement.setString(3, a.getEmail());
			preparedStatement.setInt(4, a.getEdad());

			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	
	
	
	//DELETE
	
	public boolean deleteAlumno(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;

		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Delete from Alumnia WHERE id = ?");

			preparedStatement.setString(1, a.getId());

			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}

			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	
	
	
	
	//UPDATE
	
	public boolean updateEmail(Alumno a) {
		Connection connection = getConnectionMySQL();
		boolean result = false;

		try {
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("UPDATE Alumnia SET email = ? WHERE id = ?");

			preparedStatement.setString(1, a.getEmail());
			preparedStatement.setString(2, a.getId());

			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}

			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
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
			preparedStatement = connection.prepareStatement("SELECT * from Alumnia WHERE id = ?");

			preparedStatement.setString(1, id);
			rs = preparedStatement.executeQuery();

			rs.next();

			String retrivedId = rs.getString(1);
			String retrivedName = rs.getString(2);
			String retrivedEmail = rs.getString(3);
			int retrivedAge = rs.getInt(4);

			retrieved = new Alumno(retrivedId, retrivedName, retrivedEmail, retrivedAge);

			rs.close();
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return retrieved;
	}

}
