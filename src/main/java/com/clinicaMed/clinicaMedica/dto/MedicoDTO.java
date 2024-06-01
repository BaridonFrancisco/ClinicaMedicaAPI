package com.clinicaMed.clinicaMedica.dto;

import com.clinicaMed.clinicaMedica.model.Especialidad;
import com.clinicaMed.clinicaMedica.model.Medico;

public record MedicoDTO(
        String nombre,
        Especialidad especialidad,
        String documento,
        String email


) {

    public MedicoDTO(Medico med) {
        this(med.getNombre(),med.getEspecialidad(),med.getDocumento(),med.getEmail());
    }

    public MedicoDTO(String nombre, Especialidad especialidad, String documento, String email) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.documento = documento;
        this.email = email;
    }
}
