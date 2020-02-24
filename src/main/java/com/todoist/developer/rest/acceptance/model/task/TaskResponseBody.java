package com.todoist.developer.rest.acceptance.model.task;

import lombok.Getter;

@Getter
public class TaskResponseBody {
    private Integer comment_count;
    private Boolean completed;
    private String content;
    private Due due;
    private Integer id;
    private Integer order;
    private Integer priority;
    private Integer project_id;
    private Integer section_id;
    private Integer url;
}
