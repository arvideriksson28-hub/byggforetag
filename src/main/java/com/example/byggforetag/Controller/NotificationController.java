package com.example.byggforetag.Controller;

import com.example.byggforetag.DTO.NotificationDto;
import com.example.byggforetag.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(notificationService.getAllNotificationsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> markAsRead(@PathVariable Long id){
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
