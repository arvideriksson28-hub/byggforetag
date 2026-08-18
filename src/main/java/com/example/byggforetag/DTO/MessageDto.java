package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Conversation;
import com.example.byggforetag.Model.Message;
import com.example.byggforetag.Model.User;

import java.time.LocalDateTime;

public class MessageDto {

    private String content;
    private LocalDateTime sentAt;

    public MessageDto() {
    }

    public MessageDto(String content, LocalDateTime sentAt) {
        this.content = content;
        this.sentAt = sentAt;
    }

    public static MessageDto fromEntity(Message message){
        return new MessageDto(
                message.getContent(),
                message.getSentAt()
        );
    }

    public Message toEntity(Conversation conversation, User user){
        return new Message(
                conversation,
                user,
                this.content,
                this.sentAt
        );
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
