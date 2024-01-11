package com.registerApp.registerForm.controller;

import com.registerApp.registerForm.dao.DataBaseOperations;
import com.registerApp.registerForm.model.EmployeeModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Component
@Scope("prototype")
@RequestMapping("/")
//@Validated
public class HomeController {
    public DataBaseOperations dataBaseOperations;
    @Autowired
    public HomeController(DataBaseOperations dataBaseOperations) {
       // super(errorAttributes);
        this.dataBaseOperations = dataBaseOperations;
    }
//    @RequestMapping("/error")
//    public ModelAndView handleError(HttpServletRequest request) {
//        // Handle the error and return a ModelAndView with a user-friendly message
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("error", "An error occurred during form processing.");
//        return modelAndView;
//    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping("/form")
    public String showForm(Model model){
        model.addAttribute("employeeModel" , new EmployeeModel());
        return "registerationForm";
    }

    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("employeeModel") EmployeeModel employeeModel, BindingResult bindingResult, Model model)
    {
        if (!employeeModel.getPassword().equals(employeeModel.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "password.mismatch", "Password and Confirm Password must match");
        }
        System.out.println("employeeModel "+employeeModel.getUsername());
        System.out.println("bindingResults: "+bindingResult);

        if(bindingResult.hasErrors())
        {
            return "registerationForm";
        }

        //processing data
        dataBaseOperations.save(employeeModel);

        //step2: add name to model
        model.addAttribute("employeeModelObj" , employeeModel);

        //step3: return view page
        return  "formResult";
    }
}
