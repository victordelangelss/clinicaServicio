package com.mx.Clinicas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Clinicas.dto.Respuesta;
import com.mx.Clinicas.entidad.Clinica;
import com.mx.Clinicas.service.ClinicaService;

@RestController
@RequestMapping(name="clinicas/")
@CrossOrigin
public class ClinicaController {

	@Autowired
	ClinicaService service;
	
	@GetMapping("listar")
	public ResponseEntity<?> listar(){
		return service.mostrar();
	}
	
	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Clinica clinica) {
		return service.guradar(clinica);
	}
	
	@PostMapping("Editar")
	public Respuesta editar(@RequestBody Clinica clinica) {
		return service.editar(clinica);
	}
	
	@PostMapping("eliminar")
	public Respuesta eliminar(@RequestBody Clinica clinica) {
		return service.eliminar(clinica);
	}
	
	@GetMapping("buscar/{numero}")
	public ResponseEntity<?> buscar(@PathVariable("numero") long numero){
		return service.buscar(numero);
	}
	
	@GetMapping("buscarPacientes/{numero}")
	public ResponseEntity<?> buscarPacientes(@PathVariable("numero") long numero){
		return service.buscarPacientes(numero);
	}
	
	@GetMapping("buscarMedicos/{numero}")
	public ResponseEntity<?> buscarMedicos(@PathVariable("numero") long numero){
		return service.buscarMedicos(numero);
	}
	
	@GetMapping("buscarTodo")
	public ResponseEntity<?> buscarTodo(@PathVariable("numero") long numero){
		return service.buscarTodo(numero);
	}
}
