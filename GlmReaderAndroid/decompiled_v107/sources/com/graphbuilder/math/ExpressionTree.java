package com.graphbuilder.math;

import com.graphbuilder.struc.Stack;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes.dex */
public class ExpressionTree {
    private static int MAX_LENGTH = 10000;

    private ExpressionTree() {
    }

    public static Expression parse(String s) {
        if (s == null) {
            throw new ExpressionParseException("Expression string cannot be null.", -1);
        }
        if (s.length() > MAX_LENGTH) {
            throw new ExpressionParseException("Expression string is too long.", -1);
        }
        return build(s, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x021f A[LOOP:4: B:124:0x0166->B:132:0x021f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017f A[EDGE_INSN: B:133:0x017f->B:134:0x017f BREAK  A[LOOP:4: B:124:0x0166->B:132:0x021f], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.graphbuilder.math.Expression build(java.lang.String r18, int r19) {
        /*
            Method dump skipped, instructions count: 756
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.graphbuilder.math.ExpressionTree.build(java.lang.String, int):com.graphbuilder.math.Expression");
    }

    private static Expression build(Stack s1, Stack s2) {
        Stack s3 = new Stack();
        Stack s4 = new Stack();
        while (!s2.isEmpty()) {
            Object o = s2.removeTail();
            Object o1 = s1.removeTail();
            Object o2 = s1.removeTail();
            if (o.equals("^")) {
                s1.addToTail(new PowNode((Expression) o1, (Expression) o2));
            } else {
                s1.addToTail(o2);
                s4.push(o);
                s3.push(o1);
            }
        }
        s3.push(s1.pop());
        while (!s4.isEmpty()) {
            Object o3 = s4.removeTail();
            Object o12 = s3.removeTail();
            Object o22 = s3.removeTail();
            if (o3.equals("*")) {
                s3.addToTail(new MultNode((Expression) o12, (Expression) o22));
            } else if (o3.equals(PackagingURIHelper.FORWARD_SLASH_STRING)) {
                s3.addToTail(new DivNode((Expression) o12, (Expression) o22));
            } else {
                s3.addToTail(o22);
                s2.push(o3);
                s1.push(o12);
            }
        }
        s1.push(s3.pop());
        while (!s2.isEmpty()) {
            Object o4 = s2.removeTail();
            Object o13 = s1.removeTail();
            Object o23 = s1.removeTail();
            if (o4.equals("+")) {
                s1.addToTail(new AddNode((Expression) o13, (Expression) o23));
            } else if (o4.equals(ProcessIdUtil.DEFAULT_PROCESSID)) {
                s1.addToTail(new SubNode((Expression) o13, (Expression) o23));
            } else {
                throw new ExpressionParseException("Unknown operator: " + o4, -1);
            }
        }
        return (Expression) s1.pop();
    }
}
