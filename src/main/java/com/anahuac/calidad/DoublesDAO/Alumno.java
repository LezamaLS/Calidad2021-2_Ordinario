package com.anahuac.calidad.DoublesDAO;

public class Alumno {
	// Attributes
	private String nombre;
	private String id; 
	private int edad; 
	private String email;
	
	public Alumno() {
		
	}
	
	public Alumno(String id, String nombre, String email, int edad) {
		this.nombre = nombre; 
		this.edad = edad; 
		this.id = id; 
		this.email = email;
		
	}
	
	// Getters
	public String getNombre() {
		return nombre;
	}
	public String getId() {
		return id;
	}
	public int getEdad() {
		return edad;
	}
	public String getEmail() {
		return email;
	}
	
	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
}
