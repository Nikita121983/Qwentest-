package com.microsoft.schemas.compatibility.impl;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class AlternateContentDocumentImpl extends XmlComplexContentImpl implements AlternateContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "AlternateContent")};
    private static final long serialVersionUID = 1;

    public AlternateContentDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.compatibility.AlternateContentDocument
    public AlternateContentDocument.AlternateContent getAlternateContent() {
        AlternateContentDocument.AlternateContent alternateContent;
        synchronized (monitor()) {
            check_orphaned();
            AlternateContentDocument.AlternateContent target = (AlternateContentDocument.AlternateContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            alternateContent = target == null ? null : target;
        }
        return alternateContent;
    }

    @Override // com.microsoft.schemas.compatibility.AlternateContentDocument
    public void setAlternateContent(AlternateContentDocument.AlternateContent alternateContent) {
        generatedSetterHelperImpl(alternateContent, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.compatibility.AlternateContentDocument
    public AlternateContentDocument.AlternateContent addNewAlternateContent() {
        AlternateContentDocument.AlternateContent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (AlternateContentDocument.AlternateContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    /* loaded from: classes.dex */
    public static class AlternateContentImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent {
        private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Choice"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Fallback"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
        private static final long serialVersionUID = 1;

        public AlternateContentImpl(SchemaType sType) {
            super(sType);
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public List<AlternateContentDocument.AlternateContent.Choice> getChoiceList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AlternateContentDocumentImpl.AlternateContentImpl.this.getChoiceArray(((Integer) obj).intValue());
                    }
                }, new BiConsumer() { // from class: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        AlternateContentDocumentImpl.AlternateContentImpl.this.setChoiceArray(((Integer) obj).intValue(), (AlternateContentDocument.AlternateContent.Choice) obj2);
                    }
                }, new Function() { // from class: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return AlternateContentDocumentImpl.AlternateContentImpl.this.insertNewChoice(((Integer) obj).intValue());
                    }
                }, new Consumer() { // from class: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda3
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AlternateContentDocumentImpl.AlternateContentImpl.this.removeChoice(((Integer) obj).intValue());
                    }
                }, new Supplier() { // from class: com.microsoft.schemas.compatibility.impl.AlternateContentDocumentImpl$AlternateContentImpl$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return Integer.valueOf(AlternateContentDocumentImpl.AlternateContentImpl.this.sizeOfChoiceArray());
                    }
                });
            }
            return javaListXmlObject;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Choice[] getChoiceArray() {
            return (AlternateContentDocument.AlternateContent.Choice[]) getXmlObjectArray(PROPERTY_QNAME[0], new AlternateContentDocument.AlternateContent.Choice[0]);
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Choice getChoiceArray(int i) {
            AlternateContentDocument.AlternateContent.Choice target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AlternateContentDocument.AlternateContent.Choice) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public int sizeOfChoiceArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setChoiceArray(AlternateContentDocument.AlternateContent.Choice[] choiceArray) {
            check_orphaned();
            arraySetterHelper(choiceArray, PROPERTY_QNAME[0]);
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setChoiceArray(int i, AlternateContentDocument.AlternateContent.Choice choice) {
            generatedSetterHelperImpl(choice, PROPERTY_QNAME[0], i, (short) 2);
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Choice insertNewChoice(int i) {
            AlternateContentDocument.AlternateContent.Choice target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AlternateContentDocument.AlternateContent.Choice) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Choice addNewChoice() {
            AlternateContentDocument.AlternateContent.Choice target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AlternateContentDocument.AlternateContent.Choice) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void removeChoice(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Fallback getFallback() {
            AlternateContentDocument.AlternateContent.Fallback fallback;
            synchronized (monitor()) {
                check_orphaned();
                AlternateContentDocument.AlternateContent.Fallback target = (AlternateContentDocument.AlternateContent.Fallback) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                fallback = target == null ? null : target;
            }
            return fallback;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public boolean isSetFallback() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = true;
                if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                    z = false;
                }
            }
            return z;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setFallback(AlternateContentDocument.AlternateContent.Fallback fallback) {
            generatedSetterHelperImpl(fallback, PROPERTY_QNAME[1], 0, (short) 1);
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public AlternateContentDocument.AlternateContent.Fallback addNewFallback() {
            AlternateContentDocument.AlternateContent.Fallback target;
            synchronized (monitor()) {
                check_orphaned();
                target = (AlternateContentDocument.AlternateContent.Fallback) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void unsetFallback() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public String getIgnorable() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public XmlString xgetIgnorable() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public boolean isSetIgnorable() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setIgnorable(String ignorable) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.setStringValue(ignorable);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void xsetIgnorable(XmlString ignorable) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (target == null) {
                    target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                }
                target.set(ignorable);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void unsetIgnorable() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public String getMustUnderstand() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public XmlString xgetMustUnderstand() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public boolean isSetMustUnderstand() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
            }
            return z;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setMustUnderstand(String mustUnderstand) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
                }
                target.setStringValue(mustUnderstand);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void xsetMustUnderstand(XmlString mustUnderstand) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                if (target == null) {
                    target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
                }
                target.set(mustUnderstand);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void unsetMustUnderstand() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[3]);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public String getProcessContent() {
            String stringValue;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
                stringValue = target == null ? null : target.getStringValue();
            }
            return stringValue;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public XmlString xgetProcessContent() {
            XmlString target;
            synchronized (monitor()) {
                check_orphaned();
                target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            }
            return target;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public boolean isSetProcessContent() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
            }
            return z;
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void setProcessContent(String processContent) {
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
                if (target == null) {
                    target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
                }
                target.setStringValue(processContent);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void xsetProcessContent(XmlString processContent) {
            synchronized (monitor()) {
                check_orphaned();
                XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[4]);
                if (target == null) {
                    target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[4]);
                }
                target.set(processContent);
            }
        }

        @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent
        public void unsetProcessContent() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[4]);
            }
        }

        /* loaded from: classes.dex */
        public static class ChoiceImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent.Choice {
            private static final QName[] PROPERTY_QNAME = {new QName("", "Requires"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
            private static final long serialVersionUID = 1;

            public ChoiceImpl(SchemaType sType) {
                super(sType);
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public String getRequires() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public XmlString xgetRequires() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void setRequires(String requires) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                    }
                    target.setStringValue(requires);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void xsetRequires(XmlString requires) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                    }
                    target.set(requires);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public String getIgnorable() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public XmlString xgetIgnorable() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public boolean isSetIgnorable() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = true;
                    if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                        z = false;
                    }
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void setIgnorable(String ignorable) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                    }
                    target.setStringValue(ignorable);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void xsetIgnorable(XmlString ignorable) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                    }
                    target.set(ignorable);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void unsetIgnorable() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[1]);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public String getMustUnderstand() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public XmlString xgetMustUnderstand() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public boolean isSetMustUnderstand() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void setMustUnderstand(String mustUnderstand) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                    }
                    target.setStringValue(mustUnderstand);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void xsetMustUnderstand(XmlString mustUnderstand) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                    }
                    target.set(mustUnderstand);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void unsetMustUnderstand() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[2]);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public String getProcessContent() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public XmlString xgetProcessContent() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public boolean isSetProcessContent() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void setProcessContent(String processContent) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
                    }
                    target.setStringValue(processContent);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void xsetProcessContent(XmlString processContent) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[3]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[3]);
                    }
                    target.set(processContent);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Choice
            public void unsetProcessContent() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[3]);
                }
            }
        }

        /* loaded from: classes.dex */
        public static class FallbackImpl extends XmlComplexContentImpl implements AlternateContentDocument.AlternateContent.Fallback {
            private static final QName[] PROPERTY_QNAME = {new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "Ignorable"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "MustUnderstand"), new QName(PackageNamespaces.MARKUP_COMPATIBILITY, "ProcessContent")};
            private static final long serialVersionUID = 1;

            public FallbackImpl(SchemaType sType) {
                super(sType);
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public String getIgnorable() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public XmlString xgetIgnorable() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public boolean isSetIgnorable() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[0]) != null;
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void setIgnorable(String ignorable) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                    }
                    target.setStringValue(ignorable);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void xsetIgnorable(XmlString ignorable) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[0]);
                    }
                    target.set(ignorable);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void unsetIgnorable() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[0]);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public String getMustUnderstand() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public XmlString xgetMustUnderstand() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public boolean isSetMustUnderstand() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = true;
                    if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                        z = false;
                    }
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void setMustUnderstand(String mustUnderstand) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                    }
                    target.setStringValue(mustUnderstand);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void xsetMustUnderstand(XmlString mustUnderstand) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[1]);
                    }
                    target.set(mustUnderstand);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void unsetMustUnderstand() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[1]);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public String getProcessContent() {
                String stringValue;
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    stringValue = target == null ? null : target.getStringValue();
                }
                return stringValue;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public XmlString xgetProcessContent() {
                XmlString target;
                synchronized (monitor()) {
                    check_orphaned();
                    target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                }
                return target;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public boolean isSetProcessContent() {
                boolean z;
                synchronized (monitor()) {
                    check_orphaned();
                    z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
                }
                return z;
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void setProcessContent(String processContent) {
                synchronized (monitor()) {
                    check_orphaned();
                    SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (target == null) {
                        target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                    }
                    target.setStringValue(processContent);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void xsetProcessContent(XmlString processContent) {
                synchronized (monitor()) {
                    check_orphaned();
                    XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                    if (target == null) {
                        target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[2]);
                    }
                    target.set(processContent);
                }
            }

            @Override // com.microsoft.schemas.compatibility.AlternateContentDocument.AlternateContent.Fallback
            public void unsetProcessContent() {
                synchronized (monitor()) {
                    check_orphaned();
                    get_store().remove_attribute(PROPERTY_QNAME[2]);
                }
            }
        }
    }
}
