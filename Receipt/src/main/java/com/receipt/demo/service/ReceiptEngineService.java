package com.receipt.demo.service;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
// Import the MathTool
import org.apache.velocity.tools.generic.MathTool;
import org.springframework.stereotype.Service;

import com.receipt.demo.model.Transaction;

@Service
public class ReceiptEngineService {

	private final VelocityEngine velocityEngine;

	public ReceiptEngineService() {
		this.velocityEngine = new VelocityEngine();
		Properties props = new Properties();
		props.setProperty("resource.loader", "class");
		props.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.init(props);
	}

	public String generateReceipt(Transaction transaction, String templateName) {
		VelocityContext context = new VelocityContext();
		context.put("poslog", transaction);

		// Use the imported class
		context.put("math", new MathTool());

		Template template = velocityEngine.getTemplate("templates/" + templateName);

		StringWriter writer = new StringWriter();
		template.merge(context, writer);

		return writer.toString();
	}
}