package org.Test.Metier;

import java.util.List;
import org.Test.Entities.Authority;

public interface AuthorityMetier {
	public Authority saveAuthority(Authority a);

	public List<Authority> listAuthority();

}
