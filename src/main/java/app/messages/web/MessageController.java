package app.messages.web;

import java.util.List;

import app.messages.model.Message;
import app.messages.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@DeleteMapping("/api/messages/{id}")
	@ResponseBody
	public ResponseEntity<Integer> deleteMessage(@PathVariable Integer id) {
		Integer result = messageService.delete(id);
		return ResponseEntity.ok(result);
	}
}
