package org.test.metier;

import java.util.List;
import org.test.dao.RequestRepository;
import org.test.entities.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestMetierImpl implements RequestMetier {

	@Autowired
	private RequestRepository requestRepository;

	@Override
	public Request saveRequest(Request r) {
		return requestRepository.save(r);
	}

	@Override
	public List<Request> listRequest() {
		return requestRepository.findAll();
	}

}