package com.clinicaMed.clinicaMedica.controller;


import com.clinicaMed.clinicaMedica.domain.consulta.AgendaConsultaService;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosDetalleConsulta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    AgendaConsultaService agendaConsultaService;

    @PostMapping
    @Transactional
    @Operation(summary ="registra una consulta",
    description = "registra una consulta para un paciente mediante su id y el del medico",
    tags = {"consulta","post"})
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos){
       var response= agendaConsultaService.agendar(datos);
        return ResponseEntity.ok(response);
    }

    //elimina la consulta
    @DeleteMapping
    @Transactional
    @Operation(summary = "remueve una consulta de la base de datos",
    description = "",
    tags = {"consulta","delete"})
    public ResponseEntity cancelarConsulta(DatosAgendarConsulta datosAgendarConsulta){
        agendaConsultaService.removerConsulta(datosAgendarConsulta);
        return ResponseEntity.noContent().build();

    }

}
