package com.todoist.developer.rest.acceptance.model.task;

import lombok.Data;

@Data
public class Due {
    private String date;
    private String datetime;
    private String string;
    private String timezone;
}
