package com.enzoHeitor.agendamento.equipamentos.repository;

import com.enzoHeitor.agendamento.equipamentos.entity.ReservaArmario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository  extends JpaRepository<ReservaArmario, Long> {
}
