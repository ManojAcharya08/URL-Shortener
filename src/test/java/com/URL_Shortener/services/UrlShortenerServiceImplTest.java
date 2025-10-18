package com.URL_Shortener.services;

import com.URL_Shortener.exceptions.UrlNotFoundException;
import com.URL_Shortener.repository.UrlRepository;
import com.URL_Shortener.model.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)  // Enable Mockito in JUnit 5
class UrlShortenerServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlShortenerServiceImpl urlShortenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shortenUrl_ShouldReturnSavedUrl() {
        String longUrl = "http://example.com";
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode("abc123");

        when(urlRepository.existsByShortCode(anyString())).thenReturn(false);
        when(urlRepository.save(any(Url.class))).thenReturn(url);

        Url result = urlShortenerService.shortenUrl(longUrl);

        assertNotNull(result);
        assertEquals("abc123", result.getShortCode());
        assertEquals(longUrl, result.getLongUrl());
        verify(urlRepository).existsByShortCode(anyString());
        verify(urlRepository).save(any(Url.class));
    }

    @Test
    void getOriginalUrl_ShouldReturnUrl() {
        String shortCode = "abc123";
        Url url = new Url();
        url.setShortCode(shortCode);
        url.setLongUrl("http://example.com");

        when(urlRepository.findByShortCode(shortCode)).thenReturn(Optional.of(url));

        Url result = urlShortenerService.getOriginalUrl(shortCode);

        assertNotNull(result);
        assertEquals(shortCode, result.getShortCode());
        assertEquals("http://example.com", result.getLongUrl());
        verify(urlRepository).findByShortCode(shortCode);
    }

    @Test
    void getOriginalUrl_ShouldThrowException() {
        String shortCode = "notExist";

        when(urlRepository.findByShortCode(shortCode)).thenReturn(Optional.empty());

        // Call the actual service method and assert exception
        assertThrows(UrlNotFoundException.class, () -> {
            urlShortenerService.getOriginalUrl(shortCode);
        });

        // Verify that repository method was called exactly once
        verify(urlRepository).findByShortCode(shortCode);
    }
}
