package com.api;

import lombok.Data;

/**
 * Created by Brenda on 05/08/2018.
 */
@Data
public class KudoData {
    private Long id;
    private Long userId;

    public KudoData() {
    }

    public KudoData(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
