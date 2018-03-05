package org.Test.Metier;

import java.util.List;
import org.Test.Entities.Company;

public interface CompanyMetier {
	public Company saveCompany(Company c);

	public List<Company> listCompany();

}
