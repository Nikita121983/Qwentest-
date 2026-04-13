package org.apache.poi.openxml4j.opc.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/* loaded from: classes10.dex */
public final class ContentType {
    private static final Pattern patternParams;
    private static final Pattern patternTypeSubType = Pattern.compile("^([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)/([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)$");
    private static final Pattern patternTypeSubTypeParams;
    private final Map<String, String> parameters;
    private final String subType;
    private final String type;

    static {
        String parameter = "([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)=(\"?[\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+\"?)";
        patternTypeSubTypeParams = Pattern.compile("^([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)/([\\x21-\\x7E&&[^()<>@,;:\\\\/\"\\[\\]?={}\\x20\\x09]]+)(;" + parameter + ")*$");
        patternParams = Pattern.compile(";" + parameter);
    }

    public ContentType(String contentType) throws InvalidFormatException {
        Matcher mMediaType = patternTypeSubType.matcher(contentType);
        mMediaType = mMediaType.matches() ? mMediaType : patternTypeSubTypeParams.matcher(contentType);
        if (!mMediaType.matches()) {
            throw new InvalidFormatException("The specified content type '" + contentType + "' is not compliant with RFC 2616: malformed content type.");
        }
        if (mMediaType.groupCount() >= 2) {
            this.type = mMediaType.group(1);
            this.subType = mMediaType.group(2);
            this.parameters = new HashMap();
            if (mMediaType.groupCount() >= 5) {
                Matcher mParams = patternParams.matcher(contentType.substring(mMediaType.end(2)));
                while (mParams.find()) {
                    this.parameters.put(mParams.group(1), mParams.group(2));
                }
                return;
            }
            return;
        }
        this.type = "";
        this.subType = "";
        this.parameters = Collections.emptyMap();
    }

    public final String toString() {
        return toString(true);
    }

    public final String toString(boolean withParameters) {
        StringBuilder retVal = new StringBuilder(64);
        retVal.append(getType());
        retVal.append('/');
        retVal.append(getSubType());
        if (withParameters) {
            for (Map.Entry<String, String> me : this.parameters.entrySet()) {
                retVal.append(';');
                retVal.append(me.getKey());
                retVal.append(Chars.EQ);
                retVal.append(me.getValue());
            }
        }
        return retVal.toString();
    }

    public boolean equals(Object obj) {
        return !(obj instanceof ContentType) || toString().equalsIgnoreCase(obj.toString());
    }

    public int hashCode() {
        return Objects.hash(this.type, this.subType, this.parameters);
    }

    public String getSubType() {
        return this.subType;
    }

    public String getType() {
        return this.type;
    }

    public boolean hasParameters() {
        return (this.parameters == null || this.parameters.isEmpty()) ? false : true;
    }

    public String[] getParameterKeys() {
        if (this.parameters == null) {
            return new String[0];
        }
        return (String[]) this.parameters.keySet().toArray(new String[0]);
    }

    public String getParameter(String key) {
        return this.parameters.get(key);
    }
}
