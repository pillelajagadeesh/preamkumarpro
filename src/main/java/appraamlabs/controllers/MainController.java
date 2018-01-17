package appraamlabs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

  @RequestMapping(value="/",method = RequestMethod.GET)
  public String index() {
	  return "http://localhost:8080/resources/static/map.html";
  }

}
