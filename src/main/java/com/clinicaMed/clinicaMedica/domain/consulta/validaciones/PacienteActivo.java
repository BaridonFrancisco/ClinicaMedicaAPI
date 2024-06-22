package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.paciente.Paciente;
import com.clinicaMed.clinicaMedica.domain.paciente.PacienteRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidarConsulta {

    @Autowired
    PacienteRepository pacienteRepository;
    @Override
    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        //Los id nunca vienen nulos porque se validan en el record
        //TODO quitar
        if(datosAgendarConsulta.idPaciente()==null){
            return;
        }

        var paciente=pacienteRepository.findById(datosAgendarConsulta.idPaciente())
                .orElse(null);

        if(paciente!=null && paciente.getActivo()){
            return;
        }
        throw new ValidacionConsultaException("no se puede agendar citas para Pacientes inactivos o no existentes");


    }
}
