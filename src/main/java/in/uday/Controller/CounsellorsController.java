package in.uday.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.uday.Binding.DashboardResponse;
import in.uday.Model.Counsellor;
import in.uday.Services.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorsController {

    @Autowired
    private CounsellorService counsellorser;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("Counsellor", new Counsellor());
        return "Login";
    }

    @PostMapping("/Login")
    public String handleLogin(Counsellor c, HttpServletRequest req, Model model) {
        Counsellor obj = counsellorser.LoginCheck(c.getCemail(), c.getCpwd());

        if (obj == null) {
            model.addAttribute("errmsg", "Invalid Login Details");
            model.addAttribute("Counsellor", new Counsellor());
            return "Login";
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("cid", obj.getCid());

        return "redirect:/Dashboard";
    }

    @GetMapping("/Dashboard")
    public String buildDashboard(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("cid") == null) {
            // Redirect to login if the user is not logged in
            return "redirect:/";
        }

        Integer cid = (Integer) session.getAttribute("cid");

        DashboardResponse dashboardInfo = counsellorser.getDahsBoardInfo(cid);

        model.addAttribute("Dashboard", dashboardInfo);

        return "Dashboard";
    }

    @GetMapping("/RegisterPage")
    public String regpage(Model model) {
        model.addAttribute("Counsellor", new Counsellor());
        return "RegisterPage";
    }

    @PostMapping("/RegisterPage")
    public String handleRegistration(Counsellor c, Model model) {
        String msg = counsellorser.saveCounsellor(c);
        model.addAttribute("msg", msg); // Show the registration message

        return "RegisterPage";
    }

    @GetMapping("/forgotpwd")
    public String recoverPwdpage() {
        return "forgotpwd";
    }

    @GetMapping("/recoverpwd")
    public String recoverPwd(@RequestParam String email, Model model) {
        boolean status = counsellorser.recoverPwd(email);
        if (status) {
            model.addAttribute("smsg", "Password sent to your Email");
        } else {
            model.addAttribute("errmsg", "Invalid Email");
        }

        return "forgotpwd";
    }
}
