//Entrega Final Miguel Lezama
//Calidad y Pruebas de Software

//AlumnoDAO.java

package com.anahuac.calidad.mockito;

public interface AlumnoDAO {
	public Boolean addAlumno(Alumno a); 
	public Boolean deleteAlumno(Alumno a);
	public Boolean updateEmail(Alumno a);
	public Alumno searchAlumno(String id);
	

}
