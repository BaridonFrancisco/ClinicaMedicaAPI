package com.clinicaMed.clinicaMedica.controller;


import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosDetalleConsunta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*@Controller
@ResponseBody
@RequestMapping("/consultas")*/
public class ConsultaController {

  /*  @PostMapping
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos){

        return ResponseEntity.ok(new DatosDetalleConsunta(null,null,null,null));
    }*/
}
