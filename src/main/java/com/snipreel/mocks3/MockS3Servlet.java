package com.snipreel.mocks3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(displayName = "MockS3servlet", urlPatterns = "/*")
public class MockS3Servlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MockS3Servlet.class.getName());
    private S3RequestFactory requestFactory;

    @Override
    public void init() {
        String storeType = getServletConfig().getInitParameter(S3ObjectSource.class.getName());
        S3BucketSource storeSource = S3ObjectsSource.getInstance().getStoreSource(storeType);
        log.info("Created " + storeSource + " from " + storeType);
        this.requestFactory = new S3RequestFactory(storeSource);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        requestFactory.getHandler(req, resp).doit();
    }
}
