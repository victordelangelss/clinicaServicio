package com.mx.Clinicas.openfeing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Clinicas.dto.PacienteModel;

@FeignClient(name="Pacientes", url="http://localhost:9001", path="/pacientes/")
public interface PacienteFeing {

	@GetMapping("buscarPacientesClinica/{numero}")
	public List<PacienteModel> buscarPacientes(@PathVariable("numero") long numero);
}
