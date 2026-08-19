package com.example.byggforetag.Service;

import com.example.byggforetag.DTO.NotificationDto;
import com.example.byggforetag.Exception.NotificationNotFoundException;
import com.example.byggforetag.Exception.UserNotFoundException;
import com.example.byggforetag.Model.Notification;
import com.example.byggforetag.Model.User;
import com.example.byggforetag.Repository.NotificationRepository;
import com.example.byggforetag.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public List<NotificationDto> getAllNotificationsByUserId(Long userId){
        return notificationRepository.findAllNotificationByUserId(userId).stream()
                .map(NotificationDto::fromEntity)
                .toList();
    }

    public NotificationDto markAsRead(Long id){
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()-> new NotificationNotFoundException(id));
        notification.setRead(true);
        return NotificationDto.fromEntity(notificationRepository.save(notification));
    }

    public void deleteNotification(Long id){
        notificationRepository.delete(notificationRepository.findById(id).orElseThrow(()-> new NotificationNotFoundException(id)));
    }

    public NotificationDto createNotification(Long userId, NotificationDto notificationDto){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        Notification notification = new Notification(
                user,
                notificationDto.getMessage(),
                notificationDto.getNotificationType(),
                LocalDateTime.now()
        );
        return NotificationDto.fromEntity(notificationRepository.save(notification));
    }

    public List<NotificationDto> getAllNotifications(){
        return notificationRepository.findAll().stream()
                .map(NotificationDto::fromEntity)
                .toList();
    }
}
