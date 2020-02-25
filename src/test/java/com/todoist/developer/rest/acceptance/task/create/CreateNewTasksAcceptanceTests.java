package com.todoist.developer.rest.acceptance.task.create;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import com.todoist.developer.rest.acceptance.BaseTest;
import com.todoist.developer.rest.acceptance.dataprovider.task.create.CreateNewTasksDataProvider;
import com.todoist.developer.rest.acceptance.enums.Endpoint;
import com.todoist.developer.rest.acceptance.listener.TestListener;
import com.todoist.developer.rest.acceptance.model.task.TaskRequestBody;
import com.todoist.developer.rest.acceptance.model.task.TaskResponseBody;
import com.todoist.developer.rest.acceptance.utils.StringUtils;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * The class contains acceptance tests cover the 'Create a new task' feature
 * of the TODOIST API service (https://developer.todoist.com/rest/v1).
 * <p>
 * Test Suite: CNT
 */
@Listeners(TestListener.class)
public class CreateNewTasksAcceptanceTests extends BaseTest {

    // ====================== Positive tests ======================

    /**
     * Priority: Major
     * Test Case ID: CNT-01
     * Description: A new task should be created when the request contains 'content' field is present and not empty
     * Pre-requisite: 'https://api.todoist.com/rest/v1' service is up.
     * Dependencies: N/A
     * Body:
     * Given there is a new task contains non-empty 'content' field
     * And the task does not contains others non-required fields
     * When I send the task as a 'create a new task' request
     * Then the new task should be successfully created
     * And the response should contains the content value
     * <p>
     * Post-requisite: Created test data deletion is not required.
     * API Documentation: https://developer.todoist.com/rest/v1/#create-a-new-task
     */
    @Test(dataProvider = "createNewTask13", dataProviderClass = CreateNewTasksDataProvider.class)
    public void CNT_13(TaskRequestBody body) {
        Response response = sendCreateTaskRequest(body);

        response.then()
                .log().all(true)
                .assertThat().statusCode(200);

        assertThat("Response contains 'content' does not match to the expected value.",
                response.as(TaskResponseBody.class).getContent(), is(body.getContent()));
    }

    /**
     * Priority: Major
     * Test Case ID: CNT-45
     * Description: A new task should be created when the request contains 'content', 'due_lang' and another due field values
     * Pre-requisite: 'https://api.todoist.com/rest/v1' service is up.
     * Dependencies: N/A
     * Body:
     * Given there is a new task contains non-empty 'content' field
     * And the task contains 'due_lang' as 'en'
     * And the task contains another due field
     * When I send the task as a 'create a new task' request
     * Then the new task should be successfully created
     * <p>
     * Post-requisite: Created test data deletion is not required.
     * API Documentation: https://developer.todoist.com/rest/v1/#create-a-new-task
     */
    @Test(dataProvider = "createNewTask45", dataProviderClass = CreateNewTasksDataProvider.class)
    public void CNT_45(TaskRequestBody body) {
        Response response = sendCreateTaskRequest(body);

        response.then()
                .log().all(true)
                .assertThat().statusCode(200);
    }

// ====================== Negative tests ======================

    /**
     * Priority: Major
     * Test Case ID: CNT-73
     * Description: A new task should not be created when the request contains 'content', but 'due_lang' is less than 2 letters
     * Pre-requisite: 'https://api.todoist.com/rest/v1' service is up.
     * Dependencies: N/A
     * Body:
     * Given there is a new task contains non-empty 'content' field
     * And the task contains 'due_lang' as 'e'
     * When I send the task as a 'create a new task' request
     * Then the new task should not be created
     * <p>
     * Post-requisite: Created test data deletion is not required.
     * API Documentation: https://developer.todoist.com/rest/v1/#create-a-new-task
     */
    @Test
    public void CNT_73() {
        Response response = sendCreateTaskRequest(
                new TaskRequestBody()
                        .setContent(StringUtils.randomAlphabeticString(30))
                        .setPriority(4)
                        .setDue_lang("e"));

        response.then()
                .log().all(true)
                .assertThat().statusCode(400);
    }

    private Response sendCreateTaskRequest(TaskRequestBody body) {
        RestAssuredConfig config = CurlLoggingRestAssuredConfigFactory.createConfig();
        return RestAssured
                .given()
                .log().all()
                .config(config.encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .header("Content-Type", "application/json")
                .header("X-Request-Id", getUUID())
                .header("Authorization", "Bearer " + getToken())
                .body(body, ObjectMapperType.JACKSON_2)
                .when()
                .post(getURI(Endpoint.CREATE_A_NEW_TASK));
    }
}
