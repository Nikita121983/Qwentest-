package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import org.apache.logging.log4j.util.Constants;

/* loaded from: classes10.dex */
public final class ObjectArrayMessage implements Message {
    private static final long serialVersionUID = -5903272448334166185L;
    private transient Object[] array;
    private transient String arrayString;

    public ObjectArrayMessage(final Object... obj) {
        this.array = obj == null ? Constants.EMPTY_OBJECT_ARRAY : obj;
    }

    private boolean equalObjectsOrStrings(final Object[] left, final Object[] right) {
        return Arrays.equals(left, right) || Arrays.toString(left).equals(Arrays.toString(right));
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectArrayMessage that = (ObjectArrayMessage) o;
        if (this.array != null) {
            return equalObjectsOrStrings(this.array, that.array);
        }
        if (that.array == null) {
            return true;
        }
        return false;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return getFormattedMessage();
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.arrayString == null) {
            this.arrayString = Arrays.toString(this.array);
        }
        return this.arrayString;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return this.array;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return null;
    }

    public int hashCode() {
        return Arrays.hashCode(this.array);
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.array = (Object[]) in.readObject();
    }

    public String toString() {
        return getFormattedMessage();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.array);
    }
}
