package main.com.myApp.controller;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
//import sun.misc.Contended;

@Controller
public class HomeController
{

    @RequestMapping("/")
    public String showHomePage()
    {
        return "homePage";
    }

    @RequestMapping("/form")
    public String showForm()
    {
        return "studentForm";
    }

//    @RequestMapping("/processForm")
//    public String processForm(HttpServletRequest request, Model model)
//    {
//        String nameFromRequest = request.getParameter("name");
//
//        model.addAttribute("StudentName",nameFromRequest);
//
//        return "resultStudentForm";
//    }
    @RequestMapping("/age")
    public String showAgeCalculator()
    {
        return "ageCalculator";
    }
    @RequestMapping("/processForm")
    public String processFormCalculatingAge(HttpServletRequest request, Model model)
    {
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        long yearsDifference = LocalDate.now().getYear() - Integer.parseInt(year);
        long monthsDifference = LocalDate.now().getMonthValue() - Integer.parseInt(month);
        long daysDifference = LocalDate.now().getDayOfMonth() - Integer.parseInt(day);

        model.addAttribute("year",yearsDifference);
        model.addAttribute("month",monthsDifference);
        model.addAttribute("day",daysDifference);

        return "resultAgeCalculation";
    }
}
