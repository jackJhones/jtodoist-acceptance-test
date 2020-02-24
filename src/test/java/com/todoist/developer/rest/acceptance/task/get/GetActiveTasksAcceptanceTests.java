package com.todoist.developer.rest.acceptance.task.get;

import com.todoist.developer.rest.acceptance.BaseTest;
import com.todoist.developer.rest.acceptance.enums.Endpoint;
import com.todoist.developer.rest.acceptance.listener.TestListener;
import io.restassured.RestAssured;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * The class contains acceptance tests cover the 'Get active tasks' feature
 * of the TODOIST API service (https://developer.todoist.com/rest/v1).
 * <p>
 * Test Suite: GAT
 */
@Listeners(TestListener.class)
public class GetActiveTasksAcceptanceTests extends BaseTest {

    // ====================== Positive tests ======================

    @Test
    public void GAT_01() {
        RestAssured
                .given()
                .log().all()
                .header("Authorization", "Bearer " + getToken())
                .get(getURI(Endpoint.GET_ACTIVE_TASKS))
                .then().log().all(true).statusCode(200);
    }
}
