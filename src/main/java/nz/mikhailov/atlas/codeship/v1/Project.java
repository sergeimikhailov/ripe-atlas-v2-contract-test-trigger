package nz.mikhailov.atlas.codeship.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

  private final List<Build> builds;

  @JsonCreator
  public Project(@JsonProperty("builds") List<Build> builds) {

    this.builds = builds;
  }

  public List<Build> getBuilds() {

    return builds;
  }

}
