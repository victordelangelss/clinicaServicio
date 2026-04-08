package com.mx.Clinicas.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.Clinicas.dao.ClinicaDao;
import com.mx.Clinicas.dto.Respuesta;
import com.mx.Clinicas.entidad.Clinica;

@Service
public class ClinicaService {

	private ClinicaDao cliDao;
	
	public ClinicaService(ClinicaDao clinicaDao) {
		cliDao = clinicaDao;
	}
	
	public ResponseEntity<?> mostrar(){
		if(cliDao.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cliDao.findAll());
	}
	
	public Respuesta guradar(Clinica clinica) {
		Respuesta rs = new Respuesta();
//		if(cliDao.existsById(clinica.getNumero())) {
//			rs.setMensaje("La clinica no se agrego porque ya existe su numero");
//			rs.setSuccess(false);
//			rs.setObj(clinica.getNumero());
//			return rs;
//		}
		for(Clinica c: cliDao.findAll()) {
			if(clinica.getNombre().equalsIgnoreCase(c.getNombre())) {
				rs.setMensaje("La clinica no se agrego porque su nombre ya existe");
				rs.setSuccess(false);
				rs.setObj(c);
				return rs;
			}
		}
		cliDao.save(clinica);
		rs.setMensaje("La clinica ha sido agregada");
		rs.setSuccess(true);
		rs.setObj(clinica);
		return rs;
	}
}
