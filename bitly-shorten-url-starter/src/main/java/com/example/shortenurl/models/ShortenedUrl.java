package com.example.shortenurl.models;

import lombok.Data;

@Data
public class ShortenedUrl extends BaseModel{
    private String originalUrl;
    private String shortUrl;
    private long expiresAt;
    private User user;
}
