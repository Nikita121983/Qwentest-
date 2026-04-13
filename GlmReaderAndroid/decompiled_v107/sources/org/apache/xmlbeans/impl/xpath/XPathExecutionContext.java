package org.apache.xmlbeans.impl.xpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;

/* loaded from: classes11.dex */
public class XPathExecutionContext {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ATTRS = 4;
    public static final int DESCEND = 2;
    public static final int HIT = 1;
    private PathContext[] _paths;
    private final ArrayList<QName> _stack = new ArrayList<>();
    private XPath _xpath;

    public final void init(XPath xpath) {
        if (this._xpath != xpath) {
            this._xpath = xpath;
            this._paths = new PathContext[xpath._selector._paths.length];
            Arrays.setAll(this._paths, new IntFunction() { // from class: org.apache.xmlbeans.impl.xpath.XPathExecutionContext$$ExternalSyntheticLambda0
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return XPathExecutionContext.this.m2694xb67da02d(i);
                }
            });
        }
        this._stack.clear();
        for (int i = 0; i < this._paths.length; i++) {
            this._paths[i].init(xpath._selector._paths[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$init$0$org-apache-xmlbeans-impl-xpath-XPathExecutionContext, reason: not valid java name */
    public /* synthetic */ PathContext m2694xb67da02d(int i) {
        return new PathContext();
    }

    public final int start() {
        int result = 0;
        for (PathContext path : this._paths) {
            result |= path.start();
        }
        return result;
    }

    public final int element(QName name) {
        if (name == null) {
            throw new AssertionError();
        }
        this._stack.add(name);
        int result = 0;
        for (PathContext path : this._paths) {
            result |= path.element(name);
        }
        return result;
    }

    public final boolean attr(QName name) {
        boolean hit = false;
        for (PathContext path : this._paths) {
            hit |= path.attr(name);
        }
        return hit;
    }

    public final void end() {
        this._stack.remove(this._stack.size() - 1);
        for (PathContext path : this._paths) {
            path.end();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public final class PathContext {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private XPathStep _curr;
        private final List<XPathStep> _prev;

        private PathContext() {
            this._prev = new ArrayList();
        }

        void init(XPathStep steps) {
            this._curr = steps;
            this._prev.clear();
        }

        private QName top(int i) {
            return (QName) XPathExecutionContext.this._stack.get((XPathExecutionContext.this._stack.size() - 1) - i);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
        
            r3._curr = r3._curr._prev;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void backtrack() {
            /*
                r3 = this;
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                if (r0 == 0) goto L4a
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                boolean r0 = r0._hasBacktrack
                if (r0 == 0) goto L11
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r0._backtrack
                r3._curr = r0
                return
            L11:
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                boolean r0 = r0._deep
                if (r0 != 0) goto L44
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r0._prev
                r3._curr = r0
            L1d:
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                boolean r0 = r0._deep
                if (r0 != 0) goto L43
                r0 = 0
                org.apache.xmlbeans.impl.xpath.XPathStep r1 = r3._curr
            L26:
                boolean r2 = r1._deep
                if (r2 != 0) goto L42
                int r2 = r0 + 1
                javax.xml.namespace.QName r0 = r3.top(r0)
                boolean r0 = r1.match(r0)
                if (r0 != 0) goto L3e
            L37:
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r3._curr
                org.apache.xmlbeans.impl.xpath.XPathStep r0 = r0._prev
                r3._curr = r0
                goto L1d
            L3e:
                org.apache.xmlbeans.impl.xpath.XPathStep r1 = r1._prev
                r0 = r2
                goto L26
            L42:
            L43:
                return
            L44:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            L4a:
                java.lang.AssertionError r0 = new java.lang.AssertionError
                r0.<init>()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xpath.XPathExecutionContext.PathContext.backtrack():void");
        }

        int start() {
            if (this._curr == null) {
                throw new AssertionError();
            }
            if (this._curr._prev != null) {
                throw new AssertionError();
            }
            if (this._curr._name != null) {
                return this._curr._flags;
            }
            this._curr = null;
            return 1;
        }

        int element(QName name) {
            this._prev.add(this._curr);
            if (this._curr == null) {
                return 0;
            }
            if (this._curr._name == null) {
                throw new AssertionError();
            }
            if (!this._curr._attr && this._curr.match(name)) {
                XPathStep xPathStep = this._curr._next;
                this._curr = xPathStep;
                if (xPathStep._name != null) {
                    return this._curr._flags;
                }
                backtrack();
                if (this._curr == null) {
                    return 1;
                }
                return 1 | this._curr._flags;
            }
            while (true) {
                backtrack();
                if (this._curr == null) {
                    return 0;
                }
                if (this._curr.match(name)) {
                    this._curr = this._curr._next;
                    break;
                }
                if (this._curr._deep) {
                    break;
                }
            }
            return this._curr._flags;
        }

        boolean attr(QName name) {
            return this._curr != null && this._curr._attr && this._curr.match(name);
        }

        void end() {
            this._curr = this._prev.remove(this._prev.size() - 1);
        }
    }
}
