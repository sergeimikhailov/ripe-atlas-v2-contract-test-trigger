package nz.mikhailov;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import nz.mikhailov.codeship.v1.CodeshipApi;

import static java.lang.System.getenv;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;

public class CodeshipBuildTrigger
    implements RequestHandler<Void, Void> {

  @Override
  public Void handleRequest(Void input, Context context) {

    CodeshipApi api = newResource(
        CodeshipApi.class,
        newClient().target(getenv("CODESHIP_BASE_URL")));
    Pipeline pipeline = new Pipeline(api);
    pipeline
        .getLastBuildId(getenv("CODESHIP_PROJECT_ID"))
        .ifPresent(pipeline::restartBuild);
    return null;
  }
}
