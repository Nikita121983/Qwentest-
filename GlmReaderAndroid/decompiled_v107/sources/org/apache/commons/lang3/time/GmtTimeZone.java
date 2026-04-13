package org.apache.commons.lang3.time;

import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import org.apache.xmlbeans.impl.common.NameUtil;

/* loaded from: classes9.dex */
final class GmtTimeZone extends TimeZone {
    private static final int HOURS_PER_DAY = 24;
    private static final int MILLISECONDS_PER_MINUTE = 60000;
    private static final int MINUTES_PER_HOUR = 60;
    static final long serialVersionUID = 1;
    private final int offset;
    private final String zoneId;

    private static StringBuilder twoDigits(StringBuilder sb, int n) {
        return sb.append((char) ((n / 10) + 48)).append((char) ((n % 10) + 48));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GmtTimeZone(boolean negate, int hours, int minutes) {
        if (hours >= 24) {
            throw new IllegalArgumentException(hours + " hours out of range");
        }
        if (minutes >= 60) {
            throw new IllegalArgumentException(minutes + " minutes out of range");
        }
        int milliseconds = ((hours * 60) + minutes) * 60000;
        this.offset = negate ? -milliseconds : milliseconds;
        this.zoneId = twoDigits(twoDigits(new StringBuilder(9).append(TimeZones.GMT_ID).append(negate ? '-' : '+'), hours).append(NameUtil.COLON), minutes).toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GmtTimeZone)) {
            return false;
        }
        GmtTimeZone other = (GmtTimeZone) obj;
        return this.offset == other.offset && Objects.equals(this.zoneId, other.zoneId);
    }

    @Override // java.util.TimeZone
    public String getID() {
        return this.zoneId;
    }

    @Override // java.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
        return this.offset;
    }

    @Override // java.util.TimeZone
    public int getRawOffset() {
        return this.offset;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.offset), this.zoneId);
    }

    @Override // java.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return false;
    }

    @Override // java.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "[GmtTimeZone id=\"" + this.zoneId + "\",offset=" + this.offset + ']';
    }

    @Override // java.util.TimeZone
    public boolean useDaylightTime() {
        return false;
    }
}
