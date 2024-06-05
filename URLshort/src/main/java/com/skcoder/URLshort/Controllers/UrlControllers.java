package com.skcoder.URLshort.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.skcoder.URLshort.Services.UrlService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/")
public class UrlControllers {

    private final UrlService urlService;
    private final CacheManager cacheManager;
    private static final Logger logger = LoggerFactory.getLogger(UrlControllers.class);

    public UrlControllers(UrlService urlService, CacheManager cacheManager) {
        this.urlService = urlService;
        this.cacheManager = cacheManager;
    }

    //add the url to the database and getback the short url
    @PostMapping("url/add")
    public ResponseEntity<String> shortTheURL(@RequestParam String url) {
        String shortUrl = urlService.addurl(url);
        cacheManager.getCache("UrlMapping").put(shortUrl, url);
        return ResponseEntity.ok().body("{\"shortUrl\": \"" +"localhost:8080"+ shortUrl + "\"}");
    }

    //getting the short url
    @GetMapping("{shortUrl}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = null;
        if (cacheManager.getCache("UrlMapping").get(shortUrl) != null) {
            originalUrl = cacheManager.getCache("UrlMapping").get(shortUrl).get().toString();
            logger.info("Cache hit occurred for short URL: {}", shortUrl);
        } else{
            originalUrl = urlService.getUrlString(shortUrl);
            cacheManager.getCache("UrlMapping").put(shortUrl, originalUrl);
            logger.info("Cache miss occurred  short URL: {}", shortUrl);
                 }
        logger.debug("Original url: {}", originalUrl);
        return new RedirectView(originalUrl);
    }
}
