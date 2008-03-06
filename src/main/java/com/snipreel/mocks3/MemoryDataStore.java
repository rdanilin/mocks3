package com.snipreel.mocks3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of DataStore that keeps things in memory, meaning each restart of 
 * a servlet loses all previous data.
 *
 */
class MemoryDataStore implements DataStore {
    
    private static final ConcurrentHashMap<String, byte[]> cache = 
        new ConcurrentHashMap<String,byte[]>();

    public void addData (String key,byte[] data) {
        if ( data == null ) {
            cache.remove(key);
        } else {
            cache.put(key, data);
        }
    }

    public byte[] getData (String key) {
        return cache.get(key);
    }

    public boolean hasData (String key) {
        return cache.containsKey(key);
    }
    
    public List<String> getKeys () {
        List<String> result = new ArrayList<String>();
        result.addAll(cache.keySet());
        Collections.sort(result);
        return result;
    }
    
}
