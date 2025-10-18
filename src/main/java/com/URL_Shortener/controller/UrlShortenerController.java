package com.URL_Shortener.controller;

import com.URL_Shortener.model.Url;
import com.URL_Shortener.interfaces.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    // Use a DTO class to receive JSON containing the long URL
    public static class UrlRequest {
        private String longUrl;

        public String getLongUrl() {
            return longUrl;
        }

        public void setLongUrl(String longUrl) {
            this.longUrl = longUrl;
        }
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody UrlRequest urlRequest) {
        Url url = urlShortenerService.shortenUrl(urlRequest.getLongUrl());
        return ResponseEntity.ok(url);
    }

    // Changed path to "/original/{shortCode}" for clarity and RESTfulness
    @GetMapping("/original/{shortCode}")
    public ResponseEntity<?> redirectToLongUrl(@PathVariable String shortCode) {
        Url url = urlShortenerService.getOriginalUrl(shortCode);
        return ResponseEntity.status(302)
                .header("Location", url.getLongUrl())
                .build();
    }
}
