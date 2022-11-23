package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeoServiceImplTest {

    private static final String IP_RUS = "172.";
    private static final String IP_USA = "96.";

    @Spy
    private GeoServiceImpl geoService;
    
    @Test
    void byIpRus() {
        assertEquals(new Location("Moscow", Country.RUSSIA, null, 0).getCountry(),
                geoService.byIp(IP_RUS).getCountry());
    }

    @Test
    void byIpUsa() {
        assertEquals(new Location("New York", Country.USA, null,  0).getCountry(),
                geoService.byIp(IP_USA).getCountry());
    }
}