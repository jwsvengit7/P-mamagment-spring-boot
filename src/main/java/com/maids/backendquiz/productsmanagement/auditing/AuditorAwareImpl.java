package com.maids.backendquiz.productsmanagement.auditing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        log.info("******** Context ****** {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return Optional.of("System");
    }
}
