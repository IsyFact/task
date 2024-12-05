package de.bund.bva.isyfact.task;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.KATEGORIE_JOURNAL;
import static de.bund.bva.isyfact.util.logging.CombinedMarkerFactory.createKategorieMarker;

@Component
public class TestHostHandlerTasks {

    public static final String SCHLUESSEL = "ISYTA99999";

    private static final Logger LOG = LoggerFactory.getLogger(TestHostHandlerTasks.class);

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    @Timed(value = "testHostHandlerTasks-scheduledTaskWithCorrectHostname")
    public void scheduledTaskWithCorrectHostname() {
        LOG.info(createKategorieMarker(KATEGORIE_JOURNAL), SCHLUESSEL, "test task - scheduled - correct host - executed at {}", LocalDateTime.now());
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void scheduledTaskWithWrongHostname() {
        LOG.info(createKategorieMarker(KATEGORIE_JOURNAL), SCHLUESSEL, "test task - scheduled - wrong host - executed at {}", LocalDateTime.now());
    }

}
