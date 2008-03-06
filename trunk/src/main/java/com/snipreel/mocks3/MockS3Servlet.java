package com.snipreel.mocks3;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MockS3Servlet extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(MockS3Servlet.class.getName());
    
    private DataStoreSource storeSource = DataStoreFactory.NULL_SOURCE;
    
    @Override
    public void init () {
        String storeType = getServletConfig().getInitParameter(DataStore.class.getName());
        this.storeSource = DataStoreFactory.getInstance().getStoreSource(storeType);
        log.fine("Created " + this.storeSource + " from " + storeType);
    }
    
    private DataStore getStore (String bucket) {
        return storeSource.getStore(bucket);
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse rsp) {
        doGet(req, rsp, true);
    }
    
    @Override
    protected void doHead (HttpServletRequest req, HttpServletResponse rsp) {
        doGet(req, rsp, false);
    }

    private void doGet (HttpServletRequest req, HttpServletResponse rsp, boolean includeContent) {
        S3Info info = new S3Info(req.getLocalName(), req.getRequestURI());
        if ( info.getBucket().length() == 0 ) {
            // to do: report available buckets in the body of the response. 
            rsp.setStatus(HttpServletResponse.SC_OK);
        } else {
            DataStore store = getStore(info.getBucket());
            if ( store == null ) {
                rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                if ( info.getKey().length() == 0 ) {
                    rsp.setStatus(HttpServletResponse.SC_OK);
                    // to do: report objects in the body of the response
                } else {
                    byte[] data = store.getData(info.getKey());
                    if ( data != null ) {
                        rsp.setStatus(HttpServletResponse.SC_OK);
                        if ( includeContent ) writeResponse(rsp, data);
                    } else {
                        rsp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }
                }
            }
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
    protected void doDelete (HttpServletRequest req, HttpServletResponse rsp) {
    }
}
