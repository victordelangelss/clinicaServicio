package com.mx.Clinicas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteModel {

	private String curp;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private double peso;
	private double altura;
	private String estadoCivil;
	private int numeroMedico;
	private long numeroClinica;
}
