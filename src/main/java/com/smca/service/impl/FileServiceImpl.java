package com.smca.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.smca.service.FileService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService{
	
	//Nombre de la carpeta donde vamos a almacenar los archivos
    //Se crea a nivel de raiz la carpeta
	
	//String directorio = "D:/Sunass/Equipos Remotos/github/equiposremotosapp-backend/ArchivosFichasDescriptivas/";
	//String directorio = "/opt/apache-tomcat-8.5.73/webapps/simcaArchivos/ArchivosFichasDescriptivas/desarrollo/";
	String directorio = "/opt/apache-tomcat-8.5.73/webapps/simcaArchivos/ArchivosFichasDescriptivas/desarrollo/";
	//String directorio = "/opt/apache-tomcat-8.5.73/webapps/simcaArchivos/ArchivosFichasDescriptivas/prd/";
	
    Path root;

    @Override
    public void init(String dinamico) {
        try {
        	root = Paths.get(directorio+dinamico);
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("No se puede inicializar la carpeta uploads");
        }
    }

    @Override
    public void save(MultipartFile file,String dinamico) {
        try {
            //copy (que queremos copiar, a donde queremos copiar)
        	root = Paths.get(directorio+dinamico);
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo. Error " + e.getMessage());
        }
    }
    
    @Override
    public String buscarArchivo(int id) {
    	File carpeta = new File(directorio+ "/" + id + "/");
    	String[] listado = carpeta.list();
    	if(listado.length==0)
    		return "";
    	return listado[0];
    }
    
    @Override
    public boolean eliminarArchivo(int id, String nombreArchivo) {
    	File carpeta = new File(directorio+ String.valueOf(id) + "/"+nombreArchivo);
    	return carpeta.delete();
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se puede leer el archivo ");
            }

        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll(){
        //Files.walk recorre nuestras carpetas (uploads) buscando los archivos
        // el 1 es la profundidad o nivel que queremos recorrer
        // :: Referencias a metodos
        // Relativize sirve para crear una ruta relativa entre la ruta dada y esta ruta
        try{
            return Files.walk(this.root,1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e){
            throw new RuntimeException("No se pueden cargar los archivos ");
        }
    }

    @Override
    public String deleteFile(String filename){
        try {
            Boolean delete = Files.deleteIfExists(this.root.resolve(filename));
                return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "Error Borrando ";
        }
    }


}
