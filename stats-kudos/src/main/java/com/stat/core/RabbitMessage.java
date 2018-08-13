package com.stat.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMessage {
    private String type;
    private String model;
    private BodyMessage bodyMessage;
}

