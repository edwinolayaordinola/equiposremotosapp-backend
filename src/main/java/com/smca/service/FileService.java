package com.smca.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileService {

    /*
    Metodo para crear la carpeta donde vamos a guardar los archivos
     */
    public void init(String carpeta);

    /*
    Metodo para guardar los archivos
     */
    public void save(MultipartFile file, String carpeta);

    /*
    Metodo para cargar un archivo
     */
    public Resource load(String filename);

    /*
    Metodo para borrar todos los archivos cada vez que se inicie el servidor
     */
    public void deleteAll();

    /*
    Metodo para Cargar todos los archivos
     */
    public Stream<Path> loadAll();

    /*
    Metodo para Borrar un archivo
     */
    public String deleteFile(String filename);
    
    public String buscarArchivo(int id);
    
    public boolean eliminarArchivo(int id,String nombreArchivo);
}

