package com.anahuac.calidad.DoublesDAO;

public interface AlumnoDAO {
	public Boolean addAlumno(Alumno a); 
	public Boolean deleteAlumno(Alumno a);
	public Boolean updateEmail(Alumno a);
	public Alumno searchAlumno(String id);
	

}
