package nz.mikhailov.codeship.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

  private final int id;
  private final List<Build> builds;

  @JsonCreator
  public Project(@JsonProperty("id") int id, @JsonProperty("builds") List<Build> builds) {

    this.id = id;
    this.builds = builds;
  }

  public int getId() {

    return id;
  }

  public List<Build> getBuilds() {

    return builds;
  }

}
