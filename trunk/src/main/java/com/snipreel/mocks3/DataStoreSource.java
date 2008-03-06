package com.snipreel.mocks3;

import java.util.List;

/**
 * Interface for retrieving data stores based on bucket names
 */
interface DataStoreSource {

    /**
     * Retrieve the data store for the bucket, null if there is no such bucket
     */
    DataStore getStore (String bucket);
    
    /**
     * Add a data store for the provided bucket, if it doesn't already exist.
     * If the named bucket already exists, this will return null
     * @param bucket
     * @return
     */
    DataStore addStore (String bucket);
    
    /**
     * Delete the named store.
     */
    void deleteStore (String bucket);
    
    /**
     * Retrieve a list of the bucket names for which stores exist
     */
    List<String> getStoreNames ();

}
