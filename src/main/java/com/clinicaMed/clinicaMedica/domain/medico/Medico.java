package com.clinicaMed.clinicaMedica.domain.medico;

import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;


@Table(name = "medicos")
@Entity(name="Medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;
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
        this.activo=true;

    }

    public Medico(Long id, String nombre,String email,String documento,Especialidad especialidad,Direccion direccion,String telefono,Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.documento = documento;
        this.especialidad=especialidad;
        this.direccion=direccion;
        this.telefono=telefono;
        this.activo=activo;
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




    public void desHabilitar(Medico medico) {
        this.activo=false;

    }
}
