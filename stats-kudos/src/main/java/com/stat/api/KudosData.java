package com.stat.api;

import lombok.Data;

@Data
public class KudosData {
    private Long id;
    private Long userId;

    public KudosData() {
    }

    public KudosData(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }
}
