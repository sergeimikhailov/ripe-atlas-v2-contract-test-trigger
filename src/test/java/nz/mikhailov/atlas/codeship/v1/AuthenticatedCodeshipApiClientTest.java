package nz.mikhailov.atlas.codeship.v1;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import nz.mikhailov.atlas.config.Configuration;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.ws.rs.core.Response;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public class AuthenticatedCodeshipApiClientTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private Configuration configuration;

  @Mock
  private LambdaLogger logger;

  @Test
  public void shouldFormRequestWithValidKey() throws Exception {

    CodeshipApi client = authenticatedClientWithKey("valid-api-key");
    Response response = client.getProject(100100);
    assertThat(response.getStatus(), is(equalTo(OK.getStatusCode())));
  }

  @Test
  public void shouldFormRequestWithInvalidKey() throws Exception {

    CodeshipApi client = authenticatedClientWithKey("invalid-api-key");
    Response response = client.restartBuild(50000003);
    assertThat(response.getStatus(), is(equalTo(UNAUTHORIZED.getStatusCode())));
  }

  private CodeshipApi authenticatedClientWithKey(String key) {

    when(configuration.getCodeshipBaseUrl()).thenReturn("http://localhost:" + wireMockRule.port());
    when(configuration.getApiKey()).thenReturn(key);
    return new AuthenticatedCodeshipApiClient(configuration, logger);
  }

}