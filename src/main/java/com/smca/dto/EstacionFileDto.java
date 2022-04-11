package com.smca.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.smca.model.Estacion;

public class EstacionFileDto {
	
	private Estacion estacion;
	private MultipartFile file;
	
	public Estacion getEstacion() {
		return estacion;
	}
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
