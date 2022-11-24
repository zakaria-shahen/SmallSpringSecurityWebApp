package com.company.smallspringsecuritywebapp.services;

import com.company.smallspringsecuritywebapp.model.LocalCsrfToken;
import com.company.smallspringsecuritywebapp.repository.LocalCsrfTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalCsrfTokenServices {

    private final LocalCsrfTokenRepository localCsrfTokenRepository;

    public LocalCsrfTokenServices(LocalCsrfTokenRepository localCsrfTokenRepository) {
        this.localCsrfTokenRepository = localCsrfTokenRepository;
    }

    public Optional<LocalCsrfToken> findById(String headerId) {
         return localCsrfTokenRepository.findByHeaderId(headerId);
    }

    public void save(LocalCsrfToken localCsrfToken) {
        localCsrfTokenRepository.save(localCsrfToken);
    }

    public void delete(LocalCsrfToken token) {
        localCsrfTokenRepository.delete(token);
    }
}
