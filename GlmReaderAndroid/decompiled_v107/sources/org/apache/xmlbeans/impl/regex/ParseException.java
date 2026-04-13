package org.apache.xmlbeans.impl.regex;

/* loaded from: classes11.dex */
public class ParseException extends RuntimeException {
    int location;

    public ParseException(String mes, int location) {
        super(mes);
        this.location = location;
    }

    public int getLocation() {
        return this.location;
    }
}
