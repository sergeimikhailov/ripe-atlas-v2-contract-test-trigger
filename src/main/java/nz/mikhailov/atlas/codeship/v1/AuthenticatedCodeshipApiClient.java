package nz.mikhailov.atlas.codeship.v1;

import nz.mikhailov.atlas.config.Configuration;

import javax.ws.rs.core.Response;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;

public class AuthenticatedCodeshipApiClient implements CodeshipApi {

  private final CodeshipApi proxy;

  public AuthenticatedCodeshipApiClient(Configuration configuration) {

    this.proxy = newResource(
        CodeshipApi.class,
        newClient()
            .target(configuration.getCodeshipBaseUrl())
            .queryParam("api_key", configuration.getApiKey()));
  }

  @Override
  public Response getProject(int projectId) {

    return proxy.getProject(projectId);
  }

  @Override
  public Response restartBuild(int buildId) {

    return proxy.restartBuild(buildId);
  }

}
