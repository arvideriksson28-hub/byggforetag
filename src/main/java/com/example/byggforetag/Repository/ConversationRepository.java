package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
