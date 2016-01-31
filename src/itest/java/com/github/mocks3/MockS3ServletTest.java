package com.github.mocks3;

import java.io.IOException;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Assert;
import org.junit.Test;

public class MockS3ServletTest {
    private String serverUrl = "http://localhost:9000";

    @Test
    public void testServerConnect() throws IOException {
        final Response response = Request.Get(serverUrl).execute();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.returnResponse().getStatusLine().getStatusCode() != 500);
    }

    @Test
    public void testBucketAccess() throws IOException {
        final Response response = Request.Get(serverUrl + "/sbucket").execute();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.returnResponse().getStatusLine().getStatusCode() != 500);
    }

    @Test
    public void testBucketCreate() throws IOException {
        final Response response = Request.Get(serverUrl + "/sbucket").execute();
        Assert.assertNotNull(response);
        Assert.assertTrue(response.returnResponse().getStatusLine().getStatusCode() != 500);
    }
}
