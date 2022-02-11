package com.smca.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.dto.FiltroReporteDto;
import com.smca.dto.ReporteDto;
import com.smca.service.IIndicadorService;
import com.smca.service.IReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
	
	@Autowired
	IReporteService reporteService;
	
	@Autowired
	IIndicadorService indicadorService;
	
	@PostMapping
	public ResponseEntity<List<ReporteDto>> listaReporte(@RequestBody FiltroReporteDto filtro){
		List<ReporteDto> reporteDto = new ArrayList<>();
		reporteService.getReporte(filtro).forEach(obj->{
			ReporteDto dto = new ReporteDto();
			dto.setNombre(obj.getEstacion().getNombre());
			dto.setFecharegistro(obj.getFecharegistro().toString());
			dto.setCloro(obj.getCloro());
			dto.setPh(obj.getPh());
			dto.setTemperatura(obj.getTemperatura());
			reporteDto.add(dto);
		});
		return new ResponseEntity<List<ReporteDto>>(reporteDto, HttpStatus.OK);
	}
	
	@GetMapping(value="/descargar/{idestacion}/{fechainicioconsulta}/{fechafinconsulta}")
	public ResponseEntity<InputStreamResource> descargar(
			@PathVariable(value="idestacion") Integer idEstacion,
			@PathVariable(value="fechainicioconsulta") String fechainicioconsulta,
			@PathVariable(value="fechafinconsulta") String fechafinconsulta){
		
		System.out.println("fechainicioconsulta : " + fechainicioconsulta);
		System.out.println("fechafinconsulta : " + fechafinconsulta);
		
		LocalDateTime datetimeinicio = LocalDateTime.parse(fechainicioconsulta+"T00:00:00");
		
		LocalDateTime datetimefin = LocalDateTime.parse(fechafinconsulta+"T00:00:00");
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HHmmss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		
		FiltroReporteDto dto = new FiltroReporteDto(idEstacion,datetimeinicio,datetimefin);
		
		ByteArrayInputStream stream = getDescargar(dto);
		
		HttpHeaders headers = new HttpHeaders();
		
		String fecha_archivo = dateFormat.format(date) + hourFormat.format(date);
		
		headers.add("Content-Disposition", "attachment; filename=smca_"+fecha_archivo+".csv");
		
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}
	
	public ByteArrayInputStream getDescargar(FiltroReporteDto filtro) {
		
		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		List<ReporteDto> reporteDto = new ArrayList<>();		
		
		reporteService.getReporte(filtro).forEach(obj->{
			ReporteDto dto = new ReporteDto();
			dto.setNombre(obj.getEstacion().getNombre());
			dto.setFecharegistro(obj.getFecharegistro().toString());
			dto.setCloro(obj.getCloro());
			dto.setPh(obj.getPh());
			dto.setTemperatura(obj.getTemperatura());
			reporteDto.add(dto);
		});
		
		
		String [] columns = {"Estación","Fecha","Cloro","PH","Temperatura"};
		
		Sheet sheet = workbook.createSheet("Data");
		Row row = sheet.createRow(0);
		
		for(int i=0;i<columns.length;i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
		}		
		
		int initRow = 1;
		for(ReporteDto dto : reporteDto) {
			row = sheet.createRow(initRow);
			row.createCell(0).setCellValue(dto.getNombre());
			row.createCell(1).setCellValue(dto.getFecharegistro());
			row.createCell(2).setCellValue(dto.getCloro());
			row.createCell(3).setCellValue(dto.getPh());
			row.createCell(4).setCellValue(dto.getTemperatura());
			initRow++;
		}
		
		try {
			workbook.write(stream);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return new ByteArrayInputStream(stream.toByteArray());		
		
	}
	
}
