package in.uday.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uday.Model.StudentEnq;

public interface StudnetRepository  extends JpaRepository<StudentEnq, Integer>{
	
	public List<StudentEnq>findByCid(Integer cid);	
}
