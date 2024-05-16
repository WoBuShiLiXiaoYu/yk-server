package com.work.ykserver.ykapps.query;

import lombok.Data;

@Data
public class BaseQuery {
    private String token;

    private String filterSQL;
}
