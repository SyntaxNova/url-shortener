package com.atharva.urlshortener.controller;

import com.atharva.urlshortener.service.UrlService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    // Create short URL
    @PostMapping("shorten")
    public String shorten(@RequestBody String longUrl) {
        return service.shortenUrl(longUrl);
    }

    // Redirect to original URL
    @GetMapping("{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String longUrl = service.getOriginalUrl(shortCode);

        return ResponseEntity
                .status(302)
                .location(URI.create(longUrl))
                .build();
    }
}