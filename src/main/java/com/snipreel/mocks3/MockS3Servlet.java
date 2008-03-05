package com.snipreel.mocks3;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MockS3Servlet extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(MockS3Servlet.class.getName());
    
    private DataStore store = DataStoreFactory.NULL;
    
    @Override
    public void init () {
        String storeType = getServletConfig().getInitParameter(DataStore.class.getName());
        this.store = DataStoreFactory.getInstance().getStore(storeType);
        log.fine("Created " + this.store + " from " + storeType);
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse rsp) {
        S3Info info = new S3Info(req.getLocalName(), req.getRequestURI());
        byte[] data = store.getData(info.getBucket(), info.getKey());
        if ( data != null ) {
            rsp.setStatus(HttpServletResponse.SC_OK);
            writeResponse(rsp, data);
        } else {
            rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    private void writeResponse (HttpServletResponse rsp, byte[] data) {
        try {
            OutputStream os = rsp.getOutputStream();
            os.write(data);
            os.flush();
        } catch (IOException ex) {
            log.warning("IO Problem writing to response");
        }
        
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse rsp) {
    }

    @Override
    protected void doPut (HttpServletRequest req, HttpServletResponse rsp) {
    }

    @Override
    protected void doHead (HttpServletRequest req, HttpServletResponse rsp) {
    }

    @Override
    protected void doDelete (HttpServletRequest req, HttpServletResponse rsp) {
    }
}
