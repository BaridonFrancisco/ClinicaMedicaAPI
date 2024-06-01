package com.clinicaMed.clinicaMedica.repository;

import com.clinicaMed.clinicaMedica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
}
