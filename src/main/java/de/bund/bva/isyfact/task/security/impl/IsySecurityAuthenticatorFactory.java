package de.bund.bva.isyfact.task.security.impl;

import de.bund.bva.isyfact.security.oauth2.client.Authentifizierungsmanager;
import de.bund.bva.isyfact.task.config.IsyTaskConfigurationProperties;
import de.bund.bva.isyfact.task.konstanten.HinweisSchluessel;
import de.bund.bva.isyfact.task.security.Authenticator;
import de.bund.bva.isyfact.task.security.AuthenticatorFactory;
import de.bund.bva.isyfact.util.text.MessageSourceHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.KATEGORIE_SICHERHEIT;
import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.createKategorieMarker;

/**
 * Creates Authenticators for authentication with isy-security.
 */
public class IsySecurityAuthenticatorFactory implements AuthenticatorFactory {
    private static final Logger LOG = LoggerFactory.getLogger(IsySecurityAuthenticatorFactory.class);

    private final IsyTaskConfigurationProperties configurationProperties;

    private final Authentifizierungsmanager authentifizierungsmanager;

    /**
     * Creates new instance.
     *
     * @param configurationProperties   {@link IsyTaskConfigurationProperties} provides credentials
     * @param authentifizierungsmanager {@link Authentifizierungsmanager} for authentication
     */
    public IsySecurityAuthenticatorFactory(
            IsyTaskConfigurationProperties configurationProperties,
            Authentifizierungsmanager authentifizierungsmanager
    ) {
        this.configurationProperties = configurationProperties;
        this.authentifizierungsmanager = authentifizierungsmanager;
    }

    /**
     * Provides an implementation of an {@link Authenticator} for a task, if the necessary credentials are configured.
     * If no credentials can be found, a {@link NoOpAuthenticator} will be returned.
     *
     * @param taskId Id of the task
     * @return {@link Authenticator} for task, return {@link NoOpAuthenticator} if no credentials have been found.
     */
    public synchronized Authenticator getAuthenticator(String taskId) {
        if (configurationProperties.getTasks().get(taskId) != null) {
            String oauth2ClientRegistrationId = configurationProperties.getTasks().get(taskId).getOauth2ClientRegistrationId();
            if (StringUtils.hasText(oauth2ClientRegistrationId)) {
                return new IsySecurityAuthenticator(authentifizierungsmanager, oauth2ClientRegistrationId);
            }
        }
        String defaultOauth2ClientRegistrationId = configurationProperties.getDefault().getOauth2ClientRegistrationId();
        if (StringUtils.hasText(defaultOauth2ClientRegistrationId)) {
            String nachricht = MessageSourceHolder
                    .getMessage(HinweisSchluessel.VERWENDE_STANDARD_KONFIGURATION, "oauth2ClientRegistrationId");
            LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), HinweisSchluessel.VERWENDE_STANDARD_KONFIGURATION, nachricht);
            return new IsySecurityAuthenticator(authentifizierungsmanager, defaultOauth2ClientRegistrationId
            );
        } else {
            LOG.info(createKategorieMarker(KATEGORIE_SICHERHEIT), HinweisSchluessel.VERWENDE_KEINE_AUTHENTIFIZIERUNG,
                    MessageSourceHolder.getMessage(HinweisSchluessel.VERWENDE_KEINE_AUTHENTIFIZIERUNG));
            return new NoOpAuthenticator();
        }
    }
}
