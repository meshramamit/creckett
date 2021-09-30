package com.creckett.core.codec;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.creckett.core.exception.TechnicalException;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerCodecStrategy implements CodecStrategy, InitializingBean {

	private Configuration configuration;

	private String templateLoaderPath;
	

	public String getTemplateLoaderPath() {
		return templateLoaderPath;
	}

	public void setTemplateLoaderPath(String templateLoaderPath) {
		this.templateLoaderPath = templateLoaderPath;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public String encode(Object objectToEncode) {
		Map<String, Map<String, Object>> model = (Map<String, Map<String, Object>>) objectToEncode;
		String templateName = model.keySet().iterator().next();
		Template template = null;
		Writer writer = new StringWriter();
		try {
			template = configuration.getTemplate(templateName +".ftl");
			template.process(model.values().iterator().next(), writer);
			writer.flush();
		} catch (IOException e) {
			throw new TechnicalException(
					"IOException occurred while encoding : " + model
							+ " with freemarker ", e);
		} catch (TemplateException e) {
			throw new TechnicalException(
					"TemplateException occurred while encoding : " + model
							+ " with freemarker ", e);
		}
		return writer.toString();
	}

	@Override
	public Object decode(Object objectToDecode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		configuration = new Configuration();
		configuration.setTemplateLoader(getTemplateLoaderForPath());
	}

	protected TemplateLoader getTemplateLoaderForPath() {
		// Try to load via the file system, fall back to SpringTemplateLoader
		// (for hot detection of template changes, if possible).
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource path = resourceLoader.getResource(templateLoaderPath);
		File file;
		try {
			file = path.getFile();
			return new FileTemplateLoader(file);
		} catch (IOException e) {
			throw new TechnicalException(
					"IOException occurred while encoding with freemarker ", e);
		} // will fail if not resolvable in the file
			// system

	}

}
