package app.messages.web;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import app.messages.Message;
import app.messages.MessageService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MessageControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private MessageService service;
	
	@Test
	public void getMessages_existingMessages_shouldReturnJsonArray() throws Exception {
		Message firstMessage = new Message("First Message");
		List<Message> allMessages = Arrays.asList(firstMessage);
		when(service.getMessages()).thenReturn(allMessages);
		mvc.perform(get("/api/messages").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].text", is(firstMessage.getText())));
	}
}
