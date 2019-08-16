package com.yffd.bcap.uamc.ui.web.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/th")
public class ThymeleafController {

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(Model model){
        model.addAttribute("uid","123456789");
        model.addAttribute("name","<span style='color:red'>Jerry</span>");
        model.addAttribute("code","<span style='color:red'>Jerry</span>");
        return "show";
    }

}
