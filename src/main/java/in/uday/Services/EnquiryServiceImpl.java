package in.uday.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.uday.Binding.SearchCriteria;
import in.uday.Model.StudentEnq;
import in.uday.Repo.StudnetRepository;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private StudnetRepository strepo;

	@Override
	public boolean addEnq(StudentEnq c) {
		try {
			StudentEnq save = strepo.save(c);
			return save.getEnqid() != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<StudentEnq> getEnquiries(Integer cid, SearchCriteria s) {
		StudentEnq enq = new StudentEnq();
		
		//
		enq.setCid(cid);

		//
		if (s != null && s.getClassmode() != null && !s.getClassmode().equals("")) {
			enq.setClassmode(s.getClassmode());
		}

		if (s != null && s.getCourse() != null && !s.getCourse().equals("")) {
			enq.setCourse(s.getCourse());
		}

		if (s != null && s.getEniquirystatus() != null && !s.getEniquirystatus().equals("")) {
			enq.setStatus(s.getEniquirystatus());
		}

		Example<StudentEnq> example = Example.of(enq);
		List<StudentEnq> enquires = strepo.findAll(example);

		return enquires;
	}

}
