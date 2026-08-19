package com.example.byggforetag.DTO;

import com.example.byggforetag.Enums.NotificationType;
import com.example.byggforetag.Model.Notification;
import com.example.byggforetag.Model.User;

import java.time.LocalDateTime;

public class NotificationDto {

    private String message;
    private NotificationType notificationType;
    private LocalDateTime createdAt;

    public NotificationDto() {
    }

    public NotificationDto(String message, NotificationType notificationType, LocalDateTime createdAt) {
        this.message = message;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
    }

    public static NotificationDto fromEntity(Notification notification){
        return new NotificationDto(
                notification.getMessage(),
                notification.getNotificationType(),
                notification.getCreatedAt()
                );
    }

    public Notification toEntity(User user){
        return new Notification(
                user,
                this.message,
                this.notificationType,
                LocalDateTime.now()
        );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
