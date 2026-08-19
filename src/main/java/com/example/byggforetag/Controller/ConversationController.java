package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.ConversationDto;
import com.example.byggforetag.Service.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<ConversationDto> getConversationByJobId(@PathVariable Long jobId){
        return ResponseEntity.ok(conversationService.getConversationByJobId(jobId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ConversationDto>> getConversationByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(conversationService.getConversationsByUserId(userId));
    }

    @PostMapping("/direct/{userId1}/{userId2}")
    public ResponseEntity<ConversationDto> createConversation(@PathVariable Long userId1, @PathVariable Long userId2, @RequestBody String title){
        return ResponseEntity.status(HttpStatus.CREATED).body(conversationService.createDirectConversation(userId1,userId2, title));
    }

}
