package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import nz.mikhailov.atlas.codeship.v1.AuthenticatedCodeshipApiClient;
import nz.mikhailov.atlas.codeship.v1.CodeshipApi;
import nz.mikhailov.atlas.config.Configuration;
import nz.mikhailov.atlas.config.EnvironmentConfiguration;

public class LambdaHandler
    implements RequestHandler<Void, Void> {

  @Override
  public Void handleRequest(Void input, Context context) {

    LambdaLogger logger = context.getLogger();
    Configuration config = new EnvironmentConfiguration(logger);
    CodeshipApi client = new AuthenticatedCodeshipApiClient(config, logger);
    Pipeline pipeline = new Pipeline(client, logger);
    new Application(config, pipeline, logger).run();
    return null;
  }
}
