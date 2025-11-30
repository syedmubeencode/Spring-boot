package com.receipt.demo.service;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.receipt.demo.model.Transaction;

@Service
public class ReceiptService {

	private final VelocityEngine velocityEngine;
	private final XmlMapper xmlMapper;

	public ReceiptService(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
		this.xmlMapper = new XmlMapper();
	}

	public String generateReceiptFromXml(String xmlPayload, String templateName) {
		try {
			// 1) XML → Transaction object
			Transaction txn = xmlMapper.readValue(xmlPayload, Transaction.class);

			// 2) Prepare Velocity context
			VelocityContext context = new VelocityContext();
			context.put("txn", txn);

			// 3) Load template (e.g., "receipt.vm")
			Template template = velocityEngine.getTemplate("templates/" + templateName + ".vm");

			// 4) Merge template + data
			StringWriter writer = new StringWriter();
			template.merge(context, writer);

			return writer.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Failed to generate receipt: " + ex.getMessage(), ex);
		}
	}
}
