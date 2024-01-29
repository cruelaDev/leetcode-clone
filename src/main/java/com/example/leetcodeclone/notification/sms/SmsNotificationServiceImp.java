package com.example.leetcodeclone.notification.sms;

import com.example.leetcodeclone.notification.eskiz.EskizFeignClient;
import com.example.leetcodeclone.notification.eskiz.dto.SendSmsRequestDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "production")
@RequiredArgsConstructor
@Slf4j
public class SmsNotificationServiceImp implements SmsNotificationService {
    @Value("${leetcode.notification.sms.eskiz}")
    private String token;
    private final EskizFeignClient eskizFeignClient;

    @Override
    public void sendNotification(String phoneNumber, String message) {
        try {
            eskizFeignClient.smsSend("Bearer " + token, new SendSmsRequestDto(phoneNumber, message));
        } catch (FeignException.Unauthorized e) {
            token = eskizFeignClient.refresh("Bearer " + token).getData().get("token");
        } catch (Exception e) {
            log.error("Exception happened while sending notification. Phone number: {} message: {}", phoneNumber, message, e);
        }
    }
}
