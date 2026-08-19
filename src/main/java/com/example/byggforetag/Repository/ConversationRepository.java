package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findConversationByJobId(Long jobId);
    @Query("SELECT c FROM Conversation c JOIN c.conversationParticipants cp WHERE cp.user.id = :userId")
    List<Conversation> findConversationByUserId(@Param("userId") Long userId);
}
