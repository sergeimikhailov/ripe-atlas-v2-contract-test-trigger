package nz.mikhailov.atlas.codeship.v1;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/v1")
public interface CodeshipApi {

  @GET
  @Path("/projects/{projectId}.json")
  @Produces(APPLICATION_JSON)
  Response getProject(@PathParam("projectId") int projectId);

  @POST
  @Path("/builds/{buildId}/restart")
  @Produces(APPLICATION_JSON)
  Response restartBuild(@PathParam("buildId") int buildId);

}
