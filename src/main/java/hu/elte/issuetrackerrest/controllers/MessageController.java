package hu.elte.issuetrackerrest.controllers;

import hu.elte.issuetrackerrest.entities.Message;
import hu.elte.issuetrackerrest.repositories.MessageRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Message>> getAll() {
        return ResponseEntity.ok(messageRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Message> get(@PathVariable Integer id) {
        Optional<Message> issue = messageRepository.findById(id);
        if (issue.isPresent()) {
            return ResponseEntity.ok(issue.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Message> post(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable Integer id,@RequestBody Message message){
        Optional<Message> OIssue = messageRepository.findById(id);
        if (OIssue.isPresent()) {
            message.setId(id);
            return ResponseEntity.ok(messageRepository.save(message));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Integer id){
        Optional<Message> OIssue = messageRepository.findById(id);
        if (OIssue.isPresent()) {
            messageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
