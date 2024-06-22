package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.ConsultaRepository;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientesSinConsulta implements ValidarConsulta {

    @Autowired
    ConsultaRepository consultaRepository;
    @Override
    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        /*Valida si existe un paciente en la agenda con una
        * fecha entre los rango de tiempo*/
        var primerHorario=datosAgendarConsulta.fecha().withHour(7);
        var ultimoHorario=datosAgendarConsulta.fecha().withHour(19);

        var pacienteConsulta=consultaRepository.existsByPacienteIdAndDataBetween(datosAgendarConsulta.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConsulta){
            throw new ValidacionConsultaException("existe un paciente en la agenda con ese horario ");
        }

    }
}
