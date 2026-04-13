package org.apache.xmlbeans.impl.xb.ltgfmt.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;
import org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl;

/* loaded from: classes11.dex */
public class TestsDocumentImpl extends XmlComplexContentImpl implements TestsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "tests")};
    private static final long serialVersionUID = 1;

    public TestsDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument
    public TestsDocument.Tests getTests() {
        TestsDocument.Tests tests;
        synchronized (monitor()) {
            check_orphaned();
            TestsDocument.Tests target = (TestsDocument.Tests) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            tests = target == null ? null : target;
        }
        return tests;
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument
    public void setTests(TestsDocument.Tests tests) {
        generatedSetterHelperImpl(tests, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument
    public TestsDocument.Tests addNewTests() {
        TestsDocument.Tests target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TestsDocument.Tests) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes11.dex */
    public static class TestsImpl extends XmlComplexContentImpl implements TestsDocument.Tests {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/05/xmlbean/ltgfmt", "test")};
        private static final long serialVersionUID = 1;

        public TestsImpl(SchemaType sType) {
            super(sType);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public List<TestCase> getTestList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return TestsDocumentImpl.TestsImpl.this.getTestArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        TestsDocumentImpl.TestsImpl.this.setTestArray(((Integer) obj).intValue(), (TestCase) obj2);
                    }
                }, new Function() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return TestsDocumentImpl.TestsImpl.this.insertNewTest(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TestsDocumentImpl.TestsImpl.this.removeTest(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: org.apache.xmlbeans.impl.xb.ltgfmt.impl.TestsDocumentImpl$TestsImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(TestsDocumentImpl.TestsImpl.this.sizeOfTestArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public TestCase[] getTestArray() {
            return (TestCase[]) getXmlObjectArray(PROPERTY_QNAME[0], new TestCase[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public TestCase getTestArray(int i) {
            TestCase target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TestCase) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public int sizeOfTestArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public void setTestArray(TestCase[] testArray) {
            check_orphaned();
            arraySetterHelper(testArray, PROPERTY_QNAME[0]);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public void setTestArray(int i, TestCase test) {
            generatedSetterHelperImpl(test, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public TestCase insertNewTest(int i) {
            TestCase target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TestCase) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public TestCase addNewTest() {
            TestCase target;
            synchronized (monitor()) {
                check_orphaned();
                target = (TestCase) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument.Tests
        public void removeTest(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }
    }
}
