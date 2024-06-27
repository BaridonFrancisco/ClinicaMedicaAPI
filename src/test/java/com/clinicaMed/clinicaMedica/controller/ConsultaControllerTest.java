package com.clinicaMed.clinicaMedica.controller;

import com.clinicaMed.clinicaMedica.domain.consulta.AgendaConsultaService;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.consulta.DatosDetalleConsulta;
import com.clinicaMed.clinicaMedica.domain.medico.Especialidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleConsulta;
    @Autowired
    private JacksonTester<DatosAgendarConsulta>agendarConsulta;
    @MockBean
    private AgendaConsultaService agendaConsultaService;
    @Test
    @DisplayName("verifica que retorne un bad request 400")
    @WithMockUser
    void agendar() throws Exception {
        var respuesta=mockMvc.perform(post("/consultas")).andReturn()
                .getResponse();

        Assertions.assertEquals(respuesta.getStatus(), HttpStatus.BAD_REQUEST.value());
    }
    //TODO investigar sobre mockito y el mockeo
    @Test
    @DisplayName("deberia retornar 200 cuandos los datos son validos")
    @WithMockUser
    void agendar2() throws Exception {
        //given
        var fecha= LocalDateTime.now().plusHours(1);
        Especialidad especialidad=Especialidad.CARDIOLOGIA;
        var datos=new DatosDetalleConsulta(null,1L,4L,fecha);

        //When
        when(agendaConsultaService.agendar(any())).thenReturn(datos);

        var respuesta=mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(agendarConsulta.write((new DatosAgendarConsulta(null,1L,4L,fecha,especialidad)))
                        .getJson()))
                .andReturn()
                .getResponse();

        //Then
        Assertions.assertEquals(respuesta.getStatus(), HttpStatus.OK.value());

        String jsonEsperado=detalleConsulta.write(datos).getJson();


        Assertions.assertEquals(respuesta.getContentAsString(),jsonEsperado);


    }

}