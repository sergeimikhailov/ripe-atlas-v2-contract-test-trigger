package nz.mikhailov;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CodeshipBuildTriggerTest {

  private CodeshipBuildTrigger trigger = new CodeshipBuildTrigger();

  @Test
  public void shouldReturnNull() throws Exception {

    Void result = trigger.handleRequest(null, null);
    assertThat(result, is(nullValue()));
  }
}