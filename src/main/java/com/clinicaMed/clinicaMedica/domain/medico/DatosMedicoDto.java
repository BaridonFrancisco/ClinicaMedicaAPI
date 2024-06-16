package com.clinicaMed.clinicaMedica.domain.medico;

import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;


public record DatosMedicoDto(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Especialidad especialidad,
        DatosDireccion direccion



) {
}
