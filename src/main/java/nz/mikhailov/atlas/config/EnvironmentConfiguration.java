package nz.mikhailov.atlas.config;

import static java.lang.Integer.parseInt;
import static java.lang.System.getenv;

public class EnvironmentConfiguration
    implements Configuration {

  @Override
  public String getCodeshipBaseUrl() {

    return getenv("CODESHIP_BASE_URL");
  }

  @Override
  public int getCodeshipProjectId() {

    return parseInt(getenv("CODESHIP_PROJECT_ID"));
  }

  @Override public String getApiKey() {

    return getenv("CODESHIP_API_KEY");
  }
}
