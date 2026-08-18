package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
