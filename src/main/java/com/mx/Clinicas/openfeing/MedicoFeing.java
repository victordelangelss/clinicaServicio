package com.mx.Clinicas.openfeing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Clinicas.dto.MedicoModel;

@FeignClient(name="Medicos", url="http://localhost:9002", path="/medicos/")
public interface MedicoFeing {

	@GetMapping("buscarMedicosClinica/{numero}")
	public List<MedicoModel> buscarMedicos(@PathVariable(name="numero")long numero);
}
