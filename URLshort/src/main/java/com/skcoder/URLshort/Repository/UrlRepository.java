package com.skcoder.URLshort.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skcoder.URLshort.Entity.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Long>{



	Optional<UrlEntity> findByurlString(String urlString);

}
