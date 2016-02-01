package com.github.mocks3;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.amazonaws.services.s3.model.HeadBucketResult;
import com.amazonaws.services.s3.model.ListBucketsRequest;

public class AmazonS3ClientMock extends AmazonS3Client {
    private final Set<Bucket> buckets = Collections.synchronizedSet(new HashSet<>());
    private String endpoint;

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Bucket createBucket(CreateBucketRequest request) throws AmazonClientException {
        rejectNull(request, "CreateBucketRequest was null");

        final Bucket bucket = new Bucket(request.getBucketName());
        buckets.add(bucket);
        return bucket;
    }

    @Override
    public void deleteBucket(DeleteBucketRequest request) throws AmazonClientException {
        rejectNull(request, "DeleteBucketRequest was null");

        final Bucket bucket = buckets.stream()
            .filter(b -> b.getName().equalsIgnoreCase(request.getBucketName()))
            .findFirst()
            .orElse(null);

        if (bucket != null) {
            buckets.remove(bucket);
        }
    }

    @Override
    public boolean doesBucketExist(String bucketName) throws AmazonClientException {
        rejectNull(bucketName, "Bucket name was null");
        return buckets.stream().filter(b -> bucketName.equalsIgnoreCase(b.getName())).count() > 0;
    }

    @Override
    public HeadBucketResult headBucket(HeadBucketRequest headBucketRequest) throws AmazonClientException {
        return super.headBucket(headBucketRequest);
    }


    @Override
    public String getBucketLocation(String bucketName) throws AmazonClientException {
        return super.getBucketLocation(bucketName);
    }

    @Override
    public List<Bucket> listBuckets(ListBucketsRequest request) throws AmazonClientException {
        rejectNull(request, "ListBucketsRequest was null");
        return buckets.stream().collect(Collectors.toList());
    }

    private void rejectNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) throw new IllegalArgumentException(errorMessage);
    }
}
