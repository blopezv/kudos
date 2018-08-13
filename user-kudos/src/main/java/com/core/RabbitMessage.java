package com.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Created by Brenda on 05/08/2018.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMessage {
    private String type;
    private String model;
    private BodyMessage bodyMessage;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BodyMessage getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(BodyMessage bodyMessage) {
        this.bodyMessage = bodyMessage;
    }
}

