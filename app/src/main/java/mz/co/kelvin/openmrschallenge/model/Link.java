package mz.co.kelvin.openmrschallenge.model;

public class Link {
    private Rel rel;
    private String uri;

    public Rel getRel() { return rel; }
    public void setRel(Rel value) { this.rel = value; }

    public String getURI() { return uri; }
    public void setURI(String value) { this.uri = value; }
}
