package com.snipreel.mocks3;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

class S3ObjectsSource {
    
    private static final Logger log = Logger.getLogger(S3ObjectsSource.class.getName());
    
    private static final S3ObjectsSource instance = new S3ObjectsSource();
    static final S3ObjectsSource getInstance () { return instance; }
    
    S3BucketSource getStoreSource (String storeClassName) {
        return new S3BucketMemorySource(storeClassName);
    }
    
    S3ObjectSource getStore (String storeClassName) {
        if ( storeClassName != null ) {
            try {
                Class<?> cls = Class.forName(storeClassName);
                if ( S3ObjectSource.class.isAssignableFrom(cls) ) {
                    return S3ObjectSource.class.cast(cls.newInstance());
                }
            } catch (ClassNotFoundException e) {
                log.severe("Unable to find class " + storeClassName);
            } catch (InstantiationException e) {
                log.severe("Unable to create class " + storeClassName);
            } catch (IllegalAccessException e) {
                log.severe("Unable to create class " + storeClassName);
            }
        }
        return NULL;
    }
    
    static final S3BucketSource NULL_SOURCE = new S3BucketSource () {
        public S3ObjectSource addBucket(String bucket) { return null; }
        public void deleteBucket(String bucket) {}
        public S3ObjectSource getBucket(String bucket) {return null;}
        public List<String> getBucketNames() {return Collections.<String>emptyList();}
    };
    
    static final S3ObjectSource NULL = new S3ObjectSource () {
        public void addObject(String key,byte[] data) {}
        public byte[] getObject(String key)           { return null; }
        public boolean hasObject(String key)          { return false; }
        public List<String> getKeys ()              { return Collections.<String>emptyList(); }
    };

}
