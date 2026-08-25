package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.ConversationDto;
import com.example.byggforetag.Exception.ConversationNotFoundException;
import com.example.byggforetag.Exception.UnauthorizedException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Conversation;
import com.example.byggforetag.Model.ConversationParticipant;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.ConversationParticipantRepository;
import com.example.byggforetag.Repository.ConversationRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final ConversationParticipantRepository conversationParticipantRepository;


    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository, ConversationParticipantRepository conversationParticipantRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.conversationParticipantRepository = conversationParticipantRepository;
    }

    public ConversationDto getConversationByJobId(Long jobId){
        return ConversationDto.fromEntity(conversationRepository.findConversationByJobId(jobId)
                .orElseThrow(() -> new ConversationNotFoundException(jobId)));

    }

    @Transactional
    public List<ConversationDto> getConversationsByUserEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));

        return conversationRepository.findConversationByUserId(user.getId()).stream()
                .map(ConversationDto::fromEntity)
                .toList();
    }

    @Transactional
    public List<ConversationDto> getAllConversations(){
        return conversationRepository.findAll().stream()
                .map(ConversationDto::fromEntity)
                .toList();
    }

    public ConversationDto createDirectConversation(Long userId1, Long userId2, String title){
        User user1 = userRepository.findById(userId1)
                .orElseThrow(()-> new UserNotFoundException(userId1));
        User user2 = userRepository.findById(userId2)
                .orElseThrow(()-> new UserNotFoundException(userId2));

        Conversation conversation = new Conversation(null, title);
        conversationRepository.save(conversation);

        conversationParticipantRepository.save(new ConversationParticipant(conversation, user1));
        conversationParticipantRepository.save(new ConversationParticipant(conversation, user2));

        return ConversationDto.fromEntity(conversation);
    }
}
