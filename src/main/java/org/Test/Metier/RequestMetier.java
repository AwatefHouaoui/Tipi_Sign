package org.Test.Metier;

import java.util.List;
import org.Test.Entities.Request;

public interface RequestMetier {
	public Request saveRequest(Request r);

	public List<Request> listRequest();

}
