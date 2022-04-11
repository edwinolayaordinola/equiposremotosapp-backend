package com.smca.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.smca.dto.ArchivoDto;
import com.smca.message.FileMessage;
import com.smca.model.FileModel;
import com.smca.service.FileService;

@RestController
public class FileController {
	
	@Autowired
    FileService fileService;
	
	ArchivoDto archivoDto;

    @PostMapping("/upload")
    public ResponseEntity<FileMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("id") String id){
        String message = "";
        try{
        	
        	fileService.init(id);
            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file->{
                fileService.save(file,id.toString());
                fileNames.add(file.getOriginalFilename());
            });

            message = "Se subieron los archivos correctamente " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch (Exception e){
            message = "Fallo al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }
    
    @PutMapping("/upload")
    public ResponseEntity<FileMessage> uploadUpdateFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("id") String id){
        String message = "";
        try{
            
            String nombreArchivo = fileService.buscarArchivo(Integer.parseInt(id));
            if(nombreArchivo.equals("")) {
            	Arrays.asList(files).stream().forEach(file->{
            		fileService.save(file,id.toString());
            	});
                message = "Se actualizaron los archivos correctamente ";
            } else {
            	if(fileService.eliminarArchivo(Integer.parseInt(id), nombreArchivo)) {
                	Arrays.asList(files).stream().forEach(file->{
                		fileService.save(file,id.toString());
                	});
                    message = "Se actualizaron los archivos correctamente ";
                }
            }
            message = "Se actualizaron los archivos correctamente ";
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        }catch (Exception e){
            message = "FalloS al subir los archivos";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }
    
    @GetMapping("/upload/{id}")
    public ResponseEntity<ArchivoDto> getNombreArchivo(@PathVariable("id") Integer id){
    	String nombreArchivo = fileService.buscarArchivo(id);  	
    	return new ResponseEntity<ArchivoDto>(new ArchivoDto(nombreArchivo),HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileModel>> getFiles(){
        List<FileModel> fileInfos = fileService.loadAll().map(path -> {
          String filename = path.getFileName().toString();
          String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile",
                  path.getFileName().toString()).build().toString();
          return new FileModel(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = fileService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\""+file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete/{filename:.+}")
    public ResponseEntity<FileMessage> deleteFile(@PathVariable String filename) {
        String message = "";
        try {
            message = fileService.deleteFile(filename);
            return ResponseEntity.status(HttpStatus.OK).body(new FileMessage(message));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessage(message));
        }
    }

}
