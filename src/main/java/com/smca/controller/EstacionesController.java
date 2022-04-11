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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smca.dto.EstacionDto;
import com.smca.dto.EstacionIndicadorDto;
import com.smca.dto.IndicadorCorrienteRecienteDto;
import com.smca.dto.IndicadorRecienteDto;
import com.smca.model.Estacion;
import com.smca.model.Indicador;
import com.smca.model.IndicadorCorriente;
import com.smca.service.IEstacionService;
import com.smca.service.IIndicadorCorrienteService;
import com.smca.service.IIndicadorService;

@RestController
@RequestMapping("/estaciones")
public class EstacionesController {	
	
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
	@GetMapping(path="/todosatributos")
	public ResponseEntity<List<Estacion>> listarTodosAtributos(){		
		return new ResponseEntity<List<Estacion>>(estacionService.listar(),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<EstacionDto>> listarHabilitadas(){
		
		listaEstacionDto = new ArrayList<>();
		estacionService.listarHabilitadas().stream().sorted().forEach(obj->{
			estacionDto = new EstacionDto();			
			estacionDto.setId(obj.getId());
			estacionDto.setNombre(obj.getNombre());
			estacionDto.setDid(obj.getDid());
			estacionDto.setCoordx(obj.getCoordx());
			estacionDto.setCoordy(obj.getCoordy());
			estacionDto.setChip(obj.getChip());
			estacionDto.setDepartamento(obj.getDistrito().getProvincia().getDepartamento().getNombre());
			estacionDto.setProvincia(obj.getDistrito().getProvincia().getNombre());
			estacionDto.setDistrito(obj.getDistrito().getNombre());
			estacionDto.setEstado(obj.getEstado());
			listaEstacionDto.add(estacionDto);
		});
		return new ResponseEntity<List<EstacionDto>>(listaEstacionDto,HttpStatus.OK);
	}
	
	@GetMapping(path="/popup")
	public ResponseEntity<List<Estacion>> listarHabilitadasPopup(){
		
		List<Estacion> estaciones = new ArrayList<>();
		estacionService.listarHabilitadas().stream().sorted().forEach(obj->{
			estaciones.add(obj);
		});
		return new ResponseEntity<List<Estacion>>(estaciones,HttpStatus.OK);
	}
	
	@GetMapping(path="/todas")
	public ResponseEntity<List<EstacionDto>> listarTodas(){
		
		listaEstacionDto = new ArrayList<>();
		estacionService.listar().stream().sorted().forEach(obj->{
			System.out.println(obj.getNombre());
			estacionDto = new EstacionDto();
			estacionDto.setId(obj.getId());
			estacionDto.setNombre(obj.getNombre());
			estacionDto.setDid(obj.getDid());
			estacionDto.setCoordx(obj.getCoordx());
			estacionDto.setCoordy(obj.getCoordy());
			estacionDto.setChip(obj.getChip());
			estacionDto.setDepartamento(obj.getDistrito().getProvincia().getDepartamento().getNombre());
			estacionDto.setProvincia(obj.getDistrito().getProvincia().getNombre());
			estacionDto.setDistrito(obj.getDistrito().getNombre());
			estacionDto.setEstado(obj.getEstado());
			listaEstacionDto.add(estacionDto);
		});
		return new ResponseEntity<List<EstacionDto>>(listaEstacionDto,HttpStatus.OK);
	}
	
	@GetMapping(path="/indicadores")
	public ResponseEntity<List<EstacionIndicadorDto>> listarEstacionesIndicadores(){
		
		return getListaEstacionIndicador();
	}
	
	@GetMapping("/{idEstacion}")
	public ResponseEntity<Estacion> listarPorId(@PathVariable("idEstacion") Integer idEstacion){
		return new ResponseEntity<Estacion>(estacionService.listarPorId(idEstacion),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> registrar(@RequestBody Estacion dto){
		dto.setId(estacionService.maxId()+1);
		return new ResponseEntity<Integer>(estacionService.registrar(dto).getId(),HttpStatus.OK);
	}	
	
	@PutMapping
	public ResponseEntity<Integer> modificar(@RequestBody Estacion estacion){
		return new ResponseEntity<Integer>(estacionService.modificar(estacion).getId(),HttpStatus.OK);
	}
	
	
	@PostMapping(path="/indicadores",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public ResponseEntity<List<EstacionIndicadorDto>> listarIndicadorespost(@RequestParam Map<String, String> map) throws UnsupportedEncodingException, MalformedURLException{
		
		JSONObject json =  new JSONObject(map);
		System.out.println(json);
		if(addEstacionIndicadorBd(json)==0) {
			addEstacionIndicadorCorrienteBd(json);
		}
		return getListaEstacionIndicador();
	}
	
	
	
	public Integer addEstacionIndicadorBd(JSONObject data){		
		try {
			estacion = estacionService.getDid(data.getString("DID"));
			if(data.getString("MDS0").equals("0") || data.getString("MDS0").equals("1")) {
				cloro = Float.parseFloat(data.getString("MDR0"));
			} else {
				cloro = Float.parseFloat("0.0");
			}
			
			if(data.getString("MDS1").equals("0") || data.getString("MDS1").equals("1")) {
				temperatura = Float.parseFloat(data.getString("MDR1"));				
			} else {
				temperatura = Float.parseFloat("0.0");				
			}
			
			if(data.getString("MDS2").equals("0") || data.getString("MDS2").equals("1")) {
				ph = Float.parseFloat(data.getString("MDR2"));
			} else {
				ph= Float.parseFloat("0.0");
			}
			
			if(data.getString("MDS3").equals("0") || data.getString("MDS3").equals("1")) {
				caudal = Float.parseFloat(data.getString("MDR3"));
			} else {
				caudal= Float.parseFloat("0.0");
			}
			
			fecharegistro = LocalDateTime.now();
			indicador = new Indicador(ph,cloro,temperatura,caudal,fecharegistro,estacion);			
			if(indicadorService.registrar(indicador)!=null) {
				return 1;
			}
		}
		catch(Exception exc) {
			return 0;
		}
		return 0;
	}
	
	public ResponseEntity<List<EstacionIndicadorDto>>  getListaEstacionIndicador() {
		
		listaeidto  = new ArrayList<>();
		estacion = new Estacion();
		indicador = new Indicador();
		indicadorCorriente = new IndicadorCorriente();
		
		estacionService.listarHabilitadas().stream().sorted().forEach(obj->{
			Indicador indicador = indicadorService.getReciente(obj.getId());
			
			if(indicador!=null) {
				eiDto = new EstacionIndicadorDto();
				eiDto.setCoordx(indicador.getEstacion().getCoordx());
				eiDto.setCoordy(indicador.getEstacion().getCoordy());
				eiDto.setDid(indicador.getEstacion().getDid());
				eiDto.setId(indicador.getEstacion().getId());
				eiDto.setNombre(indicador.getEstacion().getNombre());
				eiDto.setCloro(indicador.getCloro());
				eiDto.setPh(indicador.getPh());
				eiDto.setTemperatura(indicador.getTemperatura());
				eiDto.setCaudal(indicador.getCaudal());
				eiDto.setFecharegistro(dateFormat.format(indicador.getFecharegistro()));
				IndicadorCorriente ic = indicadorcorrienteService.getReciente(indicador.getEstacion().getId());
				if(ic!=null) {					
					eiDto.setCorrientedc(ic.getCorrientedc());
					eiDto.setCorrienteac(ic.getCorrienteac());					
					eiDto.setFecharegistrocorriente(dateFormat.format(ic.getFecharegistro()));
					eiDto.setIdestacion(indicador.getEstacion().getId());
				} else {
					eiDto.setCorrientedc((float)-1.0);
					eiDto.setCorrienteac((float)-1.0);
					eiDto.setFecharegistrocorriente(null);
					eiDto.setIdestacion(obj.getId());
				}				
			} else {
				eiDto = new EstacionIndicadorDto();
				eiDto.setCoordx(obj.getCoordx());
				eiDto.setCoordy(obj.getCoordy());
				eiDto.setDid(obj.getDid());
				eiDto.setId(obj.getId());
				eiDto.setNombre(obj.getNombre());
				eiDto.setCloro((float)-1.0);
				eiDto.setPh((float)-1.0);
				eiDto.setTemperatura((float)-1.0);
				eiDto.setCaudal((float)-1.0);
				eiDto.setCorrientedc((float)-1.0);
				eiDto.setCorrienteac((float)-1.0);
				eiDto.setFecharegistro(null);
				eiDto.setFecharegistrocorriente(null);
				eiDto.setIdestacion(obj.getId());
			}				
			listaeidto.add(eiDto);
		});
		
		/*indicadorService.getRecientes().forEach(irdto->{
			indicadorcorrienteService.getRecientes().forEach(icrdto->{
				if(irdto.getIdestacion().equals(icrdto.getIdestacion())) {					
					estacion = estacionService.listarPorId(irdto.getIdestacion());					
					if(estacion.getId()<=26) {
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
				}
			});
		});*/
		return new ResponseEntity<List<EstacionIndicadorDto>>(listaeidto,HttpStatus.OK);
	}
	
	public Integer addEstacionIndicadorCorrienteBd(JSONObject data){		
		try {
			estacion = estacionService.getDid(data.getString("DID"));
			if(data.getString("MDS4").equals("0") || data.getString("MDS4").equals("1")) {
				corrientedc = Float.parseFloat(data.getString("MDR4"));
			} else {
				corrientedc= Float.parseFloat("0.0");
			}
			
			if(data.getString("MDS5").equals("0") || data.getString("MDS5").equals("1")) {
				corrienteac = Float.parseFloat(data.getString("MDR5"));
			} else {
				corrienteac= Float.parseFloat("0.0");
			}
			
			fecharegistro = LocalDateTime.now();
			indicadorCorriente = new IndicadorCorriente(corrientedc,corrienteac,fecharegistro,estacion);			
			if(indicadorcorrienteService.registrar(indicadorCorriente)!=null)
				return 1;
		}
		catch(Exception exc) {
			
			return 0;
		}
		return 0;
	}
	
	@DeleteMapping("/{id}/{estado}")
	public ResponseEntity<Integer> cambiarEstadoHabilitar(@PathVariable("id") Integer id, @PathVariable("estado") Boolean estado){
		return new ResponseEntity<Integer>(estacionService.cambiarEstadoHabilitado(id, estado),HttpStatus.OK);
	}
}