package org.apache.xmlbeans.impl.regex;

import java.util.Vector;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class Op {
    static final int ANCHOR = 5;
    static final int BACKREFERENCE = 16;
    static final int CAPTURE = 15;
    static final int CHAR = 1;
    static final int CLOSURE = 7;
    static final int CONDITION = 26;
    static final boolean COUNT = false;
    static final int DOT = 0;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIER = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    static final int NONGREEDYCLOSURE = 8;
    static final int NONGREEDYQUESTION = 10;
    static final int NRANGE = 4;
    static final int QUESTION = 9;
    static final int RANGE = 3;
    static final int STRING = 6;
    static final int UNION = 11;
    static int nofinstances = 0;
    Op next = null;
    int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Op createDot() {
        return new Op(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharOp createChar(int data) {
        return new CharOp(1, data);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharOp createAnchor(int data) {
        return new CharOp(5, data);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharOp createCapture(int number, Op next) {
        CharOp op = new CharOp(15, number);
        op.next = next;
        return op;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UnionOp createUnion(int size) {
        return new UnionOp(11, size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChildOp createClosure(int id) {
        return new ModifierOp(7, id, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChildOp createNonGreedyClosure() {
        return new ChildOp(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChildOp createQuestion(boolean nongreedy) {
        return new ChildOp(nongreedy ? 10 : 9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RangeOp createRange(Token tok) {
        return new RangeOp(3, tok);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChildOp createLook(int type, Op next, Op branch) {
        ChildOp op = new ChildOp(type);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CharOp createBackReference(int refno) {
        return new CharOp(16, refno);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StringOp createString(String literal) {
        return new StringOp(6, literal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ChildOp createIndependent(Op next, Op branch) {
        ChildOp op = new ChildOp(24);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ModifierOp createModifier(Op next, Op branch, int add, int mask) {
        ModifierOp op = new ModifierOp(25, add, mask);
        op.setChild(branch);
        op.next = next;
        return op;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConditionOp createCondition(Op next, int ref, Op conditionflow, Op yesflow, Op noflow) {
        ConditionOp op = new ConditionOp(26, ref, conditionflow, yesflow, noflow);
        op.next = next;
        return op;
    }

    protected Op(int type) {
        this.type = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Op elementAt(int index) {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Op getChild() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getData() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getData2() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RangeToken getToken() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getString() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CharOp extends Op {
        int charData;

        CharOp(int type, int data) {
            super(type);
            this.charData = data;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        int getData() {
            return this.charData;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class UnionOp extends Op {
        Vector branches;

        UnionOp(int type, int size) {
            super(type);
            this.branches = new Vector(size);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void addElement(Op op) {
            this.branches.addElement(op);
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        int size() {
            return this.branches.size();
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        Op elementAt(int index) {
            return (Op) this.branches.elementAt(index);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ChildOp extends Op {
        Op child;

        ChildOp(int type) {
            super(type);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setChild(Op child) {
            this.child = child;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        Op getChild() {
            return this.child;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ModifierOp extends ChildOp {
        int v1;
        int v2;

        ModifierOp(int type, int v1, int v2) {
            super(type);
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        int getData() {
            return this.v1;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        int getData2() {
            return this.v2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class RangeOp extends Op {
        Token tok;

        RangeOp(int type, Token tok) {
            super(type);
            this.tok = tok;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        RangeToken getToken() {
            return (RangeToken) this.tok;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class StringOp extends Op {
        String string;

        StringOp(int type, String literal) {
            super(type);
            this.string = literal;
        }

        @Override // org.apache.xmlbeans.impl.regex.Op
        String getString() {
            return this.string;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ConditionOp extends Op {
        Op condition;
        Op no;
        int refNumber;
        Op yes;

        ConditionOp(int type, int refno, Op conditionflow, Op yesflow, Op noflow) {
            super(type);
            this.refNumber = refno;
            this.condition = conditionflow;
            this.yes = yesflow;
            this.no = noflow;
        }
    }
}
