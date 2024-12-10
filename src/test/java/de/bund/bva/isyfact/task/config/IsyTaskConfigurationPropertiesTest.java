package de.bund.bva.isyfact.task.config;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class IsyTaskConfigurationPropertiesTest {

    @Test
    void defaultGetter() {
        String testValue = "TEST";
        IsyTaskConfigurationProperties.Default propDefault = new IsyTaskConfigurationProperties.Default();
        propDefault.setHost(testValue);
        assertEquals(testValue, propDefault.getHost());
        propDefault.setOauth2ClientRegistrationId(testValue);
        assertEquals(testValue, propDefault.getOauth2ClientRegistrationId());
    }
}