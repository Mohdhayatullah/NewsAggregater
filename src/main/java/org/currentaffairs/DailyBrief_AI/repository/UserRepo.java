package org.currentaffairs.DailyBrief_AI.repository;

import org.currentaffairs.DailyBrief_AI.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Client,Long> {
    Optional<Client> findByEmail(String email);
}
