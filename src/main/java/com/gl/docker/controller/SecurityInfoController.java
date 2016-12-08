package com.gl.docker.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityInfoController {
    @RequestMapping("security")
    public SecurityContext getInfo(){
        return SecurityContextHolder.getContext();
    }

    @RequestMapping("vcap")
    public String getVcap(){
        return System.getenv("VCAP_SERVICES");
    }
}
