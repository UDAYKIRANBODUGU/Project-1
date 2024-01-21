package in.uday.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.uday.Binding.SearchCriteria;
import in.uday.Model.StudentEnq;
import in.uday.Services.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqSer;

	@GetMapping("/enquiry")
	public String enqPage(Model model) {
		model.addAttribute("studentEnq", new StudentEnq());
		return "AddEnqview";
	}

	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("studentEnq") StudentEnq se, HttpServletRequest req,
			BindingResult bindingResult, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		se.setCid(cid);

		boolean status = enqSer.addEnq(se);

		if (status) {
			model.addAttribute("smsg", "Enquiry added successfully");
		} else {
			model.addAttribute("errmsg", "Failed to add enquiry");
		}
		model.addAttribute("studentEnq", new StudentEnq());

		return "AddEnqview";
	}

	@GetMapping("/enquires")
	public String viewEnquiries(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		model.addAttribute("s", new SearchCriteria());

		List<StudentEnq> enquiriesList = enqSer.getEnquiries(cid, new SearchCriteria());
		model.addAttribute("enquires", enquiriesList);

		return "dispEnqview";
	}

	@PostMapping("/filter-enquires")
	public String filterEnquiries(@ModelAttribute("s") SearchCriteria s, HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		List<StudentEnq> enquiriesList = enqSer.getEnquiries(cid, s);
		model.addAttribute("enquires", enquiriesList);

		return "filterEnqView";
	}
}
