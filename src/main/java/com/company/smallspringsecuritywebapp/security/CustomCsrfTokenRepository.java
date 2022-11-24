package com.company.smallspringsecuritywebapp.security;

import com.company.smallspringsecuritywebapp.model.LocalCsrfToken;
import com.company.smallspringsecuritywebapp.services.LocalCsrfTokenServices;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

    private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";

    private final LocalCsrfTokenServices localCsrfTokenServices;

    public CustomCsrfTokenRepository(LocalCsrfTokenServices localCsrfTokenServices) {
        this.localCsrfTokenServices = localCsrfTokenServices;
    }


    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(
                DEFAULT_CSRF_HEADER_NAME,
                DEFAULT_CSRF_PARAMETER_NAME,
                UUID.randomUUID().toString()
        );
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {

        String headerId = request.getHeader("X-IDENTIFIER");

        if (! StringUtils.hasLength(headerId)) {
            return;
        }

        Optional<LocalCsrfToken> localCsrfToken = localCsrfTokenServices.findById(headerId);

        if (token == null) {
            localCsrfToken.ifPresent(localCsrfTokenServices::delete);
            return;
        }

        localCsrfToken.ifPresentOrElse(
                x -> {
                    x.setToken(token.getToken());
                    localCsrfTokenServices.save(x);
                }

                , () -> localCsrfTokenServices.save(
                            LocalCsrfToken.builder()
                                    .headerId(headerId)
                                    .token(token.getToken())
                                    .build()
                        )
         );

        addHeaderToResponse(response, token.getToken());

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String headerId = request.getHeader("X-IDENTIFIER");

        if (! StringUtils.hasLength(headerId)) {
            return null;
        }

        Optional<LocalCsrfToken> localCsrfToken = localCsrfTokenServices.findById(headerId);

        if (localCsrfToken.isEmpty()) {
            return null;
        }


        return new DefaultCsrfToken(
                DEFAULT_CSRF_HEADER_NAME,
                DEFAULT_CSRF_PARAMETER_NAME,
                localCsrfToken.get().getToken()
        );
    }

    private void addHeaderToResponse(HttpServletResponse response, String token) {
        response.setHeader(DEFAULT_CSRF_PARAMETER_NAME, token);
    }


}
