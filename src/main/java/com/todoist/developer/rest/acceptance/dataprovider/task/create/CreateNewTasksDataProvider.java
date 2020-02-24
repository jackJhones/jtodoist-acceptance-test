package com.todoist.developer.rest.acceptance.dataprovider.task.create;

import com.todoist.developer.rest.acceptance.model.task.TaskRequestBody;
import com.todoist.developer.rest.acceptance.utils.StringUtils;
import org.testng.annotations.DataProvider;

public class CreateNewTasksDataProvider {

    @DataProvider(name="createNewTask45")
    private Object[][] createNewTask45() {
        return new Object[][]{
                {new TaskRequestBody()
                        .setContent(StringUtils.randomAlphabeticString(40))
                        .setDue_string("tomorrow at 12:00")
                        .setDue_lang("en")},
                {new TaskRequestBody()
                        .setContent(StringUtils.randomAlphabeticString(40))
                        .setDue_date("2020-03-03")
                        .setDue_lang("en")},
                {new TaskRequestBody()
                        .setContent(StringUtils.randomAlphabeticString(40))
                        .setDue_datetime("2020-03-03T11:00:00Z")
                        .setDue_lang("en")}
        };
    }

    @DataProvider(name="createNewTask13")
    private Object[][] createNewTask13() {
        return new Object[][]{
                {new TaskRequestBody()
                        .setContent(StringUtils.randomAlphabeticString(40))},
                {new TaskRequestBody()
                        .setContent(StringUtils.randomAlphanumericString(30))}};
    }
}
