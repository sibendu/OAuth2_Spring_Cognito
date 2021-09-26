package sd.oauth.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/api/admin")
@Api( tags = "Admin" , description = "Admin API Endpoints", value = "Admin APIs", position=0)
@SecurityRequirement(name = "oAuthSecurity") //, scopes = "onestore/test")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	
  @ApiOperation(value = "This method need oAuth2 Bearer token", httpMethod = "GET")
  @PreAuthorize("#oauth2.hasScope('onestore/test')")
  @GetMapping(value="/message", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MessageDTO> getMessage(Principal principal) {
	
	OAuth2Authentication auth = (OAuth2Authentication)principal;	
	System.out.println("Inside getMessage() : caled by " + auth.toString());
    return ResponseEntity.ok(new MessageDTO("Admin message for " + principal.getName() ));
  }
  
  @GetMapping("/greeting")
  public ResponseEntity<MessageDTO> greeting() {
	System.out.println("Inside greeting");	
    return ResponseEntity.ok(new MessageDTO("Admin message"));
  }
}
