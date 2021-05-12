package com.Gongdae9.fcm.api;

import com.Gongdae9.fcm.service.FCMService;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FCMApiController {

    private final FCMService fcmService;

    @PostMapping("/api/fcm/send")
    public void updateStatusMessage(String token,String title,String content)
        throws ExecutionException, InterruptedException {
        fcmService.send(token,title,content);
    }
}
