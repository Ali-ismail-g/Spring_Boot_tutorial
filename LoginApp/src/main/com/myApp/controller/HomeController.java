package main.com.myApp.controller;

import main.com.myApp.dao.DataBaseOperations;
import main.com.myApp.model.EmployeeModel;
import main.com.myApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;


@Controller
@Component
@Scope("prototype")
public class HomeController
{
    public DataBaseOperations dataBaseOperations;
    @Autowired
    public HomeController(DataBaseOperations dataBaseOperations) {
        this.dataBaseOperations = dataBaseOperations;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @RequestMapping("/")
    public String showRegisterationForm(Model model){
        model.addAttribute("employeeModel" , new EmployeeModel());
        return "registerationForm";
    }

    @RequestMapping("/processForm")
    public String processForm(@Valid @ModelAttribute("employeeModel") EmployeeModel employeeModel, BindingResult bindingResult, Model model)
    {
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


