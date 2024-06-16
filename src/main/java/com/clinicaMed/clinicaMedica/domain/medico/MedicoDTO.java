package com.clinicaMed.clinicaMedica.domain.medico;

import com.clinicaMed.clinicaMedica.domain.Medico;

public record MedicoDTO(
        Long id,
        String nombre,
        Especialidad especialidad,
        String documento,
        String email


) {

    public MedicoDTO(Medico med) {
        this(med.getId(),med.getNombre(),med.getEspecialidad(),med.getDocumento(),med.getEmail());
    }

    public MedicoDTO(Long id,String nombre, Especialidad especialidad, String documento, String email) {
        this.id=id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.documento = documento;
        this.email = email;
    }
}
