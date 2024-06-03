package com.clinicaMed.clinicaMedica.model;

import com.clinicaMed.clinicaMedica.dto.DatosDireccion;
import com.clinicaMed.clinicaMedica.dto.DatosMedicos;
import com.clinicaMed.clinicaMedica.dto.MedicoActualizarDTO;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "medicos")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;



    public Medico(DatosMedicos datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());

    }

    public void actualizarMedico(MedicoActualizarDTO medicoActualizarDTO) {
        if(medicoActualizarDTO.nombre()!=null && !medicoActualizarDTO.nombre().isBlank()){
            this.nombre=medicoActualizarDTO.nombre();
        }
        if(medicoActualizarDTO.documento()!=null && !medicoActualizarDTO.documento().isBlank()){
            this.documento=medicoActualizarDTO.documento();
        }


        if(medicoActualizarDTO.direccion()!=null){
            this.direccion=this.direccion.actualizarDireccion(medicoActualizarDTO.direccion());
        }


    }


}
