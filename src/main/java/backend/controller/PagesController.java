package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PagesController {
	
	/*
	@RequestMapping("/connectionResponse")
	public ConnectionResponse getStatusConnecton() {
		
		ConnectionResponse connectionResponse = new ConnectionResponse(true);
		
		return connectionResponse;
	}*/
	   
	    @CrossOrigin(origins = "http://localhost:4200")
	    @PutMapping("/save_appareils")
	    public Boolean postBody(@RequestBody String appareils) {
	    	System.out.println(appareils);
	    	
	    
	    	
	    	return false;
	    	
	    }
}
