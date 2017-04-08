package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import nz.mikhailov.atlas.config.Configuration;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock
  private Configuration configuration;

  @Mock
  private Pipeline pipeline;

  @Mock
  private LambdaLogger logger;

  @InjectMocks
  private Application application;

  @Test
  public void shouldTriggerBuild() throws Exception {

    when(configuration.getCodeshipProjectId()).thenReturn(10);
    when(pipeline.getLastBuildId(10)).thenReturn(Optional.of(110));
    application.run();
    verify(pipeline, times(1)).restartBuild(110);
  }

  @Test
  public void shouldNotTriggerBuildIfNoRecentBuilds() throws Exception {

    when(configuration.getCodeshipProjectId()).thenReturn(11);
    when(pipeline.getLastBuildId(11)).thenReturn(empty());
    application.run();
    verify(pipeline, never()).restartBuild(anyInt());
  }

}
