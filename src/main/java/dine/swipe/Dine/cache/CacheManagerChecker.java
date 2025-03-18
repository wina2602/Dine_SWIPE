package dine.swipe.Dine.cache;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CacheManagerChecker {

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void checkCacheManager() {
        System.out.println("CacheManager class: " + cacheManager.getClass().getName());
    }
}
