package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import nz.mikhailov.atlas.codeship.v1.AuthenticatedCodeshipApiClient;
import nz.mikhailov.atlas.codeship.v1.CodeshipApi;
import nz.mikhailov.atlas.config.Configuration;
import nz.mikhailov.atlas.config.EnvironmentConfiguration;

public class LambdaHandler
    implements RequestHandler<Void, Void> {

  @Override
  public Void handleRequest(Void input, Context context) {

    Configuration config = new EnvironmentConfiguration();
    CodeshipApi client = new AuthenticatedCodeshipApiClient(config);
    Pipeline pipeline = new Pipeline(client);
    new Application(config, client, pipeline).run();
    return null;
  }
}
