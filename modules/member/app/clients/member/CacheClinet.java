package clients.member;

import javax.inject.Inject;

import play.cache.CacheApi;

public class CacheClinet {

    @Inject
    private CacheApi cache;
    
    public void set(String key, Object value) {
        cache.set(key, value);
    }
    
    public Object get(String key) {
        return cache.get(key);
    }
    
    public void remove(String key) {
        cache.remove(key);
    }
}
