package com.core;
/**
 * Created by Brenda on 05/08/2018.
 */
public interface Utils {
    static String getCreatedUrl(String path, long id) {
        return String.format("./%s/%d", path, id);
    }
}