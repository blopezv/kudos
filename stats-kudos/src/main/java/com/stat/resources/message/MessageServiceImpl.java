package com.stat.resources.message;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.stat.core.RabbitMessage;

public class MessageServiceImpl implements MessageService {
    @Override
    public void sendNewKudos(RabbitMessage rabbitMessage) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://localhost:8095/api/kudos_message")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(rabbitMessage)
                .asJson();
    }
}
