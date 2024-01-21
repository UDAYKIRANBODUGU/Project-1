package in.uday.Binding;

public class SearchCriteria {
	
	
	private String course;
	private String Eniquirystatus;
	private String classmode;
	
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getEniquirystatus() {
		return Eniquirystatus;
	}
	public void setEniquirystatus(String eniquirystatus) {
		Eniquirystatus = eniquirystatus;
	}
	public String getClassmode() {
		return classmode;
	}
	public void setClassmode(String classmode) {
		this.classmode = classmode;
	}
	@Override
	public String toString() {
		return "SearchCriteria [course=" + course + ", Eniquirystatus=" + Eniquirystatus + ", classmode=" + classmode
				+ "]";
	}
	
	


}
