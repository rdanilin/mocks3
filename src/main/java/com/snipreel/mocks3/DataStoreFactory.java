package com.snipreel.mocks3;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

class DataStoreFactory {
    
    private static final Logger log = Logger.getLogger(DataStoreFactory.class.getName());
    
    private static final DataStoreFactory instance = new DataStoreFactory();
    static final DataStoreFactory getInstance () { return instance; }
    
    DataStoreSource getStoreSource (String storeClassName) {
        return new MemoryDataStoreSource(storeClassName);
    }
    
    DataStore getStore (String storeClassName) {
        if ( storeClassName != null ) {
            try {
                Class<?> cls = Class.forName(storeClassName);
                if ( DataStore.class.isAssignableFrom(cls) ) {
                    return DataStore.class.cast(cls.newInstance());
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
    
    static final DataStoreSource NULL_SOURCE = new DataStoreSource () {
        public DataStore addStore(String bucket) { return null; }
        public void deleteStore(String bucket) {}
        public DataStore getStore(String bucket) {return null;}
        public List<String> getStoreNames() {return Collections.<String>emptyList();}
    };
    
    static final DataStore NULL = new DataStore () {
        public void addData(String key,byte[] data) {}
        public byte[] getData(String key)           { return null; }
        public boolean hasData(String key)          { return false; }
        public List<String> getKeys ()              { return Collections.<String>emptyList(); }
    };

}
