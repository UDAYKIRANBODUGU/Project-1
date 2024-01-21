package in.uday.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.uday.Binding.DashboardResponse;
import in.uday.Model.Counsellor;
import in.uday.Model.StudentEnq;
import in.uday.Repo.CounselleorRepository;
import in.uday.Repo.StudnetRepository;
import in.uday.utils.EmailsUtil;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounselleorRepository crepo;

	@Autowired
	private StudnetRepository strepo; // Corrected the repository name

	@Autowired
	private EmailsUtil emailutil;

	@Override
	public String saveCounsellor(Counsellor c) {
		try {
			Counsellor existing = crepo.findByCemail(c.getCemail());
			if (existing != null) {
				return "Duplicate Email Details";
			}

			Counsellor savedCounsellor = crepo.save(c);
			if (savedCounsellor.getCid() != null) {
				return "Registration Successful";
			} else {
				throw new Exception("Registration failed");
			}
		} catch (Exception e) {
			return "Registration Failed: " + e.getMessage();
		}
	}

	@Override
	public Counsellor LoginCheck(String email, String pwd) {
		return crepo.findByCemailAndCpwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor c = crepo.findByCemail(email);
		if (c == null) {
			return false;
		}

		String subject = "Recover Password UDAYKIRAN";
		String body = "<h1>Your Password: " + c.getCpwd() + "</h1>";

		return emailutil.sendEmail(subject, body, email); // Corrected the arguments here
	}

	@Override
	public DashboardResponse getDahsBoardInfo(Integer cid) {
		List<StudentEnq> allEnqs = strepo.findByCid(cid);

		long enrolledEnqs = allEnqs.stream().filter(e -> "Enrolled".equals(e.getStatus())).count();

		DashboardResponse response = new DashboardResponse();
		response.setTotalEnq(allEnqs.size());
		response.setEnrolledEnq((int) enrolledEnqs);
		response.setLostEnq(allEnqs.size() - (int) enrolledEnqs);

		return response;
	}
}
