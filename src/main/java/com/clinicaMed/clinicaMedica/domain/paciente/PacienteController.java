package com.clinicaMed.clinicaMedica.domain.paciente;


import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;
import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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


    @GetMapping
    public ResponseEntity<Page<DatosPacienteDTO>> obtenerPacientes(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(pacienteRepository.findByActivoTrue(paginacion)
                .map(DatosPacienteDTO::new));

    }

    /*Solo se van a poder cambiar */
    @Transactional
    @PutMapping
    public ResponseEntity<PacienteUpdateDTO> actualizarPaciente(@RequestBody @Valid  PacienteActualizar pacienteActualizar){
        Paciente paciente=pacienteRepository.getReferenceById(pacienteActualizar.id());
        System.out.println(pacienteActualizar);
        paciente.updatePaciente(pacienteActualizar);
        System.out.println(paciente);
        return ResponseEntity.ok(new PacienteUpdateDTO(paciente.getId(),paciente.getNombre(),
                paciente.getEmail(),paciente.getTelefono(),
                paciente.getDocumento()
                ,new DatosDireccion(paciente.getDireccion().getCalle(),
                paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(),
                paciente.getDireccion().getDistrito(),
                paciente.getDireccion().getCiudad())));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Paciente> deshabilitarPaciente(@PathVariable Long id){
        Paciente paciente=pacienteRepository.getReferenceById(id);
        paciente.deleteLogico();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosPacienteDTO> obtenerPacientePorId(@PathVariable Long id){
        Paciente paciente=pacienteRepository.getReferenceById(id);
        DatosPacienteDTO datosPacienteDTO=new DatosPacienteDTO(paciente.getNombre(),paciente.getEmail(),paciente.getTelefono()
                ,paciente.getDocumento(),new Direccion(paciente.getDireccion().getCalle(),paciente.getDireccion().getNumero(),
                paciente.getDireccion().getComplemento(),paciente.getDireccion().getDistrito(),paciente
                .getDireccion().getCiudad()));
        return ResponseEntity.ok(datosPacienteDTO);


    }

}
