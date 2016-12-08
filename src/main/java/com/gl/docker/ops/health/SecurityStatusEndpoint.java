package com.gl.docker.ops.health;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * In Spring Cloud Angel this does not seem to grab the context for the current user.
 */
@Component
public class SecurityStatusEndpoint implements Endpoint<SecurityContext> {
    @Override
    public String getId() {
        return "security";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public SecurityContext invoke() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context;
    }
}
