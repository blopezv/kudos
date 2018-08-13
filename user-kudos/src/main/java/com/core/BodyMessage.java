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
public class BodyMessage {
    private String id;
    private Integer kudosQty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getKudosQty() {
        return kudosQty;
    }

    public void setKudosQty(Integer kudosQty) {
        this.kudosQty = kudosQty;
    }
}
