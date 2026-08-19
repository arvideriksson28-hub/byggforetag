package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.MessageDto;
import com.example.byggforetag.Exception.ConversationNotFoundException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Conversation;
import com.example.byggforetag.Model.Message;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.ConversationRepository;
import com.example.byggforetag.Repository.MessageRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public MessageDto sendMessage(Long conversationId, Long userId, MessageDto messageDto){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(()-> new ConversationNotFoundException(conversationId));

        Message message = new Message(conversation, user, messageDto.getContent(), LocalDateTime.now());

        return MessageDto.fromEntity(messageRepository.save(message));
    }
}
