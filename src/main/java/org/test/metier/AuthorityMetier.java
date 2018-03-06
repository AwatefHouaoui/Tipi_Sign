package org.test.metier;

import java.util.List;
import org.test.entities.Authority;

public interface AuthorityMetier {
	public Authority saveAuthority(Authority a);

	public List<Authority> listAuthority();

}
