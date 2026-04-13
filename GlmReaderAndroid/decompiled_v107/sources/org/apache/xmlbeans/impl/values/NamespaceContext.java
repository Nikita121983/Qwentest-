package org.apache.xmlbeans.impl.values;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Map;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.xml.stream.StartElement;

/* loaded from: classes11.dex */
public class NamespaceContext implements PrefixResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAP = 3;
    private static final int RESOLVER = 5;
    private static final int START_ELEMENT = 4;
    private static final int TYPE_STORE = 1;
    private static final int XML_OBJECT = 2;
    private static ThreadLocal tl_namespaceContextStack = new ThreadLocal();
    private int _code = 3;
    private Object _obj;

    public NamespaceContext(Map prefixToUriMap) {
        this._obj = prefixToUriMap;
    }

    public NamespaceContext(TypeStore typeStore) {
        this._obj = typeStore;
    }

    public NamespaceContext(XmlObject xmlObject) {
        this._obj = xmlObject;
    }

    public NamespaceContext(StartElement start) {
        this._obj = start;
    }

    public NamespaceContext(PrefixResolver resolver) {
        this._obj = resolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class NamespaceContextStack {
        NamespaceContext current;
        ArrayList<NamespaceContext> stack;

        private NamespaceContextStack() {
            this.stack = new ArrayList<>();
        }

        final void push(NamespaceContext next) {
            this.stack.add(this.current);
            this.current = next;
        }

        final void pop() {
            this.current = this.stack.get(this.stack.size() - 1);
            this.stack.remove(this.stack.size() - 1);
        }
    }

    public static void clearThreadLocals() {
        tl_namespaceContextStack.remove();
    }

    private static NamespaceContextStack getNamespaceContextStack() {
        NamespaceContextStack namespaceContextStack = (NamespaceContextStack) tl_namespaceContextStack.get();
        if (namespaceContextStack == null) {
            NamespaceContextStack namespaceContextStack2 = new NamespaceContextStack();
            tl_namespaceContextStack.set(namespaceContextStack2);
            return namespaceContextStack2;
        }
        return namespaceContextStack;
    }

    public static void push(NamespaceContext next) {
        getNamespaceContextStack().push(next);
    }

    public static void pop() {
        NamespaceContextStack nsContextStack = getNamespaceContextStack();
        nsContextStack.pop();
        if (nsContextStack.stack.size() == 0) {
            tl_namespaceContextStack.set(null);
        }
    }

    public static PrefixResolver getCurrent() {
        return getNamespaceContextStack().current;
    }

    @Override // org.apache.xmlbeans.impl.common.PrefixResolver
    public String getNamespaceForPrefix(String prefix) {
        if (prefix != null && prefix.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        switch (this._code) {
            case 1:
                return ((TypeStore) this._obj).getNamespaceForPrefix(prefix);
            case 2:
                Object obj = this._obj;
                if (Proxy.isProxyClass(obj.getClass())) {
                    obj = Proxy.getInvocationHandler(obj);
                }
                if (obj instanceof TypeStoreUser) {
                    return ((TypeStoreUser) obj).get_store().getNamespaceForPrefix(prefix);
                }
                XmlCursor cur = ((XmlObject) this._obj).newCursor();
                if (cur == null) {
                    if (cur != null) {
                        cur.close();
                        break;
                    }
                } else {
                    try {
                        if (cur.currentTokenType() == XmlCursor.TokenType.ATTR) {
                            cur.toParent();
                        }
                        String namespaceForPrefix = cur.namespaceForPrefix(prefix);
                        if (cur != null) {
                            cur.close();
                        }
                        return namespaceForPrefix;
                    } finally {
                    }
                }
                break;
            case 3:
                break;
            case 4:
                return ((StartElement) this._obj).getNamespaceUri(prefix);
            case 5:
                return ((PrefixResolver) this._obj).getNamespaceForPrefix(prefix);
            default:
                throw new AssertionError("Improperly initialized NamespaceContext.");
        }
        return (String) ((Map) this._obj).get(prefix);
    }
}
