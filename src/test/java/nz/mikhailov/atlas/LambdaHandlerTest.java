package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.mockito.Mockito.when;

public class LambdaHandlerTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Rule
  public EnvironmentVariables environmentVariables = new EnvironmentVariables();

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private Context context;

  @Mock
  private LambdaLogger logger;

  @Test
  public void endToEndHappyPathShouldNotThrow() throws Exception {

    environmentVariables.set("CODESHIP_BASE_URL", "http://localhost:" + wireMockRule.port());
    environmentVariables.set("CODESHIP_PROJECT_ID", "100103");
    environmentVariables.set("CODESHIP_API_KEY", "valid-api-key");
    when(context.getLogger()).thenReturn(logger);
    new LambdaHandler()
        .handleRequest(null, context);
  }
}