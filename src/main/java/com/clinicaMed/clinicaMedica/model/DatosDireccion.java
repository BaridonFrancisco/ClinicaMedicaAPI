package com.clinicaMed.clinicaMedica.model;

public record DatosDireccion(
        String calle,
        String distrito,
        String ciudad,
        int numero,
        String complemento
) {
}
