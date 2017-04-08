package nz.mikhailov.atlas;

import nz.mikhailov.atlas.codeship.v1.CodeshipApi;
import nz.mikhailov.atlas.config.Configuration;

public class Application {

  private final Configuration configuration;
  private final CodeshipApi client;
  private final Pipeline pipeline;


  public Application(Configuration configuration, CodeshipApi client, Pipeline pipeline) {

    this.configuration = configuration;
    this.client = client;
    this.pipeline = pipeline;
  }

  public void run() {

    pipeline
        .getLastBuildId(configuration.getCodeshipProjectId())
        .ifPresent(pipeline::restartBuild);
  }

}
