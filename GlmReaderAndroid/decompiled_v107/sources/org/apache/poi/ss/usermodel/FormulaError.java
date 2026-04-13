package org.apache.poi.ss.usermodel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes10.dex */
public enum FormulaError {
    _NO_ERROR(-1, "(no error)"),
    NULL(0, "#NULL!"),
    DIV0(7, "#DIV/0!"),
    VALUE(15, "#VALUE!"),
    REF(23, "#REF!"),
    NAME(29, "#NAME?"),
    NUM(36, "#NUM!"),
    NA(42, "#N/A"),
    CIRCULAR_REF(-60, "~CIRCULAR~REF~"),
    FUNCTION_NOT_IMPLEMENTED(-30, "~FUNCTION~NOT~IMPLEMENTED~");

    private static final Map<Byte, FormulaError> bmap;
    private static final Map<Integer, FormulaError> imap;
    private static final Map<String, FormulaError> smap;
    private final int longType;
    private final String repr;
    private final byte type;

    static {
        Map<String, FormulaError> mapS = new HashMap<>();
        Map<Byte, FormulaError> mapB = new HashMap<>();
        Map<Integer, FormulaError> mapI = new HashMap<>();
        for (FormulaError error : values()) {
            mapB.put(Byte.valueOf(error.getCode()), error);
            mapI.put(Integer.valueOf(error.getLongCode()), error);
            mapS.put(error.getString(), error);
        }
        smap = Collections.unmodifiableMap(mapS);
        bmap = Collections.unmodifiableMap(mapB);
        imap = Collections.unmodifiableMap(mapI);
    }

    FormulaError(int type, String repr) {
        this.type = (byte) type;
        this.longType = type;
        this.repr = repr;
    }

    public byte getCode() {
        return this.type;
    }

    public int getLongCode() {
        return this.longType;
    }

    public String getString() {
        return this.repr;
    }

    public static boolean isValidCode(int errorCode) {
        for (FormulaError error : values()) {
            if (error.getCode() == errorCode || error.getLongCode() == errorCode) {
                return true;
            }
        }
        return false;
    }

    public static FormulaError forInt(byte type) throws IllegalArgumentException {
        FormulaError err = bmap.get(Byte.valueOf(type));
        if (err == null) {
            throw new IllegalArgumentException("Unknown error type: " + ((int) type));
        }
        return err;
    }

    public static FormulaError forInt(int type) throws IllegalArgumentException {
        FormulaError err = imap.get(Integer.valueOf(type));
        if (err == null) {
            err = bmap.get(Byte.valueOf((byte) type));
        }
        if (err == null) {
            throw new IllegalArgumentException("Unknown error type: " + type);
        }
        return err;
    }

    public static FormulaError forString(String code) throws IllegalArgumentException {
        FormulaError err = smap.get(code);
        if (err == null) {
            throw new IllegalArgumentException("Unknown error code: " + code);
        }
        return err;
    }
}
