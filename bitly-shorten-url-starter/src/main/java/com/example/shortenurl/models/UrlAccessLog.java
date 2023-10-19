package com.example.shortenurl.models;

import lombok.Data;

@Data
public class UrlAccessLog extends BaseModel{
    private ShortenedUrl shortenedUrl;
    private long accessedAt;
}
