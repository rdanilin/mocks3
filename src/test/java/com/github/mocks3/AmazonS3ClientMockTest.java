package com.github.mocks3;

import com.amazonaws.services.s3.model.Bucket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AmazonS3ClientMockTest {
    private AmazonS3ClientMock amazonS3Client;

    @Before
    public void setUp() throws Exception {
        amazonS3Client = new AmazonS3ClientMock();
        amazonS3Client.setEndpoint("localhost:9000/");
    }

    @Test
    public void testBucketExists() {
        boolean bucketExist = amazonS3Client.doesBucketExist("my_bucket");
        Assert.assertFalse(bucketExist);
    }

    @Test
    public void testCreateBucket() {
        final Bucket bucket = amazonS3Client.createBucket("my-bucket");
        Assert.assertNotNull(bucket);
    }

//    @Test
//    public void testHeadBucket() {
//        final HeadBucketRequest headBucketRequest = new HeadBucketRequest("my_bucket");
//        final HeadBucketResult headBucketResult = amazonS3Client.headBucket(headBucketRequest);
//    }
}
