package mz.co.kelvin.openmrschallenge.model;

import java.util.List;

public class Identifier {
    private String uuid;
    private String display;
    private List<Link> links;

    public String getUUID() { return uuid; }
    public void setUUID(String value) { this.uuid = value; }

    public String getDisplay() { return display; }
    public void setDisplay(String value) { this.display = value; }

    public List<Link> getLinks() { return links; }
    public void setLinks(List<Link> value) { this.links = value; }
}
