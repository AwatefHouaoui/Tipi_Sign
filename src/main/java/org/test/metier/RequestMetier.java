package org.test.metier;

import java.util.List;
import org.test.entities.Request;

public interface RequestMetier {
	public Request saveRequest(Request r);

	public List<Request> listRequest();

}
