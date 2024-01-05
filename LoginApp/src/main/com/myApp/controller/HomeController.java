package main.com.myApp.controller;

import main.com.myApp.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController
{

    @RequestMapping("/")
    public St   ring showHomePage(Model model)
    {
        model.addAttribute("userModel" , new UserModel());
        return "formPage";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("userModel") UserModel userModel, Model model)
    {
        //processing data

        //step2: add name to model
        model.addAttribute("userModelObj" , userModel);

        //step3: return view page
        return "resultPage";
    }
}


