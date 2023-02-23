package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Post;
import com.sendgrid.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String apiKey;

    public String sendTextEmail(Post post){
        Email from = new Email("joshua.stephen.mullins");
        String subject = "New post has been created.";
        Email to = new Email(post.getPoster().getEmail());
        Content content = new Content("text/plain", "Thank you for submitting a new post.");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            return response.getBody();
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
}

