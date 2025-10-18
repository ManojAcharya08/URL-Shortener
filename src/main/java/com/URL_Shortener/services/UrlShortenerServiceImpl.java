package com.URL_Shortener.services;

import com.URL_Shortener.exceptions.UrlNotFoundException;
import com.URL_Shortener.repository.UrlRepository;
import com.URL_Shortener.interfaces.UrlShortenerService;
import com.URL_Shortener.model.Url;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlRepository urlRepository;

    public UrlShortenerServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public Url shortenUrl(String longUrl) {
        Url url = new Url();
        url.setLongUrl(longUrl);
        url.setShortCode(generateUniqueCode());
        return urlRepository.save(url);
    }

    @Override
    public Url getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException("URL not found for code: " + shortCode));
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 6); // simple short code generator
    }
}
