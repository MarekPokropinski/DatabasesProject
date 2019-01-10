package project.controllers;

import java.security.Principal;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class BasicController {
	private static final Logger LOG = Logger.getLogger(BasicController.class);

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
