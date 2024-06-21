package com.clinicaMed.clinicaMedica.domain.consulta;

import com.clinicaMed.clinicaMedica.domain.medico.Medico;
import com.clinicaMed.clinicaMedica.domain.medico.MedicoRepository;
import com.clinicaMed.clinicaMedica.domain.paciente.PacienteRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DatosAgendarConsulta datosValidar){

       if(pacienteRepository.existsById(datosValidar.idPaciente()) && medicoRepository.existsById(datosValidar.idMedico())){
           var paciente=pacienteRepository.findById(datosValidar.idPaciente());
           var medico=medicoRepository.findById(datosValidar.idMedico());
            var medicoAleatorio=seleccionarMedico(datosValidar);
           if(medico.isPresent() && paciente.isPresent()){
               var consultaCreada=new Consulta(null,medico.get(),paciente.get(),datosValidar.fecha());
               consultaRepository.save(consultaCreada);
           }

       }

        throw new ValidacionConsultaException("no se ha podido validar los datos de la consulta");
    }


    private Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta) {
        if(datosAgendarConsulta.idMedico()!=null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        return medicoRepository.seleccionarMedicoConEspecialidad(datosAgendarConsulta.especialidad(),datosAgendarConsulta.fecha());

    }

}
