package com.clinicaMed.clinicaMedica.controller;

import com.clinicaMed.clinicaMedica.dto.DatosMedicos;
import com.clinicaMed.clinicaMedica.model.Medico;
import com.clinicaMed.clinicaMedica.repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosMedicos datosMedicos){
        System.out.println("Request llego existosamente");
        medicoRepository.save(new Medico(datosMedicos));

    }
    /*
    //Post data
{
	"nombre":"Francisco",
	"email":"emailrandom@gmail.com",
	"documento":"123213",
	"especialidad":"ORTOPEDIA",
	"direccion":{
			"calle":"1271",
			"distrito":"La Plata",
			"ciudad":"Buenos Aires",
			"complemento":"no"
	}

}*/
}
