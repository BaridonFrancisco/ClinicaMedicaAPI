package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.ConsultaRepository;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;

public class MedicoConConsulta {
    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DatosAgendarConsulta datosAgendarConsulta){
        if(datosAgendarConsulta.idMedico()==null){
            return;
        }
        var condicion=consultaRepository.existByMedicoIdAndFecha(datosAgendarConsulta.idMedico(),datosAgendarConsulta.fecha());

        if(condicion){
            throw new ValidacionConsultaException("el medico ya esta trabajando con ese paciente");
        }
    }
}
