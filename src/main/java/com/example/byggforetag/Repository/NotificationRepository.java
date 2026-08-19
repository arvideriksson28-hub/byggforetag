package com.example.byggforetag.Repository;

import com.example.byggforetag.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllNotificationByUserId(Long userId);
}
