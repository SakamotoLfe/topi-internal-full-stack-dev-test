package com.topi.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

class SwaggerConfigTest {

    SwaggerConfig swaggerConfig;

    @MockBean
    ResourceHandlerRegistry registry;

    @BeforeEach
    void startUp() {
        registry = Mockito.mock(ResourceHandlerRegistry.class, Mockito.RETURNS_DEEP_STUBS);
        swaggerConfig = new SwaggerConfig();
    }

    @Test
    void api() {
        Assertions.assertNotNull(swaggerConfig.api());
    }
}