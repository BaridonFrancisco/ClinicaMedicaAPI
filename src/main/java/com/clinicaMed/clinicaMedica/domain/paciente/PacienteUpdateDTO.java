package com.clinicaMed.clinicaMedica.domain.paciente;

import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;

public record PacienteUpdateDTO(

        Long id,
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        Direccion direccionPaciente

) {
}
