package nz.mikhailov.atlas.codeship.v1;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import nz.mikhailov.atlas.config.Configuration;

import javax.ws.rs.core.Response;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;

public class AuthenticatedCodeshipApiClient
    implements CodeshipApi {

  private final LambdaLogger logger;

  private final CodeshipApi proxy;

  public AuthenticatedCodeshipApiClient(Configuration configuration, LambdaLogger logger) {

    this.logger = logger;
    this.proxy = newResource(
        CodeshipApi.class,
        newClient()
            .target(configuration.getCodeshipBaseUrl())
            .queryParam("api_key", configuration.getApiKey()));
  }

  @Override
  public Response getProject(int projectId) {

    logger.log("Requesting project with id: " + projectId + "\n");
    return proxy.getProject(projectId);
  }

  @Override
  public Response restartBuild(int buildId) {

    logger.log("Restarting build with id: " + buildId + "\n");
    return proxy.restartBuild(buildId);
  }

}
