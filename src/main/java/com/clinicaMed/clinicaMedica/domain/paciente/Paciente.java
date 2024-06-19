package com.clinicaMed.clinicaMedica.domain.paciente;


import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "paciente")
@Table(name="pacientes")
@EqualsAndHashCode(of = "id")
@ToString
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Paciente(DatosPaciente datosPaciente) {
        this.activo=true;
        this.nombre=datosPaciente.nombre();
        this.email=datosPaciente.email();
        this.telefono=datosPaciente.telefono();
        this.documento=datosPaciente.documento();
        this.direccion=datosPaciente.direccion();
    }

    public void updatePaciente(PacienteActualizar pacienteActualizar) {
        //TODO se puede refactorizar o mejorar
        if( pacienteActualizar.nombre()!=null && !pacienteActualizar.nombre().isBlank()){
            this.nombre=pacienteActualizar.nombre();
        }
        if(pacienteActualizar.email()!=null && !pacienteActualizar.email().isBlank()){
            this.email=pacienteActualizar.email();
        }
        if(pacienteActualizar.telefono()!=null && !pacienteActualizar.telefono().isBlank()){
            this.telefono=pacienteActualizar.telefono();
        }
        if(pacienteActualizar.documentoIdentidad()!=null && !pacienteActualizar.documentoIdentidad().isBlank()){
            this.documento=pacienteActualizar.documentoIdentidad();
        }
        System.out.println("los datos "+pacienteActualizar.datosDireccion());
        this.direccion.actualizarDireccion(pacienteActualizar.datosDireccion());
    }


    public void deleteLogico() {
        this.activo=false;
    }
}
