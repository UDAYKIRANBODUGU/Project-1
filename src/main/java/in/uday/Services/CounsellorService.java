package in.uday.Services;

import in.uday.Binding.DashboardResponse;
import in.uday.Model.Counsellor;

public interface CounsellorService {
	
	
	public String saveCounsellor(Counsellor c);
	
	public Counsellor LoginCheck(String email,String pwd);
	
	public boolean recoverPwd(String email);
	
	public DashboardResponse getDahsBoardInfo(Integer cid);

}
