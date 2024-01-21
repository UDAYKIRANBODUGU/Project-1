package in.uday.Services;

import java.util.List;

import in.uday.Binding.SearchCriteria;
import in.uday.Model.StudentEnq;

public interface EnquiryService {
	
	
	public boolean addEnq(StudentEnq c);
	
	public List<StudentEnq>getEnquiries(Integer cid,SearchCriteria s);
	
	

}
