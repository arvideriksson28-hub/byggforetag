package com.example.byggforetag.DTO;

import com.example.byggforetag.Model.Conversation;
import com.example.byggforetag.Model.Job;

import java.util.List;

public class ConversationDto {
    private String title;
    private List<Long> participantsIds;

    public ConversationDto() {
    }

    public ConversationDto(String title, List<Long> participantsIds) {
        this.title = title;
        this.participantsIds = participantsIds;
    }


    public static ConversationDto fromEntity(Conversation conversation){
        return new ConversationDto(
                conversation.getTitle(),
                conversation.getConversationParticipants().stream()
                        .map(cp -> cp.getUser().getId())
                        .toList()
        );
    }

    public Conversation toEntity(Job job){
        return new Conversation(
                job,
                this.title
        );
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getParticipantsIds() {
        return participantsIds;
    }

    public void setParticipantsIds(List<Long> participantsIds) {
        this.participantsIds = participantsIds;
    }
}


