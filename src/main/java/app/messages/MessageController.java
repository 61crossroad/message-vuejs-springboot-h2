package app.messages;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @RequestMapping("/messages")
public class MessageController {
	
	MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("/welcome")
	// @ResponseBody
	public String welcome(Model model) {
		model.addAttribute("message", "Hello, welcome to Spring Boot!");
		// return "Hello, Welcome to Spring Boot!";
		return "welcome";
	}
	
	@GetMapping("/messages")
	public String index() {
		return "index";
	}
	
	@GetMapping("/api/messages")
	@ResponseBody
	public ResponseEntity<List<Message>> getMessages() {
		List<Message> messages = messageService.getMessages();
		return ResponseEntity.ok(messages);
	}
	
	@PostMapping("/api/messages")
	@ResponseBody
	public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
		Message saved = messageService.save(data.getText());
		if (saved == null) {
			return ResponseEntity.status(500).build();
		}
		return ResponseEntity.ok(saved);
	}
	
}
