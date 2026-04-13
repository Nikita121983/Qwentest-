package org.apache.commons.lang3.event;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.function.FailableConsumer;

/* loaded from: classes9.dex */
public class EventListenerSupport<L> implements Serializable {
    private static final long serialVersionUID = 3593265990380473632L;
    private List<L> listeners;
    private transient L[] prototypeArray;
    private transient L proxy;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public class ProxyInvocationHandler implements InvocationHandler {
        private final FailableConsumer<Throwable, IllegalAccessException> handler;

        public ProxyInvocationHandler(EventListenerSupport this$0) {
            this(new FailableConsumer() { // from class: org.apache.commons.lang3.event.EventListenerSupport$ProxyInvocationHandler$$ExternalSyntheticLambda0
                @Override // org.apache.commons.lang3.function.FailableConsumer
                public final void accept(Object obj) {
                    ExceptionUtils.rethrow((Throwable) obj);
                }
            });
        }

        public ProxyInvocationHandler(FailableConsumer<Throwable, IllegalAccessException> handler) {
            this.handler = (FailableConsumer) Objects.requireNonNull(handler);
        }

        protected void handle(Throwable t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            this.handler.accept(t);
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object unusedProxy, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Iterator it = EventListenerSupport.this.listeners.iterator();
            while (it.hasNext()) {
                try {
                    method.invoke(it.next(), args);
                } catch (Throwable t) {
                    handle(t);
                }
            }
            return null;
        }
    }

    public static <T> EventListenerSupport<T> create(Class<T> listenerInterface) {
        return new EventListenerSupport<>(listenerInterface);
    }

    private EventListenerSupport() {
        this.listeners = new CopyOnWriteArrayList();
    }

    public EventListenerSupport(Class<L> listenerInterface) {
        this(listenerInterface, Thread.currentThread().getContextClassLoader());
    }

    public EventListenerSupport(Class<L> listenerInterface, ClassLoader classLoader) {
        this();
        Objects.requireNonNull(listenerInterface, "listenerInterface");
        Objects.requireNonNull(classLoader, "classLoader");
        Validate.isTrue(listenerInterface.isInterface(), "Class %s is not an interface", listenerInterface.getName());
        initializeTransientFields(listenerInterface, classLoader);
    }

    public void addListener(L listener) {
        addListener(listener, true);
    }

    public void addListener(L listener, boolean allowDuplicate) {
        Objects.requireNonNull(listener, "listener");
        if (allowDuplicate || !this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    protected InvocationHandler createInvocationHandler() {
        return new ProxyInvocationHandler(this);
    }

    private void createProxy(Class<L> listenerInterface, ClassLoader classLoader) {
        this.proxy = listenerInterface.cast(Proxy.newProxyInstance(classLoader, new Class[]{listenerInterface}, createInvocationHandler()));
    }

    public L fire() {
        return this.proxy;
    }

    int getListenerCount() {
        return this.listeners.size();
    }

    public L[] getListeners() {
        return (L[]) this.listeners.toArray(this.prototypeArray);
    }

    private void initializeTransientFields(Class<L> cls, ClassLoader classLoader) {
        this.prototypeArray = (L[]) ArrayUtils.newInstance(cls, 0);
        createProxy(cls, classLoader);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Object[] objArr = (Object[]) objectInputStream.readObject();
        this.listeners = new CopyOnWriteArrayList(objArr);
        Class<L> listenerInterface = ArrayUtils.getComponentType(objArr);
        initializeTransientFields(listenerInterface, Thread.currentThread().getContextClassLoader());
    }

    public void removeListener(L listener) {
        this.listeners.remove(Objects.requireNonNull(listener, "listener"));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ArrayList<L> serializableListeners = new ArrayList<>();
        ObjectOutputStream testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
        for (L listener : this.listeners) {
            try {
                testObjectOutputStream.writeObject(listener);
                serializableListeners.add(listener);
            } catch (IOException e) {
                testObjectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            }
        }
        objectOutputStream.writeObject(serializableListeners.toArray(this.prototypeArray));
    }
}
