package in.uday.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uday.Model.Counsellor;

public interface CounselleorRepository extends JpaRepository<Counsellor, Integer> {

	Counsellor findByCemail(String email);

	Counsellor findByCemailAndCpwd(String email, String pwd);
}
