package org.apache.xmlbeans.impl.xb.ltgfmt;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLTOOLS.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TestsDocument extends XmlObject {
    public static final DocumentFactory<TestsDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "tests5621doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Tests extends XmlObject {
        public static final ElementFactory<Tests> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "tests9d6eelemtype");
        public static final SchemaType type = Factory.getType();

        TestCase addNewTest();

        TestCase getTestArray(int i);

        TestCase[] getTestArray();

        List<TestCase> getTestList();

        TestCase insertNewTest(int i);

        void removeTest(int i);

        void setTestArray(int i, TestCase testCase);

        void setTestArray(TestCase[] testCaseArr);

        int sizeOfTestArray();
    }

    Tests addNewTests();

    Tests getTests();

    void setTests(Tests tests);
}
