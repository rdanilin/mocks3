package com.github.mocks3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.amazonaws.services.s3.model.HeadBucketResult;

public class AmazonS3ClientMock extends AmazonS3Client {
    private final Set<Bucket> buckets = Collections.synchronizedSet(new HashSet<>());
    private String endpoint;

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Bucket createBucket(CreateBucketRequest request) throws AmazonClientException {
        if (request == null) {
            throw new IllegalArgumentException();
        }
        return new Bucket(request.getBucketName());
    }

    @Override
    public boolean doesBucketExist(String bucketName) throws AmazonClientException {
        if (bucketName == null) {
            throw new IllegalArgumentException("bucketName is null");
        }
        return buckets.stream().filter(b -> bucketName.equalsIgnoreCase(b.getName())).count() > 0;
    }

    @Override
    public HeadBucketResult headBucket(HeadBucketRequest headBucketRequest) throws AmazonClientException {
        return super.headBucket(headBucketRequest);
    }
}
