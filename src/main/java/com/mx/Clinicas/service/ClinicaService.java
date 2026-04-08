package com.mx.Clinicas.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mx.Clinicas.dao.ClinicaDao;
import com.mx.Clinicas.dto.MedicoModel;
import com.mx.Clinicas.dto.PacienteModel;
import com.mx.Clinicas.dto.Respuesta;
import com.mx.Clinicas.entidad.Clinica;
import com.mx.Clinicas.openfeing.MedicoFeing;
import com.mx.Clinicas.openfeing.PacienteFeing;

@Service
public class ClinicaService {

	private ClinicaDao cliDao;
	private MedicoFeing medicoFeing;
	private PacienteFeing pacienteFeing;
	
	public ClinicaService(ClinicaDao clinicaDao, MedicoFeing medicoFeing, PacienteFeing pacienteFeing) {
		cliDao = clinicaDao;
		this.medicoFeing = medicoFeing;
		this.pacienteFeing = pacienteFeing;
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
	
	public Respuesta editar(Clinica clinica) {
		Respuesta rs = new Respuesta();
		if(cliDao.existsById(clinica.getNumero())) {
			for(Clinica c: cliDao.findAll()) {
				if(clinica.getNumero() != c.getNumero() 
						&& clinica.getNombre().equalsIgnoreCase(c.getNombre())) {
					rs.setMensaje("La clinica no se agrego porque su nombre ya existe");
					rs.setSuccess(false);
					rs.setObj(c);
					return rs;
					
				}
			}
			cliDao.save(clinica);
			rs.setMensaje("La clinica se ha editado");
			rs.setSuccess(true);
			rs.setObj(clinica);
			return rs;
		}
		rs.setMensaje("La clinica que tratas de editar no existe");
		rs.setSuccess(false);
		rs.setObj(clinica.getNumero());
		return rs;
	}
	
	public Respuesta eliminar(Clinica clinica) {
		Respuesta rs = new Respuesta();
		
		if(cliDao.existsById(clinica.getNumero())) {
			List<PacienteModel> pacientes = pacienteFeing.buscarPacientes(clinica.getNumero());
			if(pacientes != null) {
				rs.setMensaje("La clinica no se puede eliminar porque tiene pacientes");
				rs.setSuccess(false);
				rs.setObj(pacientes);
				return rs;
			}
			List<MedicoModel> medicos = medicoFeing.buscarMedicos(clinica.getNumero());
			if(medicos != null) {
				rs.setMensaje("La clinica no se puede eliminar porque tiene medicos");
				rs.setSuccess(false);
				rs.setObj(medicos);
				return rs;
			}
			clinica = cliDao.findById(clinica.getNumero()).orElse(null);
			rs.setObj(clinica);
			cliDao.delete(clinica);
			rs.setMensaje("La clinica ha sido eliminada");
			rs.setSuccess(true);
			return rs;
		}
		rs.setMensaje("La clinica que tratas de eliminar no existe");
		rs.setSuccess(false);
		rs.setObj(clinica.getNumero());
		return rs;
	}
	
	public ResponseEntity<?> buscar(long numero){
		Clinica clinica = cliDao.findById(numero).orElse(null);
		if(clinica == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clinica);
	}
	
	public ResponseEntity<?> buscarPacientes(long numero){
		List<PacienteModel> pacientes = pacienteFeing.buscarPacientes(numero);
		if(pacientes == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(pacientes);
	}
	
	public ResponseEntity<?> buscarMedicos(long numero){
		List<MedicoModel> medicos = medicoFeing.buscarMedicos(numero);
		if(medicos == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicos);
	}
	
	public ResponseEntity<?> buscarTodo(long numero){
		HashMap<String, Object> todo = new HashMap<>();
		List<PacienteModel> pacientes = pacienteFeing.buscarPacientes(numero);
		List<MedicoModel> medicos = medicoFeing.buscarMedicos(numero);
		todo.put("Medicos", medicos);
		todo.put("Pacientes", pacientes);
		return ResponseEntity.ok(todo);
	}
}
