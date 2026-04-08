package com.mx.Clinicas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoModel {

	private int numero;
	private String nombre;
	private String apellido;
	private String genero;
	private double sueldo;
	private String especialidad;
	private String area;
	private long numeroClinica;
}
