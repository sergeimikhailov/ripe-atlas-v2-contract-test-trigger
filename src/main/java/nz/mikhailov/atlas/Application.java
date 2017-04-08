package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import nz.mikhailov.atlas.config.Configuration;

public class Application {

  private final LambdaLogger logger;

  private final Configuration configuration;

  private final Pipeline pipeline;

  public Application(Configuration configuration, Pipeline pipeline, LambdaLogger logger) {

    this.configuration = configuration;
    this.pipeline = pipeline;
    this.logger = logger;
  }

  public void run() {

    logger.log("Restarting last build");
    pipeline
        .getLastBuildId(configuration.getCodeshipProjectId())
        .ifPresent(pipeline::restartBuild);
  }

}
