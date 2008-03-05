package com.snipreel.mocks3;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of DataStore that keeps things in memory, meaning each restart of 
 * a servlet loses all previous data.
 *
 */
class MemoryDataStore implements DataStore {
    
    private static final ConcurrentHashMap<String, byte[]> cache = 
        new ConcurrentHashMap<String,byte[]>();

    public void addData (String bucket, String key,byte[] data) {
        String cacheKey = cacheKey(bucket, key);
        if ( data == null ) {
            cache.remove(cacheKey);
        } else {
            cache.put(cacheKey, data);
        }
    }

    public byte[] getData (String bucket, String key) {
        return cache.get(cacheKey(bucket, key));
    }

    public boolean hasData (String bucket, String key) {
        return cache.containsKey(cacheKey(bucket, key));
    }
    
    private String cacheKey (String bucket, String key) {
        return bucket + "-" + key;
    }

}
