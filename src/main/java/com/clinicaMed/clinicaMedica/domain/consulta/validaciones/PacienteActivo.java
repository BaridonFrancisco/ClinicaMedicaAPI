package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.paciente.PacienteRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;

public class PacienteActivo {

    @Autowired
    PacienteRepository pacienteRepository;

    public void validacion(DatosAgendarConsulta datosAgendarConsulta){
        if(datosAgendarConsulta.idPaciente()==null){
            return;
        }
        var paciente=pacienteRepository.buscarActivo(datosAgendarConsulta.idPaciente());

        if(!paciente){
            throw new ValidacionConsultaException("no se puede agendar citas con Pacientes inactivos");

        }

    }
}
