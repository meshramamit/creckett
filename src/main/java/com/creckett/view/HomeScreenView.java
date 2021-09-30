/**
 * Copyright InnoTech Solutions Pvt. Ltd 2011. All rights reserved. No part of this program may be photocopied 
 * reproduced or translated to another program language without prior written consent of InnoTech Solutions Pvt. Ltd
 *
 * FILE         : HomeScreenView.java
 *
 * PACKAGE      : com.creckett.view
 *
 * AUTHOR(S)    : Dhaval P Shah - shah_d_p@yahoo.com
 *
 * VERSION      : 1.0
 *
 * REVISION LOG :
 *
 *    Date          By         Version  TicketId        Description
 *    ------------  ---------  -------  --------  --------------------------------------------------
 *    Apr 30, 2011	Dhaval	   1.0      NA        Created first cut of code.
 *
 */
package com.creckett.view;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.web.servlet.View;

import com.creckett.logsmgmt.LoggerInstancesManager;

/**
 * @author Dhaval
 *
 */
public class HomeScreenView implements View {

	private static final Logger LOG = LoggerInstancesManager.getLogger(HomeScreenView.class);
	/**
	 * 
	 */
	public HomeScreenView() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#getContentType()
	 */
	@Override
	public String getContentType() {
		return "text/xml";
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("Entering render") ;
		Document document = createDocument(model);
		String xmlAsString = document.asXML() ;
		response.setContentLength( xmlAsString.length() ) ;
		PrintWriter out = new PrintWriter( response.getOutputStream() );
	    out.print( xmlAsString );
	    out.flush();
	    out.close(); 
		LOG.info("Leaving render") ;
	}

	private Document createDocument(Map model) {
		LOG.info("Entering createDocument") ;
		Document document = DocumentHelper.createDocument() ;
		
		return document ;
	}	
}
