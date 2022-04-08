package mz.co.kelvin.openmrschallenge.model;

import java.util.List;

public class Result {
    private String uuid;
    private String display;
    private List<Identifier> identifiers;
    private Person person;
    private boolean voided;
    private List<Link> links;
    private String resourceVersion;

    public String getUUID() { return uuid; }
    public void setUUID(String value) { this.uuid = value; }

    public String getDisplay() { return display; }
    public void setDisplay(String value) { this.display = value; }

    public List<Identifier> getIdentifiers() { return identifiers; }
    public void setIdentifiers(List<Identifier> value) { this.identifiers = value; }

    public Person getPerson() { return person; }
    public void setPerson(Person value) { this.person = value; }

    public boolean getVoided() { return voided; }
    public void setVoided(boolean value) { this.voided = value; }

    public List<Link> getLinks() { return links; }
    public void setLinks(List<Link> value) { this.links = value; }

    public String getResourceVersion() { return resourceVersion; }
    public void setResourceVersion(String value) { this.resourceVersion = value; }
}
