package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* loaded from: classes9.dex */
public class MutableShort extends Number implements Comparable<MutableShort>, Mutable<Number> {
    private static final long serialVersionUID = -2135791679;
    private short value;

    public MutableShort() {
    }

    public MutableShort(Number value) {
        this.value = value.shortValue();
    }

    public MutableShort(short value) {
        this.value = value;
    }

    public MutableShort(String value) {
        this.value = Short.parseShort(value);
    }

    public void add(Number operand) {
        this.value = (short) (this.value + operand.shortValue());
    }

    public void add(short operand) {
        this.value = (short) (this.value + operand);
    }

    public short addAndGet(Number operand) {
        this.value = (short) (this.value + operand.shortValue());
        return this.value;
    }

    public short addAndGet(short operand) {
        this.value = (short) (this.value + operand);
        return this.value;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableShort other) {
        return NumberUtils.compare(this.value, other.value);
    }

    public void decrement() {
        this.value = (short) (this.value - 1);
    }

    public short decrementAndGet() {
        this.value = (short) (this.value - 1);
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableShort) && this.value == ((MutableShort) obj).shortValue();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    public short getAndAdd(Number operand) {
        short last = this.value;
        this.value = (short) (this.value + operand.shortValue());
        return last;
    }

    public short getAndAdd(short operand) {
        short last = this.value;
        this.value = (short) (this.value + operand);
        return last;
    }

    public short getAndDecrement() {
        short last = this.value;
        this.value = (short) (this.value - 1);
        return last;
    }

    public short getAndIncrement() {
        short last = this.value;
        this.value = (short) (this.value + 1);
        return last;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    @Deprecated
    /* renamed from: getValue, reason: avoid collision after fix types in other method */
    public Number getValue2() {
        return Short.valueOf(this.value);
    }

    public int hashCode() {
        return this.value;
    }

    public void increment() {
        this.value = (short) (this.value + 1);
    }

    public short incrementAndGet() {
        this.value = (short) (this.value + 1);
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number value) {
        this.value = value.shortValue();
    }

    public void setValue(short value) {
        this.value = value;
    }

    @Override // java.lang.Number
    public short shortValue() {
        return this.value;
    }

    public void subtract(Number operand) {
        this.value = (short) (this.value - operand.shortValue());
    }

    public void subtract(short operand) {
        this.value = (short) (this.value - operand);
    }

    public Short toShort() {
        return Short.valueOf(shortValue());
    }

    public String toString() {
        return String.valueOf((int) this.value);
    }
}
