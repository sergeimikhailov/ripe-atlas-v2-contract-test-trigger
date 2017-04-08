package nz.mikhailov.atlas;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import nz.mikhailov.atlas.codeship.v1.CodeshipApi;
import org.junit.Rule;
import org.junit.Test;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Optional.empty;
import static javax.ws.rs.client.ClientBuilder.newClient;
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PipelineIntegrationTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Test
  public void getLastBuildIdShouldReturnEmptyOptionalWhenNotFound() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    Optional<Integer> result = pipeline.getLastBuildId(100000);
    assertThat(result, is(empty()));
  }

  @Test(expected = RuntimeException.class)
  public void getLastBuildIdShouldThrowWhenAuthFailed() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithInvalidKey());
    pipeline.getLastBuildId(100100);
  }

  @Test(expected = RuntimeException.class)
  public void getLastBuildIdShouldThrowWhenStatusIs500() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    pipeline.getLastBuildId(100500);
  }

  @Test
  public void getLastBuildIdShouldReturnEmptyOptionalWhenNoBuilds() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    Optional<Integer> result = pipeline.getLastBuildId(100100);
    assertThat(result, is(empty()));
  }

  @Test
  public void getLastBuildIdShouldReturnOneWhenOnlyOneBuild() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    int result = pipeline.getLastBuildId(100101).get();
    assertThat(result, is(equalTo(50000001)));
  }

  @Test
  public void getLastBuildIdShouldReturnLastWhenManyBuilds() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    int result = pipeline.getLastBuildId(100103).get();
    assertThat(result, is(equalTo(50000003)));
  }

  @Test(expected = RuntimeException.class)
  public void restartBuildShouldThrowWhenUnauthorized() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithInvalidKey());
    pipeline.restartBuild(50000003);
  }

  @Test(expected = RuntimeException.class)
  public void restartBuildShouldThrowWhenBuildNotFound() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    pipeline.restartBuild(50000009);
  }

  @Test
  public void restartBuildShouldNotThrowWhenResponseIsOk() throws Exception {

    Pipeline pipeline = new Pipeline(clientWithValidKey());
    pipeline.restartBuild(50000003);
  }

  private CodeshipApi clientWithValidKey() {

    return newResource(
        CodeshipApi.class,
        newClient()
            .target("http://localhost:" + wireMockRule.port())
            .queryParam("api_key", "valid-api-key"));
  }

  private CodeshipApi clientWithInvalidKey() {

    return newResource(
        CodeshipApi.class,
        newClient()
            .target("http://localhost:" + wireMockRule.port())
            .queryParam("api_key", "invalid-api-key"));
  }

}