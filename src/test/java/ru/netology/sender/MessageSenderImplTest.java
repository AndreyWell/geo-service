package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    private static final String IP_RUS = "172.";
    private static final String IP_USA = "96.";

    @Mock
    private LocalizationServiceImpl localizationService;

    @Mock
    private GeoServiceImpl geoService;

    private MessageSender messageSender;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void sendRusMessage() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", IP_RUS);
        Mockito.when(geoService.byIp(Mockito.startsWith(IP_RUS))).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Mockito.eq(Country.RUSSIA))).thenReturn("Добро пожаловать");

        String actualResult = messageSender.send(headers);

        String expectedResult = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void sendUsMessage() {
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", IP_USA);
        Mockito.when(geoService.byIp(Mockito.startsWith(IP_USA))).thenReturn(
                new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(Mockito.eq(Country.USA))).thenReturn("Welcome");

        String actualResult = messageSender.send(headers);

        String expectedResult = localizationService.locale(Country.USA);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}