package org.apache.logging.log4j.message;

import java.util.Arrays;
import org.apache.logging.log4j.message.ParameterFormatter;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.StringBuilders;

/* loaded from: classes10.dex */
public class ReusableParameterizedMessage implements ReusableMessage, ParameterVisitable, Clearable {
    private static final int MAX_PARAMS = 10;
    private static final long serialVersionUID = 7800075879295123856L;
    private int argCount;
    private String messagePattern;
    private transient Throwable throwable;
    private transient Object[] varargs;
    private final ParameterFormatter.MessagePatternAnalysis patternAnalysis = new ParameterFormatter.MessagePatternAnalysis();
    private final StringBuilder formatBuffer = new StringBuilder(Constants.MAX_REUSABLE_MESSAGE_SIZE);
    private transient Object[] params = new Object[10];
    transient boolean reserved = false;

    private Object[] getTrimmedParams() {
        return this.varargs == null ? Arrays.copyOf(this.params, this.argCount) : this.varargs;
    }

    private Object[] getParams() {
        return this.varargs == null ? this.params : this.varargs;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Object[] swapParameters(final Object[] emptyReplacement) {
        Object[] result;
        if (this.varargs == null) {
            Object[] result2 = this.params;
            if (emptyReplacement.length >= 10) {
                this.params = emptyReplacement;
                return result2;
            }
            if (this.argCount <= emptyReplacement.length) {
                System.arraycopy(this.params, 0, emptyReplacement, 0, this.argCount);
                for (int i = 0; i < this.argCount; i++) {
                    this.params[i] = null;
                }
                return emptyReplacement;
            }
            this.params = new Object[10];
            return result2;
        }
        if (this.argCount <= emptyReplacement.length) {
            result = emptyReplacement;
        } else {
            result = new Object[this.argCount];
        }
        System.arraycopy(this.varargs, 0, result, 0, this.argCount);
        return result;
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public short getParameterCount() {
        return (short) this.argCount;
    }

    @Override // org.apache.logging.log4j.message.ParameterVisitable
    public <S> void forEachParameter(final ParameterConsumer<S> action, final S state) {
        Object[] parameters = getParams();
        for (short i = 0; i < this.argCount; i = (short) (i + 1)) {
            action.accept(parameters[i], i, state);
        }
    }

    @Override // org.apache.logging.log4j.message.ReusableMessage
    public Message memento() {
        return new ParameterizedMessage(this.messagePattern, getTrimmedParams());
    }

    private void init(final String messagePattern, final int argCount, final Object[] args) {
        this.varargs = null;
        this.messagePattern = messagePattern;
        this.argCount = argCount;
        ParameterFormatter.analyzePattern(messagePattern, argCount, this.patternAnalysis);
        this.throwable = determineThrowable(args, argCount, this.patternAnalysis.placeholderCount);
    }

    private static Throwable determineThrowable(final Object[] args, final int argCount, final int placeholderCount) {
        if (placeholderCount < argCount) {
            Object lastArg = args[argCount - 1];
            if (lastArg instanceof Throwable) {
                return (Throwable) lastArg;
            }
            return null;
        }
        return null;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object... arguments) {
        init(messagePattern, arguments == null ? 0 : arguments.length, arguments);
        this.varargs = arguments;
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0) {
        this.params[0] = p0;
        init(messagePattern, 1, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1) {
        this.params[0] = p0;
        this.params[1] = p1;
        init(messagePattern, 2, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        init(messagePattern, 3, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        init(messagePattern, 4, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        init(messagePattern, 5, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        this.params[5] = p5;
        init(messagePattern, 6, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        this.params[5] = p5;
        this.params[6] = p6;
        init(messagePattern, 7, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        this.params[5] = p5;
        this.params[6] = p6;
        this.params[7] = p7;
        init(messagePattern, 8, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        this.params[5] = p5;
        this.params[6] = p6;
        this.params[7] = p7;
        this.params[8] = p8;
        init(messagePattern, 9, this.params);
        return this;
    }

    public ReusableParameterizedMessage set(final String messagePattern, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
        this.params[0] = p0;
        this.params[1] = p1;
        this.params[2] = p2;
        this.params[3] = p3;
        this.params[4] = p4;
        this.params[5] = p5;
        this.params[6] = p6;
        this.params[7] = p7;
        this.params[8] = p8;
        this.params[9] = p9;
        init(messagePattern, 10, this.params);
        return this;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return getTrimmedParams();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        try {
            formatTo(this.formatBuffer);
            return this.formatBuffer.toString();
        } finally {
            StringBuilders.trimToMaxSize(this.formatBuffer, Constants.MAX_REUSABLE_MESSAGE_SIZE);
            this.formatBuffer.setLength(0);
        }
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(final StringBuilder builder) {
        ParameterFormatter.formatMessage(builder, this.messagePattern, getParams(), this.argCount, this.patternAnalysis);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReusableParameterizedMessage reserve() {
        this.reserved = true;
        return this;
    }

    public String toString() {
        return "ReusableParameterizedMessage[messagePattern=" + getFormat() + ", argCount=" + ((int) getParameterCount()) + ", throwableProvided=" + (getThrowable() != null) + ']';
    }

    @Override // org.apache.logging.log4j.message.Clearable
    public void clear() {
        this.reserved = false;
        this.varargs = null;
        this.messagePattern = null;
        this.throwable = null;
        if (this.patternAnalysis.placeholderCharIndices != null && this.patternAnalysis.placeholderCharIndices.length > 16) {
            this.patternAnalysis.placeholderCharIndices = new int[16];
        }
    }

    private Object writeReplace() {
        return memento();
    }
}
