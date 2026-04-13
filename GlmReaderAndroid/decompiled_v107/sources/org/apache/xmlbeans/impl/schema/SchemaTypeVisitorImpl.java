package org.apache.xmlbeans.impl.schema;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;

/* loaded from: classes11.dex */
public class SchemaTypeVisitorImpl implements TypeStoreVisitor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final boolean CHECK_VALIDITY = false;
    static final boolean PROBE_VALIDITY = true;
    private boolean _isValid;
    private SchemaParticle _matchedParticle;
    private VisitorState[] _rollback;
    private int _rollbackIndex;
    int _rollbackSize;
    private VisitorState[] _stack;
    int _stackSize;
    private VisitorState _top;

    public SchemaTypeVisitorImpl(SchemaParticle part) {
        init(part);
    }

    public SchemaTypeVisitorImpl() {
    }

    public void init(SchemaParticle part) {
        if (this._stack == null) {
            this._stack = expand(null);
        }
        if (this._rollback == null) {
            this._rollback = expand(null);
        }
        this._stackSize = 0;
        this._rollbackSize = 0;
        if (part != null) {
            push(part);
            this._rollbackIndex = 1;
        }
    }

    public VisitorState[] expand(VisitorState[] orig) {
        int newsize = orig == null ? 4 : orig.length * 2;
        VisitorState[] result = new VisitorState[newsize];
        if (orig != null) {
            System.arraycopy(orig, 0, result, 0, orig.length);
        }
        for (int i = orig != null ? orig.length : 0; i < newsize; i++) {
            result[i] = new VisitorState();
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class VisitorState {
        int _childCount;
        int _curCount;
        int _curMax;
        int _curMin;
        SchemaParticle _curPart;
        int _processedChildCount;
        boolean[] _seen;

        private VisitorState() {
        }

        public void copy(VisitorState orig) {
            this._curPart = orig._curPart;
            this._curCount = orig._curCount;
            this._curMin = orig._curMin;
            this._curMax = orig._curMax;
            this._processedChildCount = orig._processedChildCount;
            this._childCount = orig._childCount;
            if (orig._seen != null) {
                this._seen = new boolean[orig._seen.length];
                System.arraycopy(orig._seen, 0, this._seen, 0, orig._seen.length);
            }
        }

        public void init(SchemaParticle part) {
            this._curPart = part;
            this._curMin = part.getIntMinOccurs();
            this._curMax = part.getIntMaxOccurs();
            this._curCount = 0;
            this._processedChildCount = 0;
            this._childCount = part.countOfParticleChild();
            this._seen = part.getParticleType() == 1 ? new boolean[this._childCount] : null;
        }
    }

    VisitorState topRef() {
        return this._stack[this._stackSize - 1];
    }

    void saveCopy(VisitorState ref) {
        if (this._rollback.length == this._rollbackSize) {
            this._rollback = expand(this._rollback);
        }
        this._rollback[this._rollbackSize].copy(ref);
        this._rollbackSize++;
    }

    void addParticle(SchemaParticle part) {
        if (this._stack.length == this._stackSize) {
            this._stack = expand(this._stack);
        }
        this._stack[this._stackSize].init(part);
        this._stackSize++;
    }

    boolean prepare() {
        if (this._rollbackIndex == 0) {
            this._top = null;
            return false;
        }
        this._top = topRef();
        saveCopy(this._top);
        this._rollbackIndex = this._stackSize - 1;
        return true;
    }

    void push(SchemaParticle part) {
        addParticle(part);
        this._top = topRef();
    }

    boolean pop() {
        this._stackSize--;
        if (this._stackSize <= this._rollbackIndex) {
            return prepare();
        }
        this._top = topRef();
        return true;
    }

    void commit() {
        this._top = null;
        this._rollbackIndex = this._stackSize;
        this._rollbackSize = 0;
    }

    void rollback() {
        while (this._rollbackSize > 0) {
            this._rollbackSize--;
            VisitorState temp = this._stack[this._rollbackIndex];
            this._stack[this._rollbackIndex] = this._rollback[this._rollbackSize];
            this._rollback[this._rollbackSize] = temp;
            this._rollbackIndex++;
        }
        this._stackSize = this._rollbackIndex;
        this._top = null;
    }

    boolean notValid() {
        this._isValid = false;
        this._matchedParticle = null;
        rollback();
        return false;
    }

    boolean ok(SchemaParticle part, boolean testValidity) {
        if (!testValidity) {
            this._matchedParticle = part;
            commit();
            return true;
        }
        rollback();
        return true;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreVisitor
    public boolean visit(QName eltName) {
        return visit(eltName, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:123:0x00df, code lost:
    
        return notValid();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0188, code lost:
    
        if (r9 != null) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x018f, code lost:
    
        return ok(null, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0194, code lost:
    
        return notValid();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x004b. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0188 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x000d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean visit(javax.xml.namespace.QName r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl.visit(javax.xml.namespace.QName, boolean):boolean");
    }

    public boolean testValid(QName eltName) {
        return visit(eltName, true);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreVisitor
    public int get_elementflags() {
        if (currentParticle() == null || currentParticle().getParticleType() != 4) {
            return 0;
        }
        SchemaLocalElement schemaLocalElement = (SchemaLocalElement) currentParticle();
        return (schemaLocalElement.isFixed() ? 4 : 0) | (schemaLocalElement.isNillable() ? 1 : 0) | (schemaLocalElement.isDefault() ? 2 : 0);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreVisitor
    public String get_default_text() {
        if (currentParticle() == null || currentParticle().getParticleType() != 4) {
            return null;
        }
        return ((SchemaLocalElement) currentParticle()).getDefaultText();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreVisitor
    public SchemaField get_schema_field() {
        if (currentParticle() instanceof SchemaField) {
            return (SchemaField) currentParticle();
        }
        return null;
    }

    public SchemaParticle currentParticle() {
        return this._matchedParticle;
    }

    public boolean isAllValid() {
        return this._isValid;
    }
}
