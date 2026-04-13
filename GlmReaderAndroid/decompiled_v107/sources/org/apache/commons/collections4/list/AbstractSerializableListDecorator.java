package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class AbstractSerializableListDecorator<E> extends AbstractListDecorator<E> {
    private static final long serialVersionUID = 2684959196747496299L;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSerializableListDecorator(List<E> list) {
        super(list);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setCollection((Collection) in.readObject());
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(decorated());
    }
}
