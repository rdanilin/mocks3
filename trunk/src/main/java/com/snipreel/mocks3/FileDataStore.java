package com.snipreel.mocks3;

/**
 * Implementation of a DataStore that stores data in a file
 * to allow for persistent storage across a restart of a service.
 * Absolutely NO attempt is made to provide for efficient storage
 * or retrieval of data.  This is not intended to be a useful
 * file storage class; it is intended merely to provide a temporarily
 * persistent storage means, allowing testing of S3 across 
 * service restarts
 */
class FileDataStore implements DataStore {

    public void addData(String key,byte[] data) {
    }

    public byte[] getData(String key) {
        return null;
    }

    public boolean hasData(String key) {
        return false;
    }

}
