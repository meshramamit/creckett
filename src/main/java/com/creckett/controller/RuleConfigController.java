/**
 * 
 */
package com.creckett.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author vkarmani
 *
 */
public class RuleConfigController implements Controller {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource path = resourceLoader.getResource("rulesConfig.xml");
		File ruleFile = path.getFile();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(ruleFile);
			byte[] fileBytes = new byte[(int) ruleFile.length()];  
			fis.read(fileBytes);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("ruleConfigXML", new String(fileBytes));
			return new ModelAndView("RuleConfig", model);
		}finally{
			if (fis!=null){
				fis.close();
			}
		}
	}

}
