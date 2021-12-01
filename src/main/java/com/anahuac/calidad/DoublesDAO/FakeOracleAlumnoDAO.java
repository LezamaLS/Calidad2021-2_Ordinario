package com.anahuac.calidad.DoublesDAO;

public class FakeOracleAlumnoDAO implements AlumnoDAO {

	@Override
	public Boolean addAlumno(Alumno a) {
		return false;
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean deleteAlumno(Alumno a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean updateEmail(Alumno a) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public Alumno searchAlumno(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
