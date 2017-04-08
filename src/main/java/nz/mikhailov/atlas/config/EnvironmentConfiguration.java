package nz.mikhailov.atlas.config;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import static java.lang.Integer.parseInt;
import static java.lang.System.getenv;

public class EnvironmentConfiguration
    implements Configuration {

  private final LambdaLogger logger;

  private final String codeshipBaseUrl;

  private final int codeshipProjectId;

  private final String codeshipApiKey;

  public EnvironmentConfiguration(LambdaLogger logger) {

    codeshipBaseUrl = getenv("CODESHIP_BASE_URL");
    logger.log("Reading environment variable CODESHIP_BASE_URL with value: " + codeshipBaseUrl);
    codeshipProjectId = parseInt(getenv("CODESHIP_PROJECT_ID"));
    logger.log("Reading environment variable CODESHIP_PROJECT_ID with value: " + codeshipProjectId);
    codeshipApiKey = getenv("CODESHIP_API_KEY");
    logger.log("Reading environment variable CODESHIP_API_KEY with "
        + (codeshipApiKey.isEmpty() ? "empty value" : "NOT empty value"));
    this.logger = logger;
  }

  @Override
  public String getCodeshipBaseUrl() {

    return codeshipBaseUrl;
  }

  @Override
  public int getCodeshipProjectId() {

    return codeshipProjectId;
  }

  @Override
  public String getApiKey() {

    return codeshipApiKey;
  }
}
