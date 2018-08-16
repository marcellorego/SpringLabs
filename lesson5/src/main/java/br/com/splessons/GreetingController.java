package br.com.splessons;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    /*@RequestMapping(method = RequestMethod.GET)
    public String sayAgain(ModelMap model) {
        model.addAttribute("name", "Try again!");
        return "form";
    }*/
 
    @RequestMapping(value="/helloagain", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        model.addAttribute("name", "again from Spring 4 MVC");
        return "greeting";
    }
    
    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("greeting");
		model.addObject("name", name);

		return model;
	}

    @RequestMapping(value = "/hellopost", method = RequestMethod.POST)
	public ModelAndView helloPost(@Valid GreetingForm greetingForm, BindingResult bindingResult) {

    	ModelAndView model = new ModelAndView();
    	
    	if (bindingResult.hasErrors()) {
    		
    		model.setViewName("greeting");
            return model;
        }

        //return "redirect:/results";
		
		model.setViewName("results");
		model.addObject("name", greetingForm.getName());

		return model;
	}
}
