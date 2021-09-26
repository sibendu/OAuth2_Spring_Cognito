package sd.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController {

  @GetMapping("/message")
  public ResponseEntity<MessageDTO> message() {
    return ResponseEntity.ok(new MessageDTO("Public message"));
  }
}
