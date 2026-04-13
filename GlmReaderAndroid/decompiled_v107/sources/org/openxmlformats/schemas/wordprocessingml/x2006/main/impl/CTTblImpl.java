package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

/* loaded from: classes12.dex */
public class CTTblImpl extends XmlComplexContentImpl implements CTTbl {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "bookmarkEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "commentRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlInsRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlDelRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveFromRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXmlMoveToRangeEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblPr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tblGrid"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "tr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "customXml"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "sdt"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "proofErr"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permStart"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "permEnd"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "ins"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "del"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveFrom"), new QName(XSSFRelation.NS_WORDPROCESSINGML, "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath")};
    private static final long serialVersionUID = 1;

    public CTTblImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getBookmarkStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda130
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setBookmarkStartArray(((Integer) obj).intValue(), (CTBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda131
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewBookmarkStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda132
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeBookmarkStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfBookmarkStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfBookmarkStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setBookmarkStartArray(CTBookmark[] bookmarkStartArray) {
        check_orphaned();
        arraySetterHelper(bookmarkStartArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setBookmarkStartArray(int i, CTBookmark bookmarkStart) {
        generatedSetterHelperImpl(bookmarkStart, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTBookmark addNewBookmarkStart() {
        CTBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getBookmarkEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setBookmarkEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeBookmarkEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfBookmarkEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange getBookmarkEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfBookmarkEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setBookmarkEndArray(CTMarkupRange[] bookmarkEndArray) {
        check_orphaned();
        arraySetterHelper(bookmarkEndArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setBookmarkEndArray(int i, CTMarkupRange bookmarkEnd) {
        generatedSetterHelperImpl(bookmarkEnd, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveFromRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda54
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda55
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda56
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromRangeStartArray(CTMoveBookmark[] moveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeStartArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromRangeStartArray(int i, CTMoveBookmark moveFromRangeStart) {
        generatedSetterHelperImpl(moveFromRangeStart, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda98
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda99
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda100
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[3], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromRangeEndArray(CTMarkupRange[] moveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveFromRangeEndArray, PROPERTY_QNAME[3]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromRangeEndArray(int i, CTMarkupRange moveFromRangeEnd) {
        generatedSetterHelperImpl(moveFromRangeEnd, PROPERTY_QNAME[3], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda34
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda35
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveToRangeStartArray(((Integer) obj).intValue(), (CTMoveBookmark) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[4], new CTMoveBookmark[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToRangeStartArray(CTMoveBookmark[] moveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeStartArray, PROPERTY_QNAME[4]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToRangeStartArray(int i, CTMoveBookmark moveToRangeStart) {
        generatedSetterHelperImpl(moveToRangeStart, PROPERTY_QNAME[4], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda93
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda94
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange getMoveToRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToRangeEndArray(CTMarkupRange[] moveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(moveToRangeEndArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToRangeEndArray(int i, CTMarkupRange moveToRangeEnd) {
        generatedSetterHelperImpl(moveToRangeEnd, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCommentRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCommentRangeStartArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda60
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCommentRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda61
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCommentRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange getCommentRangeStartArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCommentRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCommentRangeStartArray(CTMarkupRange[] commentRangeStartArray) {
        check_orphaned();
        arraySetterHelper(commentRangeStartArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCommentRangeStartArray(int i, CTMarkupRange commentRangeStart) {
        generatedSetterHelperImpl(commentRangeStart, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda28
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCommentRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCommentRangeEndArray(((Integer) obj).intValue(), (CTMarkupRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCommentRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda32
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCommentRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[7], new CTMarkupRange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange getCommentRangeEndArray(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCommentRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCommentRangeEndArray(CTMarkupRange[] commentRangeEndArray) {
        check_orphaned();
        arraySetterHelper(commentRangeEndArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCommentRangeEndArray(int i, CTMarkupRange commentRangeEnd) {
        generatedSetterHelperImpl(commentRangeEnd, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlInsRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlInsRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlInsRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlInsRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlInsRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlInsRangeStartArray(CTTrackChange[] customXmlInsRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeStartArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange customXmlInsRangeStart) {
        generatedSetterHelperImpl(customXmlInsRangeStart, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda39
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlInsRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda40
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlInsRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlInsRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlInsRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[9], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlInsRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlInsRangeEndArray(CTMarkup[] customXmlInsRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlInsRangeEndArray, PROPERTY_QNAME[9]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlInsRangeEndArray(int i, CTMarkup customXmlInsRangeEnd) {
        generatedSetterHelperImpl(customXmlInsRangeEnd, PROPERTY_QNAME[9], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda118
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlDelRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlDelRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlDelRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlDelRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[10], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlDelRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlDelRangeStartArray(CTTrackChange[] customXmlDelRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeStartArray, PROPERTY_QNAME[10]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange customXmlDelRangeStart) {
        generatedSetterHelperImpl(customXmlDelRangeStart, PROPERTY_QNAME[10], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda123
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlDelRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda124
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlDelRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda125
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda126
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlDelRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlDelRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[11], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlDelRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlDelRangeEndArray(CTMarkup[] customXmlDelRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlDelRangeEndArray, PROPERTY_QNAME[11]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlDelRangeEndArray(int i, CTMarkup customXmlDelRangeEnd) {
        generatedSetterHelperImpl(customXmlDelRangeEnd, PROPERTY_QNAME[11], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda63
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda64
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlMoveFromRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda66
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlMoveFromRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlMoveFromRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[12], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] customXmlMoveFromRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeStartArray, PROPERTY_QNAME[12]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange customXmlMoveFromRangeStart) {
        generatedSetterHelperImpl(customXmlMoveFromRangeStart, PROPERTY_QNAME[12], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlMoveFromRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda110
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlMoveFromRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlMoveFromRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[13], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] customXmlMoveFromRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveFromRangeEndArray, PROPERTY_QNAME[13]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup customXmlMoveFromRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveFromRangeEnd, PROPERTY_QNAME[13], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlMoveToRangeStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlMoveToRangeStartArray(((Integer) obj).intValue(), (CTTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlMoveToRangeStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlMoveToRangeStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[14], new CTTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] customXmlMoveToRangeStartArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeStartArray, PROPERTY_QNAME[14]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange customXmlMoveToRangeStart) {
        generatedSetterHelperImpl(customXmlMoveToRangeStart, PROPERTY_QNAME[14], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda46
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlMoveToRangeEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda47
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlMoveToRangeEndArray(((Integer) obj).intValue(), (CTMarkup) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda48
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda49
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXmlMoveToRangeEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda50
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlMoveToRangeEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[15], new CTMarkup[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] customXmlMoveToRangeEndArray) {
        check_orphaned();
        arraySetterHelper(customXmlMoveToRangeEndArray, PROPERTY_QNAME[15]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup customXmlMoveToRangeEnd) {
        generatedSetterHelperImpl(customXmlMoveToRangeEnd, PROPERTY_QNAME[15], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTblPr getTblPr() {
        CTTblPr cTTblPr;
        synchronized (monitor()) {
            check_orphaned();
            CTTblPr target = (CTTblPr) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTTblPr = target == null ? null : target;
        }
        return cTTblPr;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setTblPr(CTTblPr tblPr) {
        generatedSetterHelperImpl(tblPr, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTblPr addNewTblPr() {
        CTTblPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblPr) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTblGrid getTblGrid() {
        CTTblGrid cTTblGrid;
        synchronized (monitor()) {
            check_orphaned();
            CTTblGrid target = (CTTblGrid) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTTblGrid = target == null ? null : target;
        }
        return cTTblGrid;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setTblGrid(CTTblGrid tblGrid) {
        generatedSetterHelperImpl(tblGrid, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTTblGrid addNewTblGrid() {
        CTTblGrid target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTblGrid) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTRow> getTrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda112
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getTrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda113
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setTrArray(((Integer) obj).intValue(), (CTRow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewTr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeTr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfTrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRow[] getTrArray() {
        return (CTRow[]) getXmlObjectArray(PROPERTY_QNAME[18], new CTRow[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRow getTrArray(int i) {
        CTRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRow) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfTrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setTrArray(CTRow[] trArray) {
        check_orphaned();
        arraySetterHelper(trArray, PROPERTY_QNAME[18]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setTrArray(int i, CTRow tr) {
        generatedSetterHelperImpl(tr, PROPERTY_QNAME[18], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRow insertNewTr(int i) {
        CTRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRow) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRow addNewTr() {
        CTRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRow) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeTr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTCustomXmlRow> getCustomXmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getCustomXmlArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda18
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setCustomXmlArray(((Integer) obj).intValue(), (CTCustomXmlRow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda19
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewCustomXml(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda20
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeCustomXml(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfCustomXmlArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTCustomXmlRow[] getCustomXmlArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (XmlObject[]) new CTCustomXmlRow[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTCustomXmlRow getCustomXmlArray(int i) {
        CTCustomXmlRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfCustomXmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlArray(CTCustomXmlRow[] customXmlArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) customXmlArray, PROPERTY_QNAME[19]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setCustomXmlArray(int i, CTCustomXmlRow customXml) {
        generatedSetterHelperImpl(customXml, PROPERTY_QNAME[19], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTCustomXmlRow insertNewCustomXml(int i) {
        CTCustomXmlRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTCustomXmlRow addNewCustomXml() {
        CTCustomXmlRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeCustomXml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTSdtRow> getSdtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda101
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getSdtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setSdtArray(((Integer) obj).intValue(), (CTSdtRow) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewSdt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeSdt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda105
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfSdtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTSdtRow[] getSdtArray() {
        return (CTSdtRow[]) getXmlObjectArray(PROPERTY_QNAME[20], new CTSdtRow[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTSdtRow getSdtArray(int i) {
        CTSdtRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRow) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfSdtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setSdtArray(CTSdtRow[] sdtArray) {
        check_orphaned();
        arraySetterHelper(sdtArray, PROPERTY_QNAME[20]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setSdtArray(int i, CTSdtRow sdt) {
        generatedSetterHelperImpl(sdt, PROPERTY_QNAME[20], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTSdtRow insertNewSdt(int i) {
        CTSdtRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRow) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTSdtRow addNewSdt() {
        CTSdtRow target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSdtRow) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeSdt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getProofErrArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setProofErrArray(((Integer) obj).intValue(), (CTProofErr) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda25
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewProofErr(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeProofErr(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda27
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfProofErrArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[21], new CTProofErr[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTProofErr getProofErrArray(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfProofErrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setProofErrArray(CTProofErr[] proofErrArray) {
        check_orphaned();
        arraySetterHelper(proofErrArray, PROPERTY_QNAME[21]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setProofErrArray(int i, CTProofErr proofErr) {
        generatedSetterHelperImpl(proofErr, PROPERTY_QNAME[21], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTProofErr insertNewProofErr(int i) {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTProofErr addNewProofErr() {
        CTProofErr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda74
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getPermStartArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda75
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setPermStartArray(((Integer) obj).intValue(), (CTPermStart) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewPermStart(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda77
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removePermStart(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfPermStartArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[22], new CTPermStart[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPermStart getPermStartArray(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfPermStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setPermStartArray(CTPermStart[] permStartArray) {
        check_orphaned();
        arraySetterHelper(permStartArray, PROPERTY_QNAME[22]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setPermStartArray(int i, CTPermStart permStart) {
        generatedSetterHelperImpl(permStart, PROPERTY_QNAME[22], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPermStart insertNewPermStart(int i) {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPermStart addNewPermStart() {
        CTPermStart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getPermEndArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda69
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setPermEndArray(((Integer) obj).intValue(), (CTPerm) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda70
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewPermEnd(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removePermEnd(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfPermEndArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[23], new CTPerm[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPerm getPermEndArray(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfPermEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setPermEndArray(CTPerm[] permEndArray) {
        check_orphaned();
        arraySetterHelper(permEndArray, PROPERTY_QNAME[23]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setPermEndArray(int i, CTPerm permEnd) {
        generatedSetterHelperImpl(permEnd, PROPERTY_QNAME[23], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPerm insertNewPermEnd(int i) {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTPerm addNewPermEnd() {
        CTPerm target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getInsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda80
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setInsArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda81
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewIns(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda82
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeIns(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfInsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[24], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange getInsArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfInsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setInsArray(CTRunTrackChange[] insArray) {
        check_orphaned();
        arraySetterHelper(insArray, PROPERTY_QNAME[24]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setInsArray(int i, CTRunTrackChange ins) {
        generatedSetterHelperImpl(ins, PROPERTY_QNAME[24], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange addNewIns() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getDelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setDelArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewDel(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda44
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeDel(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda45
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfDelArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[25], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange getDelArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfDelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setDelArray(CTRunTrackChange[] delArray) {
        check_orphaned();
        arraySetterHelper(delArray, PROPERTY_QNAME[25]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setDelArray(int i, CTRunTrackChange del) {
        generatedSetterHelperImpl(del, PROPERTY_QNAME[25], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange addNewDel() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda134
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveFromArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda135
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveFromArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveFrom(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveFrom(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveFromArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[26], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange getMoveFromArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveFromArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromArray(CTRunTrackChange[] moveFromArray) {
        check_orphaned();
        arraySetterHelper(moveFromArray, PROPERTY_QNAME[26]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveFromArray(int i, CTRunTrackChange moveFrom) {
        generatedSetterHelperImpl(moveFrom, PROPERTY_QNAME[26], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getMoveToArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setMoveToArray(((Integer) obj).intValue(), (CTRunTrackChange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda62
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewMoveTo(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeMoveTo(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfMoveToArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[27], new CTRunTrackChange[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToArray(CTRunTrackChange[] moveToArray) {
        check_orphaned();
        arraySetterHelper(moveToArray, PROPERTY_QNAME[27]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setMoveToArray(int i, CTRunTrackChange moveTo) {
        generatedSetterHelperImpl(moveTo, PROPERTY_QNAME[27], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getOMathParaArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda106
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setOMathParaArray(((Integer) obj).intValue(), (CTOMathPara) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda117
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewOMathPara(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeOMathPara(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda139
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfOMathParaArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[28], new CTOMathPara[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfOMathParaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setOMathParaArray(CTOMathPara[] oMathParaArray) {
        check_orphaned();
        arraySetterHelper(oMathParaArray, PROPERTY_QNAME[28]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setOMathParaArray(int i, CTOMathPara oMathPara) {
        generatedSetterHelperImpl(oMathPara, PROPERTY_QNAME[28], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMathPara addNewOMathPara() {
        CTOMathPara target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.getOMathArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda86
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTblImpl.this.setOMathArray(((Integer) obj).intValue(), (CTOMath) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda87
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTblImpl.this.insertNewOMath(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda88
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTblImpl.this.removeOMath(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblImpl$$ExternalSyntheticLambda89
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTblImpl.this.sizeOfOMathArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[29], new CTOMath[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMath getOMathArray(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setOMathArray(CTOMath[] oMathArray) {
        check_orphaned();
        arraySetterHelper(oMathArray, PROPERTY_QNAME[29]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void setOMathArray(int i, CTOMath oMath) {
        generatedSetterHelperImpl(oMath, PROPERTY_QNAME[29], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMath insertNewOMath(int i) {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public CTOMath addNewOMath() {
        CTOMath target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl
    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }
}
