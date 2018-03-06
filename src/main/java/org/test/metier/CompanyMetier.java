package org.test.metier;

import java.util.List;
import org.test.entities.Company;

public interface CompanyMetier {
	public Company saveCompany(Company c);

	public List<Company> listCompany();

}
