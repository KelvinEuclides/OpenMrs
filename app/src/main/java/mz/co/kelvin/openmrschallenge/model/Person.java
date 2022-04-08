package mz.co.kelvin.openmrschallenge.model;

import java.util.List;

public class Person {
    private String uuid;
    private String display;
    private String gender;
    private long age;
    private String birthdate;
    private boolean birthdateEstimated;
    private boolean dead;
    private Object deathDate;
    private Identifier causeOfDeath;
    private Identifier preferredName;
    private Identifier preferredAddress;
    private List<Identifier> attributes;
    private boolean voided;
    private Object birthtime;
    private boolean deathdateEstimated;
    private List<Link> links;
    private String resourceVersion;

    public String getUUID() { return uuid; }
    public void setUUID(String value) { this.uuid = value; }

    public String getDisplay() { return display; }
    public void setDisplay(String value) { this.display = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public long getAge() { return age; }
    public void setAge(long value) { this.age = value; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String value) { this.birthdate = value; }

    public boolean getBirthdateEstimated() { return birthdateEstimated; }
    public void setBirthdateEstimated(boolean value) { this.birthdateEstimated = value; }

    public boolean getDead() { return dead; }
    public void setDead(boolean value) { this.dead = value; }

    public Object getDeathDate() { return deathDate; }
    public void setDeathDate(Object value) { this.deathDate = value; }

    public Identifier getCauseOfDeath() { return causeOfDeath; }
    public void setCauseOfDeath(Identifier value) { this.causeOfDeath = value; }

    public Identifier getPreferredName() { return preferredName; }
    public void setPreferredName(Identifier value) { this.preferredName = value; }

    public Identifier getPreferredAddress() { return preferredAddress; }
    public void setPreferredAddress(Identifier value) { this.preferredAddress = value; }

    public List<Identifier> getAttributes() { return attributes; }
    public void setAttributes(List<Identifier> value) { this.attributes = value; }

    public boolean getVoided() { return voided; }
    public void setVoided(boolean value) { this.voided = value; }

    public Object getBirthtime() { return birthtime; }
    public void setBirthtime(Object value) { this.birthtime = value; }

    public boolean getDeathdateEstimated() { return deathdateEstimated; }
    public void setDeathdateEstimated(boolean value) { this.deathdateEstimated = value; }

    public List<Link> getLinks() { return links; }
    public void setLinks(List<Link> value) { this.links = value; }

    public String getResourceVersion() { return resourceVersion; }
    public void setResourceVersion(String value) { this.resourceVersion = value; }
}
