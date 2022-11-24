package com.company.smallspringsecuritywebapp.repository;

import com.company.smallspringsecuritywebapp.model.LocalCsrfToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalCsrfTokenRepository extends CrudRepository<LocalCsrfToken, Long> {

    Optional<LocalCsrfToken> findByHeaderId(String headerId);

    Optional<LocalCsrfToken> findByToken(String token);
}
