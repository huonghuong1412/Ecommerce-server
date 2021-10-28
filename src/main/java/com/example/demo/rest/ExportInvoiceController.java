package com.example.demo.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.order.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.impl.ExportInvoiceService;
import static java.lang.String.format;
import org.springframework.core.io.InputStreamResource;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoice/generator")
public class ExportInvoiceController {

	@Autowired
	private OrderRepository orderRepos;

	@Autowired
	private ExportInvoiceService invoiceService;

	// generate invoice pdf
	@PostMapping(value = "/generate", produces = "application/pdf")
	public ResponseEntity<InputStreamResource> invoiceGenerate(
			@RequestParam(name = "id", defaultValue = "1") Long id,
			@RequestParam(name = "lang", defaultValue = "vi") String lang) throws IOException {
		final Order order = orderRepos.getById(id);
		final File invoicePdf = invoiceService.generateInvoiceFor(order, Locale.forLanguageTag(lang));

		final HttpHeaders httpHeaders = getHttpHeaders(id, lang, invoicePdf);
		return new ResponseEntity<>(new InputStreamResource(new FileInputStream(invoicePdf)), httpHeaders,
				HttpStatus.OK);
	}

	private HttpHeaders getHttpHeaders(Long id, String lang, File invoicePdf) {
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.APPLICATION_PDF);
		respHeaders.setContentLength(invoicePdf.length());
		respHeaders.setContentDispositionFormData("attachment", format("%s-%s.pdf", id, lang));
		return respHeaders;
	}

}
