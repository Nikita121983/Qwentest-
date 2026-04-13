package com.graphbuilder.curve;

import com.graphbuilder.math.ExpressionParseException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class ControlStringParseException extends RuntimeException {
    private String descrip;
    private ExpressionParseException epe;
    private int fromIndex;
    private int toIndex;

    public ControlStringParseException(String descrip) {
        this.descrip = null;
        this.fromIndex = -1;
        this.toIndex = -1;
        this.epe = null;
        this.descrip = descrip;
    }

    public ControlStringParseException(String descrip, int index) {
        this.descrip = null;
        this.fromIndex = -1;
        this.toIndex = -1;
        this.epe = null;
        this.descrip = descrip;
        this.fromIndex = index;
        this.toIndex = index;
    }

    public ControlStringParseException(String descrip, int fromIndex, int toIndex) {
        this.descrip = null;
        this.fromIndex = -1;
        this.toIndex = -1;
        this.epe = null;
        this.descrip = descrip;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    public ControlStringParseException(String descrip, int fromIndex, int toIndex, ExpressionParseException epe) {
        this.descrip = null;
        this.fromIndex = -1;
        this.toIndex = -1;
        this.epe = null;
        this.descrip = descrip;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.epe = epe;
    }

    public int getFromIndex() {
        return this.fromIndex;
    }

    public int getToIndex() {
        return this.toIndex;
    }

    public String getDescription() {
        return this.descrip;
    }

    public ExpressionParseException getExpressionParseException() {
        return this.epe;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String e = "";
        if (this.epe != null) {
            e = StringUtils.LF + this.epe.toString();
        }
        if (this.fromIndex == -1 && this.toIndex == -1) {
            return this.descrip + e;
        }
        if (this.fromIndex == this.toIndex) {
            return this.descrip + " : [" + this.toIndex + CollectionUtils.DEFAULT_TOSTRING_SUFFIX + e;
        }
        return this.descrip + " : [" + this.fromIndex + ", " + this.toIndex + CollectionUtils.DEFAULT_TOSTRING_SUFFIX + e;
    }
}
