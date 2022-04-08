package mz.co.kelvin.openmrschallenge.model;

import java.io.IOException;

public enum Rel {
    FULL, SELF;

    public String toValue() {
        switch (this) {
            case FULL: return "full";
            case SELF: return "self";
        }
        return null;
    }

    public static Rel forValue(String value) throws IOException {
        if (value.equals("full")) return FULL;
        if (value.equals("self")) return SELF;
        throw new IOException("Cannot deserialize Rel");
    }
}
