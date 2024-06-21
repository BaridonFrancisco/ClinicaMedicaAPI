package com.clinicaMed.clinicaMedica.domain.consulta;

import com.clinicaMed.clinicaMedica.domain.medico.MedicoRepository;
import com.clinicaMed.clinicaMedica.domain.paciente.PacienteRepository;
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
        var paciente=pacienteRepository.findById(datosValidar.idPaciente());
        var medico=medicoRepository.findById(datosValidar.idMedico());
        if(medico.isPresent() && paciente.isPresent()){
            var consultaCreada=new Consulta(null,medico.get(),paciente.get(),datosValidar.fecha());
           consultaRepository.save(consultaCreada);

        }

    }

}
