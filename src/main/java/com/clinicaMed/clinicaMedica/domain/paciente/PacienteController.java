package com.clinicaMed.clinicaMedica.domain.paciente;


import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    /*UriComponenetBuilder */
    @PostMapping
    public ResponseEntity<DatosPacienteDTO> registrarPaciente(@RequestBody @Valid DatosPaciente datosPaciente, UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente=pacienteRepository.save(new Paciente(datosPaciente));
        System.out.println(paciente);
        DatosPacienteDTO datosPacienteDTO=new DatosPacienteDTO(datosPaciente);
        URI url=uriComponentsBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();
        return ResponseEntity.created(url)
                .body(datosPacienteDTO);
    }


    @GetMapping("/a")
    public ResponseEntity<Page<DatosPacienteDTO>> obtenerPacientes(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion)
                .map(DatosPacienteDTO::new));

    }

    /*Solo se van a poder cambiar */
    @Transactional
    @PutMapping
    public ResponseEntity<PacienteUpdateDTO> actualizarPaciente(@RequestBody @Valid  PacienteActualizar pacienteActualizar){
        Paciente paciente=pacienteRepository.getReferenceById(pacienteActualizar.id());
        paciente.updatePaciente(pacienteActualizar);
        System.out.println(paciente);
        return ResponseEntity.ok(new PacienteUpdateDTO(paciente.getId(),paciente.getNombre(),
                paciente.getEmail(),paciente.getTelefono(),
                paciente.getDocumento()
                ,new Direccion(paciente.getDireccion().getCalle(),
                paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(),
                paciente.getDireccion().getDistrito(),
                paciente.getDireccion().getCiudad())));
    }

    @DeleteMapping
    public void deshabilitarPaciente(){

    }
    @GetMapping
    public void obtenerPacientePorId(){

    }

}
