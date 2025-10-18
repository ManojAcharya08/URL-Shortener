package com.URL_Shortener.interfaces;

import com.URL_Shortener.model.Url;

public interface UrlShortenerService {

    Url shortenUrl(String longUrl);

    Url getOriginalUrl(String shortCode);
}
