package com.skcoder.URLshort.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.skcoder.URLshort.Entity.UrlEntity;
import com.skcoder.URLshort.Repository.UrlRepository;

@Service
public class UrlService {

    private final ShortURLService shorturlService = new ShortURLService();

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<UrlEntity> findurl(String url) {
        return urlRepository.findByurlString(url);
    }

    public String addurl(String urlString) {
        Optional<UrlEntity> urlEntity = urlRepository.findByurlString(urlString);

        if (urlEntity.isPresent()) {
            return shorturlService.idToShortURL(urlEntity.get().getId());
        } else {
            UrlEntity savedEntity = urlRepository.save(new UrlEntity(0, urlString));
            return shorturlService.idToShortURL(savedEntity.getId());
        }
    }

    public String getUrlString(String shortUrl) {
        long id = shorturlService.shortURLtoID(shortUrl);
        Optional<UrlEntity> urlEntity = urlRepository.findById(id);
        if (urlEntity.isPresent()) {
            return urlEntity.get().getUrlString();
        } else {
            throw new IllegalArgumentException("Short URL not found: " + shortUrl);
        }
    }
}
