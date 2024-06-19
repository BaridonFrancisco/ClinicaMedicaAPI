package com.clinicaMed.clinicaMedica.domain.paciente;

import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;

public record DatosPacienteDTO(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        Direccion direccion

)
{


    public DatosPacienteDTO(DatosPaciente datosPaciente) {
        this(datosPaciente.nombre(),datosPaciente.email(),datosPaciente.telefono()
                ,datosPaciente.documento(),new Direccion(datosPaciente.direccion().getCalle(),datosPaciente.direccion().getNumero(),datosPaciente.direccion().getComplemento(),datosPaciente.direccion().getDistrito(),datosPaciente.direccion().getCiudad()));
    }

    public DatosPacienteDTO(String nombre, String email, String telefono, String documentoIdentidad,Direccion direccion) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.documentoIdentidad = documentoIdentidad;
        this.direccion=direccion;
    }

    public DatosPacienteDTO(Paciente paciente) {
        this(paciente.getNombre(),paciente.getEmail(),paciente.getTelefono()
                ,paciente.getDocumento()
                ,new Direccion(paciente.getDireccion().getCalle()
                        ,paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad()));
    }
}
