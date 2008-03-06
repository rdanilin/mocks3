package com.snipreel.mocks3;

import java.util.List;

class MemoryDataStoreSource implements DataStoreSource {
    
    MemoryDataStoreSource (String storeType) {
        this.storeType = storeType;
    }
    private final String storeType;

    private static final NamedCache<DataStore> cache = new NamedCache<DataStore>();

    public DataStore addStore(String bucket) {
        DataStore store = DataStoreFactory.getInstance().getStore(storeType);
        DataStore added = cache.putIfAbsent(bucket, store);
        if ( added == store ) return added;
        else return null;
    }

    public void deleteStore(String bucket) { cache.remove(bucket); }
    public DataStore getStore(String bucket) { return cache.get(bucket); }
    public List<String> getStoreNames() { return cache.getKeys(); }
}
