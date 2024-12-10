package de.bund.bva.isyfact.task.konfiguration.impl;

import de.bund.bva.isyfact.task.exception.HostNotApplicableException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocalHostHandlerImplTest {

    @Test
    void isHostApplicableUnkownHostException() {
        LocalHostHandlerImpl handler = new LocalHostHandlerImpl();
        try (MockedStatic<InetAddress> utilities = Mockito.mockStatic(InetAddress.class)) {
            utilities.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());
            assertThrows(HostNotApplicableException.class, () -> {
                handler.isHostApplicable("TEST");
            });
        }

    }


    @Test
    void isHostApplicableNull() throws HostNotApplicableException {
        LocalHostHandlerImpl handler = new LocalHostHandlerImpl();
        try (MockedStatic<InetAddress> utilities = Mockito.mockStatic(InetAddress.class)) {
            utilities.when(InetAddress::getLocalHost).thenReturn(null);
            assertFalse(handler.isHostApplicable("TEST"));

        }
    }

    @Test
    void isHostApplicableHostnameNull() throws HostNotApplicableException {
        InetAddress address = mock(InetAddress.class);
        when(address.getHostName()).thenReturn(null);
        LocalHostHandlerImpl handler = new LocalHostHandlerImpl();
        try (MockedStatic<InetAddress> utilities = Mockito.mockStatic(InetAddress.class)) {
            utilities.when(InetAddress::getLocalHost).thenReturn(address);
            assertFalse(handler.isHostApplicable("TEST"));
        }

    }
}