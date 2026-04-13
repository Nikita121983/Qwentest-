package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda66;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors;

/* loaded from: classes12.dex */
public class CTAuthorsImpl extends XmlComplexContentImpl implements CTAuthors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "author")};
    private static final long serialVersionUID = 1;

    public CTAuthorsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public List<String> getAuthorList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAuthorsImpl.this.getAuthorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAuthorsImpl.this.setAuthorArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAuthorsImpl.this.insertAuthor(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTAuthorsImpl$$ExternalSyntheticLambda4(this), new CTAuthorsImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getAuthorArray$0(int x$0) {
        return new String[x$0];
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public String[] getAuthorArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[0], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTAuthorsImpl.lambda$getAuthorArray$0(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public String getAuthorArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public List<STXstring> xgetAuthorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAuthorsImpl.this.xgetAuthorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTAuthorsImpl.this.xsetAuthorArray(((Integer) obj).intValue(), (STXstring) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTAuthorsImpl.this.insertNewAuthor(((Integer) obj).intValue());
                }
            }, new CTAuthorsImpl$$ExternalSyntheticLambda4(this), new CTAuthorsImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STXstring[] lambda$xgetAuthorArray$1(int x$0) {
        return new STXstring[x$0];
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public STXstring[] xgetAuthorArray() {
        return (STXstring[]) xgetArray(PROPERTY_QNAME[0], new IntFunction() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTAuthorsImpl.lambda$xgetAuthorArray$1(i);
            }
        });
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public STXstring xgetAuthorArray(int i) {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public int sizeOfAuthorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void setAuthorArray(String[] authorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(authorArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void setAuthorArray(int i, String author) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(author);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void xsetAuthorArray(STXstring[] authorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(authorArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void xsetAuthorArray(int i, STXstring author) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(author);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void insertAuthor(int i, String author) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            target.setStringValue(author);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void addAuthor(String author) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            target.setStringValue(author);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public STXstring insertNewAuthor(int i) {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public STXstring addNewAuthor() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors
    public void removeAuthor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
