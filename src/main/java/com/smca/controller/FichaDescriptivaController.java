package com.smca.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.dto.FichaDescriptiva;
import com.smca.service.FileService;

@RestController
@RequestMapping("/fichadescriptiva")
public class FichaDescriptivaController {
	
	@Autowired
    FileService fileService;
	
	//String directorio = "D:/Sunass/Equipos Remotos/github/equiposremotosapp-backend/ArchivosFichasDescriptivas/";
	String directorio = "/opt/apache-tomcat-8.5.73/webapps/simcaArchivos/ArchivosFichasDescriptivas/";
	
	@GetMapping("/{idEstacion}")
	public ResponseEntity<FichaDescriptiva> listarPorId(@PathVariable("idEstacion") Integer idEstacion){
		String nombreArchivo = fileService.buscarArchivo(idEstacion);
		String ruta = directorio+idEstacion.toString()+"/"+nombreArchivo;
		FichaDescriptiva fd = new FichaDescriptiva(ruta);
		return new ResponseEntity<FichaDescriptiva>(fd,HttpStatus.OK);
		/*abrirarchivo(idEstacion,nombreArchivo);*/
	}
	
	public void abrirarchivo(Integer idEstacion,String archivo){

	     try {
            File objetofile = new File (directorio+idEstacion+"/"+archivo); 
            Desktop.getDesktop().open(objetofile);

	     }catch (IOException ex) {
	    	 System.out.println(ex);
	     }

	}
}
