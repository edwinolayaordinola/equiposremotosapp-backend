package com.smca.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smca.dto.EstacionDto;
import com.smca.dto.EstacionIndicadorDto;
import com.smca.model.Estacion;
import com.smca.model.Indicador;
import com.smca.model.IndicadorCorriente;
import com.smca.service.IEstacionService;
import com.smca.service.IIndicadorCorrienteService;
import com.smca.service.IIndicadorService;

@RestController
@RequestMapping("/estaciones")
public class EstacionController {	
	
	@Autowired
	private IEstacionService estacionService;
	
	@Autowired
	private IIndicadorService indicadorService;
	
	@Autowired
	private IIndicadorCorrienteService indicadorcorrienteService;
	
	Estacion estacion;
	Indicador indicador;
	IndicadorCorriente indicadorCorriente;
	
	EstacionIndicadorDto eiDto;
	List<EstacionIndicadorDto> listaeidto;
	
	EstacionDto estacionDto;
	List<EstacionDto> listaEstacionDto;
	
	String nombreEstacion;
	Float cloro, ph, temperatura,caudal,corrientedc,corrienteac;
	
	LocalDateTime fecharegistro;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	
	/*@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")*/
	@GetMapping
	public ResponseEntity<List<EstacionDto>> listar(){
		
		listaEstacionDto = new ArrayList<>(15);
		estacionService.listar().stream().sorted().forEach(obj->{
			estacionDto = new EstacionDto();
			estacionDto.setId(obj.getId());
			estacionDto.setNombre(obj.getNombre());
			estacionDto.setDid(obj.getDid());
			estacionDto.setCoordx(obj.getCoordx());
			estacionDto.setCoordy(obj.getCoordy());
			estacionDto.setChip(obj.getChip());
			listaEstacionDto.add(estacionDto);
		});
		return new ResponseEntity<List<EstacionDto>>(listaEstacionDto,HttpStatus.OK);
	}
	
	@GetMapping(path="/indicadores")
	public ResponseEntity<List<EstacionIndicadorDto>> listarEstacionesIndicadores(){
		
		return getListaEstacionIndicador();
	}
	
	@PostMapping(path="/indicadores",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<List<EstacionIndicadorDto>> listarIndicadorespost(@RequestParam Map<String, String> map) throws UnsupportedEncodingException, MalformedURLException{
		
		JSONObject json =  new JSONObject(map);
		if(addEstacionIndicadorBd(json)==0) {
			addEstacionIndicadorCorrienteBd(json);
		}
		return getListaEstacionIndicador();
	}
	
	public ResponseEntity<List<EstacionIndicadorDto>>  getListaEstacionIndicador() {
		
		listaeidto  = new ArrayList<>(10);
		estacion = new Estacion();
		indicador = new Indicador();
		indicadorCorriente = new IndicadorCorriente();
		
		indicadorService.getRecientes().forEach(irdto->{
			indicadorcorrienteService.getRecientes().forEach(icrdto->{
				if(irdto.getIdestacion().equals(icrdto.getIdestacion())) {
					
					estacion = estacionService.listarPorId(irdto.getIdestacion());
					indicador = indicadorService.get(irdto.getIdestacion(), irdto.getFecharegistro());
					indicadorCorriente = indicadorcorrienteService.get(icrdto.getIdestacion(), icrdto.getFecharegistro());
					
					eiDto = new EstacionIndicadorDto();
					eiDto.setCloro(indicador.getCloro());
					eiDto.setCoordx(estacion.getCoordx());
					eiDto.setCoordy(estacion.getCoordy());
					eiDto.setDid(estacion.getDid());
					eiDto.setId(estacion.getId());
					eiDto.setNombre(estacion.getNombre());
					eiDto.setPh(indicador.getPh());
					eiDto.setTemperatura(indicador.getTemperatura());
					eiDto.setCaudal(indicador.getCaudal());
					eiDto.setCorrientedc(indicadorCorriente.getCorrientedc());
					eiDto.setCorrienteac(indicadorCorriente.getCorrienteac());
					eiDto.setFecharegistro(dateFormat.format(indicador.getFecharegistro()));
					eiDto.setFecharegistrocorriente(dateFormat.format(indicadorCorriente.getFecharegistro()));
					eiDto.setIdestacion(indicador.getEstacion().getId());
					listaeidto.add(eiDto);
				}
			});
		});
		return new ResponseEntity<List<EstacionIndicadorDto>>(listaeidto,HttpStatus.OK);
	}
	
	public Integer addEstacionIndicadorBd(JSONObject data){		
		try {
			System.out.println("indicador");
			System.out.println(data);
			estacion = estacionService.getDid(data.getString("DID"));
			//if(data.getString("MDS0")!="0"  && estacion!=null) {
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
				if(data.getString("MDS3").equals("0") || data.getString("MDS3").equals("1"))
					caudal = Float.parseFloat(data.getString("MDR3"));
				else
					caudal= Float.parseFloat("0.0");
				fecharegistro = LocalDateTime.now();
				indicador = new Indicador(ph,cloro,temperatura,caudal,fecharegistro,estacion);			
				if(indicadorService.registrar(indicador)!=null)
					return 1;
			//}			
		}
		catch(Exception exc) {
			return 0;
		}
		return 0;
	}
	
	
	public Integer addEstacionIndicadorCorrienteBd(JSONObject data){		
		try {
			System.out.println("indicadorcorriente");
			System.out.println(data);
			estacion = estacionService.getDid(data.getString("DID"));
			//if(data.getString("MDS4")!="0"  && estacion!=null) {
				if(data.getString("MDS4").equals("0") || data.getString("MDS4").equals("1"))
					corrientedc = Float.parseFloat(data.getString("MDR4"));
				else
					corrientedc= Float.parseFloat("0.0");
				if(data.getString("MDS5").equals("0") || data.getString("MDS5").equals("1"))
					corrienteac = Float.parseFloat(data.getString("MDR5"));
				else
					corrienteac= Float.parseFloat("0.0");
				
				fecharegistro = LocalDateTime.now();
				indicadorCorriente = new IndicadorCorriente(corrientedc,corrienteac,fecharegistro,estacion);			
				if(indicadorcorrienteService.registrar(indicadorCorriente)!=null)
					return 1;
			//}		
		}
		catch(Exception exc) {
			
			return 0;
		}
		return 0;
	}
}