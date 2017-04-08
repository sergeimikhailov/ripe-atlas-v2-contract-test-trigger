package nz.mikhailov.atlas.codeship.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Build {

  private final int id;

  @JsonCreator
  public Build(@JsonProperty("id") int id) {

    this.id = id;
  }

  public int getId() {

    return id;
  }

}
