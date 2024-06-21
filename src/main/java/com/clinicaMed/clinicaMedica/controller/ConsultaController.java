package com.clinicaMed.clinicaMedica.controller;


import com.clinicaMed.clinicaMedica.domain.consulta.AgendaConsultaService;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosDetalleConsulta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    AgendaConsultaService agendaConsultaService;

    @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        agendaConsultaService.agendar(datos);
        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));
    }
}
