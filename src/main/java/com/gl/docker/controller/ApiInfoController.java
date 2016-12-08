package com.gl.docker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregates API documentation from composite servers that it forwards to.
 *
 * Customization of SpringFox's ApiResourceController to support multiple swagger resources. UI and security
 * configuration will have to be manually configured below if needed.
 *
 * With the current (proposed) practice of matching url structure between the api gateway and composite services,
 * this service can display swagger API documentation for any composite service as if it was for the API gateway
 * itself.
 */
@Controller
public class ApiInfoController {

    @RequestMapping("/")
    public String redirectToSwagger(){
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping(value = "/swagger-resources")
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
        // Add composite services here. They will be exposed via the API Gateway's swagger page
        resources.add(swaggerResource("Demo", "/swagger/demo", "2.0"));

        return new ResponseEntity<List<SwaggerResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/security")
    @ResponseBody
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<SecurityConfiguration>(SecurityConfiguration.DEFAULT, HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    @ResponseBody
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<UiConfiguration>(UiConfiguration.DEFAULT, HttpStatus.OK);
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setLocation(location);
        swaggerResource.setName(name);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
