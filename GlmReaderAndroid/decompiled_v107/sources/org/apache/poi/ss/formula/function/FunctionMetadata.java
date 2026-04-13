package org.apache.poi.ss.formula.function;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes10.dex */
public final class FunctionMetadata {
    private static final short FUNCTION_MAX_PARAMS = 30;
    private final int _index;
    private final int _maxParams;
    private final int _minParams;
    private final String _name;
    private final byte[] _parameterClassCodes;
    private final byte _returnClassCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FunctionMetadata(int index, String name, int minParams, int maxParams, byte returnClassCode, byte[] parameterClassCodes) {
        this._index = index;
        this._name = name;
        this._minParams = minParams;
        this._maxParams = maxParams;
        this._returnClassCode = returnClassCode;
        this._parameterClassCodes = parameterClassCodes == null ? null : (byte[]) parameterClassCodes.clone();
    }

    public int getIndex() {
        return this._index;
    }

    public String getName() {
        return this._name;
    }

    public int getMinParams() {
        return this._minParams;
    }

    public int getMaxParams() {
        return this._maxParams;
    }

    public boolean hasFixedArgsLength() {
        return this._minParams == this._maxParams;
    }

    public byte getReturnClassCode() {
        return this._returnClassCode;
    }

    public byte[] getParameterClassCodes() {
        return (byte[]) this._parameterClassCodes.clone();
    }

    public boolean hasUnlimitedVarags() {
        return 30 == this._maxParams;
    }

    public String toString() {
        return getClass().getName() + " [" + this._index + StringUtils.SPACE + this._name + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
