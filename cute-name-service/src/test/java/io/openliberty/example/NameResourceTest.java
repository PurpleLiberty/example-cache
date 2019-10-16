/*
 *
 *  Copyright 2016-2018 Red Hat, Inc, IBM, and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package io.openliberty.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class NameResourceTest {
    private static final String BASE_URI = "http://localhost:9080/ol-cache-test";

    @Deployment(testable = true)
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "ol-cache-test.war")
				.addPackages(true, "io.openliberty.example");
		return archive;
	}
    
    @Test
    @RunAsClient
    public void testGetName() {
        given()
                .baseUri(BASE_URI)
        .when()
                .get("/api/name")
        .then()
                .statusCode(200)
                .body("name", notNullValue());
    }
}
