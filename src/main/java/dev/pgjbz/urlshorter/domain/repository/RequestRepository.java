package dev.pgjbz.urlshorter.domain.repository;

public interface RequestRepository {
    
    boolean save(Long urlId, String headers);

}
