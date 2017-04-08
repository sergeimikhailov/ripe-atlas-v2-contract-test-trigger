package nz.mikhailov.atlas;

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

  private final CodeshipApi api;

  public Pipeline(CodeshipApi api) {

    this.api = api;
  }

  public Optional<Integer> getLastBuildId(int projectId) {

    Response response = api.getProject(projectId);
    if (response.getStatus() == NOT_FOUND.getStatusCode()) {
      return empty();
    }
    if (response.getStatus() != OK.getStatusCode()) {
      throw new RuntimeException("Unexpected response " + response.getStatus());
    }
    Project project = response.readEntity(Project.class);
    if (project.getBuilds() == null) {
      return empty();
    }
    return project.getBuilds()
        .stream()
        .map(Build::getId)
        .sorted(reverseOrder())
        .findFirst();
  }

  public void restartBuild(int buildId) {

    Response response = api.restartBuild(buildId);
    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed to trigger the build, response code " + response.getStatus());
    }
  }

}
