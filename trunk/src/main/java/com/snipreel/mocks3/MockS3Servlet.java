package com.snipreel.mocks3;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MockS3Servlet extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(MockS3Servlet.class.getName());
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse rsp) {
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
