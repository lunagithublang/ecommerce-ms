package dev.arena.notification.repositories;

import dev.arena.notification.entities.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository <Notification, String> {
}
