package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.MessageDto;
import com.example.byggforetag.Exception.ConversationNotFoundException;
import com.example.byggforetag.Exception.UnauthorizedException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Conversation;
import com.example.byggforetag.Model.Message;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.ConversationParticipantRepository;
import com.example.byggforetag.Repository.ConversationRepository;
import com.example.byggforetag.Repository.MessageRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final ConversationParticipantRepository conversationParticipantRepository;
    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository, ConversationParticipantRepository conversationParticipantRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public MessageDto sendMessage(Long conversationId, MessageDto messageDto, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(email));

        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(()-> new ConversationNotFoundException(conversationId));

        List<String> conversationParticipantsId = conversation.getConversationParticipants().stream()
                .map(cp -> cp.getUser().getEmail())
                .toList();

        if (!conversationParticipantsId.contains(email)){
            throw new UnauthorizedException("Du har inte behörighet att skicka meddelande i denna chat");
        }

        Message message = new Message(conversation, user, messageDto.getContent(), LocalDateTime.now());

        return MessageDto.fromEntity(messageRepository.save(message));
    }
}
