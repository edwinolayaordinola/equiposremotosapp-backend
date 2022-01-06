package com.smca.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
/*import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smca.dto.EstacionIndicadorDto;
import com.smca.model.Estacion;
import com.smca.model.Indicador;
import com.smca.service.IEstacionService;
import com.smca.service.IIndicadorService;

@RestController
@RequestMapping("/estaciones")
public class EstacionController {
	
	
	@Autowired
	private IEstacionService estacionService;
	
	@Autowired
	private IIndicadorService indicadorService;
	
	@Autowired
    RestTemplate restTemplate;
	
	Estacion estacion;
	Indicador indicador;
	EstacionIndicadorDto estacionIndicadorDto;
	List<EstacionIndicadorDto> listaEstacionIndicadorDto;
	List<EstacionIndicadorDto> listaeidto;
	
	String nombreEstacion;
	Float cloro, ph, temperatura;
	
	LocalDateTime fecharegistro;
	
	@GetMapping
	public ResponseEntity<List<EstacionIndicadorDto>> listar(){
		
		return getListaEstacionIndicador();
	}
	
	@PostMapping(path="/indicadores",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<List<EstacionIndicadorDto>> listarIndicadorespost(@RequestParam Map<String, String> map) throws UnsupportedEncodingException, MalformedURLException{
		
		JSONObject json =  new JSONObject(map);
		if(addEstacionBd(json)>0) {
			List<EstacionIndicadorDto> listaeidto  = new ArrayList<>(10);
			estacion = new Estacion();
			indicador = new Indicador();
			
			indicadorService.getRecientes().forEach(irdto->{
				estacion = estacionService.listarPorId(irdto.getIdestacion());
				indicador = indicadorService.get(irdto.getIdestacion(), irdto.getFecharegistro());
				estacionIndicadorDto = new EstacionIndicadorDto();
				estacionIndicadorDto.setCloro(indicador.getCloro());
				estacionIndicadorDto.setCoordx(estacion.getCoordx());
				estacionIndicadorDto.setCoordy(estacion.getCoordy());
				estacionIndicadorDto.setDid(estacion.getDid());
				estacionIndicadorDto.setId(estacion.getId());
				estacionIndicadorDto.setNombre(estacion.getNombre());
				estacionIndicadorDto.setPh(indicador.getPh());
				estacionIndicadorDto.setTemperatura(indicador.getTemperatura());
				estacionIndicadorDto.setFecharegistro(indicador.getFecharegistro());
				listaeidto.add(estacionIndicadorDto);
			});
			
			restTemplate.getForObject("http:localhost:8080/estaciones/indicadores",EstacionIndicadorDto[].class);
			
			
			return new ResponseEntity<List<EstacionIndicadorDto>>(listaeidto,HttpStatus.OK);
		}
		return getListaEstacionIndicador();
	}
	
	@GetMapping(path="/indicadores")
	public ResponseEntity<List<EstacionIndicadorDto>> listarIndicadoresget(){
		listaeidto  = new ArrayList<>(10);
		estacion = new Estacion();
		indicador = new Indicador();
		
		indicadorService.getRecientes().forEach(irdto->{
			estacion = estacionService.listarPorId(irdto.getIdestacion());
			indicador = indicadorService.get(irdto.getIdestacion(), irdto.getFecharegistro());
			estacionIndicadorDto = new EstacionIndicadorDto();
			estacionIndicadorDto.setCloro(indicador.getCloro());
			estacionIndicadorDto.setCoordx(estacion.getCoordx());
			estacionIndicadorDto.setCoordy(estacion.getCoordy());
			estacionIndicadorDto.setDid(estacion.getDid());
			estacionIndicadorDto.setId(estacion.getId());
			estacionIndicadorDto.setNombre(estacion.getNombre());
			estacionIndicadorDto.setPh(indicador.getPh());
			estacionIndicadorDto.setTemperatura(indicador.getTemperatura());
			estacionIndicadorDto.setFecharegistro(indicador.getFecharegistro());
			listaeidto.add(estacionIndicadorDto);
		});
		return new ResponseEntity<List<EstacionIndicadorDto>>(listaeidto,HttpStatus.OK);
	}
	
	public Integer addEstacionBd(JSONObject data) {
		
		System.out.println("MDS0 : " + data.getString("MDS0"));
		estacion = estacionService.getDid(data.getString("DID"));
		if(data.getString("MDS0")!="0"  && estacion!=null) {
			if(data.getString("MDS0").equals("0") || data.getString("MDS0").equals("1"))
				cloro = Float.parseFloat(data.getString("MDR0"));
			else
				cloro = Float.parseFloat("0.0");
			
			if(data.getString("MDS1").equals("0") || data.getString("MDS1").equals("1"))
				temperatura = Float.parseFloat(data.getString("MDR1"));				
			else
				temperatura = Float.parseFloat("0.0");				
			
			if(data.getString("MDS2").equals("0") || data.getString("MDS2").equals("1"))
				ph = Float.parseFloat(data.getString("MDR2"));
			else
				ph= Float.parseFloat("0.0");
			
			fecharegistro = LocalDateTime.now();
			indicador = new Indicador(ph,cloro,temperatura,fecharegistro,estacion);			
			if(indicadorService.registrar(indicador)!=null)
				return 1;
		}
		return 0;
		
	}
	
	public ResponseEntity<List<EstacionIndicadorDto>>  getListaEstacionIndicador() {
		
		listaeidto  = new ArrayList<>(10);
		estacion = new Estacion();
		indicador = new Indicador();
		
		indicadorService.getRecientes().forEach(irdto->{
			estacion = estacionService.listarPorId(irdto.getIdestacion());
			indicador = indicadorService.get(irdto.getIdestacion(), irdto.getFecharegistro());
			estacionIndicadorDto = new EstacionIndicadorDto();
			estacionIndicadorDto.setCloro(indicador.getCloro());
			estacionIndicadorDto.setCoordx(estacion.getCoordx());
			estacionIndicadorDto.setCoordy(estacion.getCoordy());
			estacionIndicadorDto.setDid(estacion.getDid());
			estacionIndicadorDto.setId(estacion.getId());
			estacionIndicadorDto.setNombre(estacion.getNombre());
			estacionIndicadorDto.setPh(indicador.getPh());
			estacionIndicadorDto.setTemperatura(indicador.getTemperatura());
			estacionIndicadorDto.setFecharegistro(indicador.getFecharegistro());
			listaeidto.add(estacionIndicadorDto);
		});
		return new ResponseEntity<List<EstacionIndicadorDto>>(listaeidto,HttpStatus.OK);
	}
}
