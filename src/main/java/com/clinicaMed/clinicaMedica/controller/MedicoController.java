package com.clinicaMed.clinicaMedica.controller;

import com.clinicaMed.clinicaMedica.dto.*;
import com.clinicaMed.clinicaMedica.model.Medico;
import com.clinicaMed.clinicaMedica.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosMedicoDto>  registrarMedico(@RequestBody @Valid DatosMedicos datosMedicos, UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Request llego existosamente");
       Medico medico= medicoRepository.save(new Medico(datosMedicos));
        System.out.println(uriComponentsBuilder.build().getPath());
        DatosMedicoDto datosMedicos1=new DatosMedicoDto(medico.getId(),medico.getNombre(),medico.getEmail(),medico.getDocumento(),medico.getTelefono(),medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
       URI url=uriComponentsBuilder.path("/medicos/{id}")
               .buildAndExpand(medico.getId())
               .toUri();
       return ResponseEntity.created(url)
               .body(datosMedicos1);

    }
    /*
    //Post data
{

{
	"nombre":"Eduardo",
	"email":"emailrandom2@gmail.com",
	"documento":"123",
	"especialidad":"ORTOPEDIA",
	"telefono":"2123",
	"direccion":{
			"calle":"1271",
			"numero":"12345",
			"distrito":"La Plata",
			"ciudad":"Buenos Aires",
			"complemento":"no"
	}
}
}*/
    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> listarMedicos (@PageableDefault(size =2)Pageable paginacion){
        //return medicoRepository.findAll(paginacion)
               // .map(MedicoDTO::new);
            return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion)
                    .map(MedicoDTO::new));

    }


    //Update Resource
    /*
{
	"id": 1,
	"nombre": "Ricardo",
	"documento":"56734",
	"direccion":{
			"calle":"78 es",
			"distrito":"b",
			"complemento":"no",
			"numero":"10001",
			"ciudad":"Berriso",
			"telefono":"567777"
		}
}*/


    @Transactional
    @PutMapping
    public ResponseEntity<DatosMedicoDto> actualizarMedico(@RequestBody @Valid MedicoActualizarDTO medicoActualizado){
        Medico medico=medicoRepository.getReferenceById(medicoActualizado.id());
        medico.actualizarMedico(medicoActualizado);
        return ResponseEntity.ok(new DatosMedicoDto(medico.getId(),medico.getNombre(),medico.getEmail(),medico.getDocumento(),medico.getTelefono(),medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));

    }
    //borrado logico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id){
        if(!medicoRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Medico medico=medicoRepository.getReferenceById(id);
        medico.desHabilitar(medico);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //borrado
    /* @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }*/

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosMedicoDto> getMedicoById(@PathVariable Long id){
        Medico medico=medicoRepository.getReferenceById(id);
        DatosMedicoDto datosMedicoDto=new DatosMedicoDto(medico.getId(),medico.getNombre(),medico.getEmail(),medico.getDocumento(),medico.getTelefono(),medico.getEspecialidad(),
                new DatosDireccion(medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));
        //return ResponseEntity.ok().build(); no retorna nada en el body
        return ResponseEntity.ok(datosMedicoDto);
    }

}
