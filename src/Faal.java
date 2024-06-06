import com.fasterxml.jackson.annotation.JsonProperty;

public class Faal {
    @JsonProperty("Poem")
    private String Poem;

    @JsonProperty("Interpretation")
    private String Interpretation;

    public String getPoem() {
        return Poem;
    }

    public String getInterpretation() {
        return Interpretation;
    }

    public void setPoem(String poem) {
        this.Poem = poem;
    }

    public void setInterpretation(String interpretation) {
        this.Interpretation = interpretation;
    }
}

