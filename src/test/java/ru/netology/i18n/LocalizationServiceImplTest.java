package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private Country countryRus = Country.RUSSIA;
    private Country countryUsa = Country.USA;

    @Spy
    private LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @Test
    void localeRus() {
        assertEquals("Добро пожаловать", localizationService.locale(countryRus));
    }

    @Test
    void localeUsa() {
        assertEquals("Welcome", localizationService.locale(countryUsa));
    }
}