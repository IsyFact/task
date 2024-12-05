package de.bund.bva.isyfact.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.KATEGORIE_JOURNAL;
import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.createKategorieMarker;

@Component
public class TestTaskAuthenticationTasks {

    public static final String SCHLUESSEL = "ISYTA99999";

    private static final Logger LOG = LoggerFactory.getLogger(TestTaskAuthenticationTasks.class);

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    @Secured("PRIV_Recht1")
    public void scheduledTaskSecured() {
        LOG.info(createKategorieMarker(KATEGORIE_JOURNAL), SCHLUESSEL, "test task - scheduled - secured - executed at {}", LocalDateTime.now());
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    @Secured("PRIV_Recht1")
    public void scheduledTaskSecuredInsufficientRights() {
        LOG.info(createKategorieMarker(KATEGORIE_JOURNAL), SCHLUESSEL, "test task - scheduled - insufficient rights - executed at {}", LocalDateTime.now());
    }

}
