package com.github.mocks3;

import java.io.IOException;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Assert;
import org.junit.Test;

public class S3ServletTest {
    private String serverUrl = "http://localhost:9000";

    @Test
    public void testServerConnect() throws IOException {
        final Response response = Request.Get(serverUrl).execute();
        Assert.assertNotNull(response);
    }
}