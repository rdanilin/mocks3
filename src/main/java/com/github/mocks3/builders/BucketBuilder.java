package com.github.mocks3.builders;

import java.util.Date;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.Owner;

public class BucketBuilder {
    private String name = null;
    private Owner owner = null;
    private Date creationDate = null;

    private BucketBuilder() {
    }

    public static BucketBuilder aBucket() {
        return new BucketBuilder();
    }

    public BucketBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BucketBuilder withOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public BucketBuilder withCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public BucketBuilder but() {
        return aBucket().withName(name).withOwner(owner).withCreationDate(creationDate);
    }

    public Bucket build() {
        Bucket bucket = new Bucket();
        bucket.setName(name);
        bucket.setOwner(owner);
        bucket.setCreationDate(creationDate);
        return bucket;
    }
}
