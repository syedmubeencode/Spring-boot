package com.receipt.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.receipt.demo.service.ReceiptService;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

	private final ReceiptService receiptService;

	public ReceiptController(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}

	/**
	 * Accepts POS transaction XML and returns plain text receipt. Example URL: POST
	 * /api/receipt/generate?template=receipt Content-Type: application/xml
	 */
	@PostMapping(value = "/generate", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.TEXT_XML_VALUE }, produces = MediaType.TEXT_PLAIN_VALUE)
	public String generateReceipt(@RequestBody String xmlPayload,
			@RequestParam(defaultValue = "receipt") String template) {
		return receiptService.generateReceiptFromXml(xmlPayload, template);
	}
}
