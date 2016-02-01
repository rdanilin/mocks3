package com.github.mocks3;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.amazonaws.services.s3.model.Bucket;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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

    @Test
    public void testDeleteBucket() {
        amazonS3Client.createBucket("mybucket");
        amazonS3Client.deleteBucket("mybucket");
        assertFalse(amazonS3Client.doesBucketExist("mybucket"));
    }

    @Test
    public void testListBuckets() {
        amazonS3Client.createBucket("mybucket1");
        amazonS3Client.createBucket("mybucket2");

        final List<Bucket> buckets = amazonS3Client.listBuckets();

        assertThat(buckets.stream().map(f -> f.getName()).collect(Collectors.toList()),
            allOf(hasItem("mybucket1"), hasItem("mybucket2")));
    }

    @Test
    public void testPutObject() throws Exception {
        amazonS3Client.createBucket("mybucket");

        amazonS3Client.putObject("mybucket", "testfile", new File("null"));

    }

    //    @Test
//    public void testHeadBucket() {
//        final HeadBucketRequest headBucketRequest = new HeadBucketRequest("my_bucket");
//        final HeadBucketResult headBucketResult = amazonS3Client.headBucket(headBucketRequest);
//    }
}
