package com.snipreel.mocks3;

/**
 * Used by MockS3Servlet to store and retrieve bytes from somewhere.
 * NOTE: This does not include "put-if-absent" semantics, as the 
 * S3 service does not support that.
 */
interface DataStore {

    /**
     * Retrieve the bytes stored with the provided key.  Returns null if the key does not exist.
     */
    public byte[] getData (String key);
    
    /**
     * Store the provided data for the provided key.  Overwrites data if already specified.
     * Providing null data removes the value for the key. 
     */
    public void addData (String key, byte[] data);
    
    /**
     * Return whether there is currently data available for the given key.
     */
    public boolean hasData (String key);

}
