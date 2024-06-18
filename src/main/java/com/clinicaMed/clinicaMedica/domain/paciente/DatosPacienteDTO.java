package com.clinicaMed.clinicaMedica.domain.paciente;

public record DatosPacienteDTO(
        String nombre,
        String email,
        String telefono,
        String documentoIdentidad,
        String calle,
        String numero,
        String complemento,
        String distrito,
        String ciudad

)
{


    public DatosPacienteDTO(DatosPaciente datosPaciente) {
        this(datosPaciente.nombre(),datosPaciente.email(),datosPaciente.telefono()
                ,datosPaciente.documento(),datosPaciente.direccion().getCalle()
                ,datosPaciente.direccion().getNumero(),
                datosPaciente.direccion().getComplemento(),
                datosPaciente.direccion().getDistrito(),
                datosPaciente.direccion().getCiudad());
    }

    public DatosPacienteDTO(String nombre, String email, String telefono, String documentoIdentidad, String calle, String numero, String complemento, String distrito, String ciudad) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.documentoIdentidad = documentoIdentidad;
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
        this.distrito = distrito;
        this.ciudad = ciudad;
    }

    public DatosPacienteDTO(Paciente paciente) {
        this(paciente.getNombre(),paciente.getEmail(),paciente.getTelefono(),
                paciente.getDocumento(),paciente.getDireccion().getCalle(),
                paciente.getDireccion().getNumero()
                ,paciente.getDireccion().getComplemento(),
                paciente.getDireccion().getDistrito(),
                paciente.getDireccion().getCiudad());
    }
}
