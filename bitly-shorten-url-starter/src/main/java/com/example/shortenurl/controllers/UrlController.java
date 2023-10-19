package com.example.shortenurl.controllers;

import com.example.shortenurl.dtos.ResolveShortenUrlRequestDto;
import com.example.shortenurl.dtos.ResolveShortenUrlResponseDto;
import com.example.shortenurl.dtos.ShortenUrlRequestDto;
import com.example.shortenurl.dtos.ShortenUrlResponseDto;
import com.example.shortenurl.services.UrlService;

public class UrlController {

    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto requestDto) {
        return null;
    }

    public ResolveShortenUrlResponseDto resolveShortenedUrl(ResolveShortenUrlRequestDto requestDto) {
        return null;
    }
}
