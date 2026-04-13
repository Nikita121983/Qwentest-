package org.apache.commons.math3.optim.linear;

/* loaded from: classes10.dex */
public enum Relationship {
    EQ("="),
    LEQ("<="),
    GEQ(">=");

    private final String stringValue;

    Relationship(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.stringValue;
    }

    public Relationship oppositeRelationship() {
        switch (this) {
            case LEQ:
                return GEQ;
            case GEQ:
                return LEQ;
            default:
                return EQ;
        }
    }
}
