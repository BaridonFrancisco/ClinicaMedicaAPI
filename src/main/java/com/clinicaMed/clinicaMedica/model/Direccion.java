package com.clinicaMed.clinicaMedica.model;


import com.clinicaMed.clinicaMedica.dto.DatosDireccion;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;

    public Direccion(DatosDireccion datosDireccion) {
        this.calle=datosDireccion.calle();
        this.numero=datosDireccion.numero();
        this.ciudad=datosDireccion.ciudad();
        this.complemento=datosDireccion.complemento();
        this.distrito=datosDireccion.distrito();
    }


    public Direccion actualizarDireccion(DatosDireccion datosDireccion) {
        this.calle=datosDireccion.calle();
        this.numero=datosDireccion.numero();
        this.ciudad=datosDireccion.ciudad();
        this.complemento=datosDireccion.complemento();
        this.distrito=datosDireccion.distrito();
        return this;
    }
}
