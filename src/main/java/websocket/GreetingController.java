package websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import websocket.data.DataProvider;

@Controller
public class GreetingController {

    private Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private SimpMessageSendingOperations simp;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting(String.format("Hello, %s! [%s]", dataProvider.nextValue(), message.getName()));
    }

    @SendTo("/topic/greetings")
    @Scheduled(fixedRate = 3000)
    public void push(){
        LOGGER.info("Sending data");
        simp.convertAndSend("/topic/greetings", new Greeting(String.format("Hello, %s!", dataProvider.nextValue())));
    }

}