package com.github.mocks3;

import com.amazonaws.services.s3.model.Bucket;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AmazonS3ClientMockTest {
    private AmazonS3ClientMock amazonS3Client;

    @Before
    public void setUp() throws Exception {
        amazonS3Client = new AmazonS3ClientMock();
        amazonS3Client.setEndpoint("localhost:9000/");
    }

    @Test
    public void testBucketExists() {
        final boolean bucketExist = amazonS3Client.doesBucketExist("mybucket");
        assertFalse(bucketExist);
    }

    @Test
    public void testCreateBucket() {
        final Bucket bucket = amazonS3Client.createBucket("mybucket");
        assertNotNull(bucket);
        assertTrue(amazonS3Client.doesBucketExist("mybucket"));
    }


//    @Test
//    public void testHeadBucket() {
//        final HeadBucketRequest headBucketRequest = new HeadBucketRequest("my_bucket");
//        final HeadBucketResult headBucketResult = amazonS3Client.headBucket(headBucketRequest);
//    }
}
