package com.clinicaMed.clinicaMedica.model;

public record DatosMedicos(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {
}
