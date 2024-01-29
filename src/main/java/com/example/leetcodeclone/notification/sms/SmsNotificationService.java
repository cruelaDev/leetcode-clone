package com.example.leetcodeclone.notification.sms;


public interface SmsNotificationService {
    void sendNotification(String phoneNumber, String message);
}
