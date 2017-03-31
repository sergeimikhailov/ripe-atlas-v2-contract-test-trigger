package nz.mikhailov.atlas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CodeshipBuildTrigger
    implements RequestHandler<Void, Void> {

  @Override
  public Void handleRequest(Void input, Context context) {

    return null;
  }
}
