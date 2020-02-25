package com.todoist.developer.rest.acceptance.model.task;

import lombok.Getter;

@Getter
public class TaskResponseBody {
    private Integer comment_count;
    private Boolean completed;
    private String content;
    private Due due;
    private Long id;
    private Integer[] label_ids;
    private Integer order;
    private Integer priority;
    private Long project_id;
    private Integer section_id;
    private String created;
    private String url;
}
