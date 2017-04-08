package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import nz.mikhailov.atlas.codeship.v1.Build;
import nz.mikhailov.atlas.codeship.v1.CodeshipApi;
import nz.mikhailov.atlas.codeship.v1.Project;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static java.util.Comparator.reverseOrder;
import static java.util.Optional.empty;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

public class Pipeline {

  private final LambdaLogger logger;

  private final CodeshipApi api;

  public Pipeline(CodeshipApi api, LambdaLogger logger) {

    this.api = api;
    this.logger = logger;
  }

  public Optional<Integer> getLastBuildId(int projectId) {

    logger.log("Requesting last buildId for projectId: " + projectId);
    Response response = api.getProject(projectId);
    if (response.getStatus() == NOT_FOUND.getStatusCode()) {
      logger.log("Response 404 for projectId: " + projectId);
      return empty();
    }
    if (response.getStatus() != OK.getStatusCode()) {
      logger.log("Failed to get builds for projectId: " + projectId);
      throw new RuntimeException("Unexpected response " + response.getStatus());
    }
    Project project = response.readEntity(Project.class);
    if (project.getBuilds() == null || project.getBuilds().isEmpty()) {
      logger.log("There are no builds for projectId: " + projectId);
      return empty();
    }
    return project.getBuilds()
        .stream()
        .map(Build::getId)
        .sorted(reverseOrder())
        .findFirst();
  }

  public void restartBuild(int buildId) {

    logger.log("Requesting restart for buildId: " + buildId);
    Response response = api.restartBuild(buildId);
    if (response.getStatus() != 200) {
      logger.log("Unexpected response code: " + response.getStatus() + " for buildId: " + buildId);
      throw new RuntimeException("Failed to trigger the build");
    }
  }

}
