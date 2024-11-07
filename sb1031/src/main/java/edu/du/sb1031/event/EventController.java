package edu.du.sb1031.event;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final CustomEventPublisher publisher;

    @GetMapping("/event/{msg}")
    public void event(@PathVariable("msg") String msg) {
//        publisher.publishOrderCreatedEvent( test: " + msg);
    }
}
