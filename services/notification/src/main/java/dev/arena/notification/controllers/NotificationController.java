package dev.arena.notification.controllers;

import dev.arena.notification.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(Constants.NOTIFICATIONS_URL)
public class NotificationController {
}
