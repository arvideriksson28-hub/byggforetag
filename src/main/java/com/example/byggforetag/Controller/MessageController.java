package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.MessageDto;
import com.example.byggforetag.Service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{conversationId}/{userId}")
    public ResponseEntity<MessageDto> sendMessage(@PathVariable Long conversationId, @PathVariable Long userId, @RequestBody MessageDto messageDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.sendMessage(conversationId, userId, messageDto));
    }

}
