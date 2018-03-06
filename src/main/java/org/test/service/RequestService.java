package org.test.service;

import java.util.List;
import org.test.entities.Request;
import org.test.metier.RequestMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestService {
	@Autowired
	private RequestMetier requestMetier;

	@RequestMapping(value = "/Request", method = RequestMethod.POST)
	public Request saveRequest(@RequestBody Request r) {
		return requestMetier.saveRequest(r);
	}

	@RequestMapping(value = "/getRequest", method = RequestMethod.GET)
	public List<Request> listRequest() {
		return requestMetier.listRequest();
	}

}
