package org.apache.commons.lang3;

/* loaded from: classes9.dex */
public class BitField {
    private final int mask;
    private final int shiftCount;

    public BitField(int mask) {
        this.mask = mask;
        this.shiftCount = mask == 0 ? 0 : Integer.numberOfTrailingZeros(mask);
    }

    public int clear(int holder) {
        return (~this.mask) & holder;
    }

    public byte clearByte(byte holder) {
        return (byte) clear(holder);
    }

    public short clearShort(short holder) {
        return (short) clear(holder);
    }

    public int getRawValue(int holder) {
        return this.mask & holder;
    }

    public short getShortRawValue(short holder) {
        return (short) getRawValue(holder);
    }

    public short getShortValue(short holder) {
        return (short) getValue(holder);
    }

    public int getValue(int holder) {
        return getRawValue(holder) >> this.shiftCount;
    }

    public boolean isAllSet(int holder) {
        return (this.mask & holder) == this.mask;
    }

    public boolean isSet(int holder) {
        return (this.mask & holder) != 0;
    }

    public int set(int holder) {
        return this.mask | holder;
    }

    public int setBoolean(int holder, boolean flag) {
        return flag ? set(holder) : clear(holder);
    }

    public byte setByte(byte holder) {
        return (byte) set(holder);
    }

    public byte setByteBoolean(byte holder, boolean flag) {
        return flag ? setByte(holder) : clearByte(holder);
    }

    public short setShort(short holder) {
        return (short) set(holder);
    }

    public short setShortBoolean(short holder, boolean flag) {
        return flag ? setShort(holder) : clearShort(holder);
    }

    public short setShortValue(short holder, short value) {
        return (short) setValue(holder, value);
    }

    public int setValue(int holder, int value) {
        return ((~this.mask) & holder) | ((value << this.shiftCount) & this.mask);
    }
}
