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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class NameService {
	@Inject
	@ConfigProperty(name = "cute-name-service.url")
	private String nameServiceUrl;

	public String getName() {
		Client client = ClientBuilder.newClient();
		try {
			WebTarget target = client.target(nameServiceUrl);
			NameDto dto = target.path("api").path("name").request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
					.get().readEntity(NameDto.class);
			return dto.getName();
		} finally {
			client.close();
		}
	}

	public static class NameDto {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
