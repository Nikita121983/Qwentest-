package org.apache.poi.hpsf;

import java.util.Objects;

/* loaded from: classes10.dex */
public class CustomProperty extends Property {
    private String name;

    public CustomProperty() {
        this.name = null;
    }

    public CustomProperty(Property property) {
        this(property, null);
    }

    public CustomProperty(Property property, String name) {
        super(property);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equalsContents(Object o) {
        boolean equalNames;
        CustomProperty c = (CustomProperty) o;
        String name1 = c.getName();
        String name2 = getName();
        if (name1 == null) {
            equalNames = name2 == null;
        } else {
            equalNames = name1.equals(name2);
        }
        return equalNames && c.getID() == getID() && c.getType() == getType() && c.getValue().equals(getValue());
    }

    @Override // org.apache.poi.hpsf.Property
    public int hashCode() {
        return Objects.hash(this.name, Long.valueOf(getID()));
    }

    @Override // org.apache.poi.hpsf.Property
    public boolean equals(Object o) {
        return (o instanceof CustomProperty) && equalsContents(o);
    }
}
