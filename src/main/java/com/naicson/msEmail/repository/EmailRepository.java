package com.naicson.msEmail.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naicson.msEmail.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
