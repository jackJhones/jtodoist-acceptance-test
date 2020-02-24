package com.todoist.developer.rest.acceptance.model.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = false, chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequestBody {
    private String content;
    private Integer project_id;
    private Integer section_id;
    private Integer parent;
    private Integer order;
    private List<Integer> label_ids;
    private Integer priority;
    private String due_string;
    private String due_date;
    private String due_datetime;
    private String due_lang;
}
