package com.linmh.bookstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "source-dir")
public class ResourceConfig {
    private String imageDir;
    private String imageMapUrl;

    public String getImageMapUrl() {
        return imageMapUrl;
    }

    public void setImageMapUrl(String imageMapUrl) {
        this.imageMapUrl = imageMapUrl;
    }

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }
}
