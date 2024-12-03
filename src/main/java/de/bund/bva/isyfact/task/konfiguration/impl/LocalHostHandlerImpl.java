package de.bund.bva.isyfact.task.konfiguration.impl;

import de.bund.bva.isyfact.task.exception.HostNotApplicableException;
import de.bund.bva.isyfact.task.konfiguration.HostHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The HostHandler is a utility class that checks a host instance.
 */
public class LocalHostHandlerImpl implements HostHandler {
    /**
     * Class logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocalHostHandlerImpl.class);

    /**
     * Checks whether the task is allowed to run on the host.
     */
    @Override
    public synchronized boolean isHostApplicable(String expectedHostName) throws HostNotApplicableException {

        InetAddress inetAdress;

        try {
            inetAdress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new HostNotApplicableException(expectedHostName, e);
        }

        LOG.debug("isHostApplicable: inetAdress: {}", inetAdress);

        if (inetAdress == null) {
            return false;
        }

        String currentHostName = inetAdress.getHostName();

        LOG.debug("isHostApplicable: currentHostName: {}", currentHostName);

        if (currentHostName == null || currentHostName.isEmpty()) {
            LOG.debug("isHostApplicable: inetAdress: {}", inetAdress);
            return false;
        }

        if (!currentHostName.matches(expectedHostName)) {
            LOG.debug("isHostApplicable: hostNames do not match! expectedHostName: {} currentHostName: {}",
                    expectedHostName, currentHostName);
            return false;
        }

        return true;
    }
}
