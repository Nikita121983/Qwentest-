package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes12.dex */
public interface CTAuthors extends XmlObject {
    public static final DocumentFactory<CTAuthors> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctauthorsb8a7type");
    public static final SchemaType type = Factory.getType();

    void addAuthor(String str);

    STXstring addNewAuthor();

    String getAuthorArray(int i);

    String[] getAuthorArray();

    List<String> getAuthorList();

    void insertAuthor(int i, String str);

    STXstring insertNewAuthor(int i);

    void removeAuthor(int i);

    void setAuthorArray(int i, String str);

    void setAuthorArray(String[] strArr);

    int sizeOfAuthorArray();

    STXstring xgetAuthorArray(int i);

    STXstring[] xgetAuthorArray();

    List<STXstring> xgetAuthorList();

    void xsetAuthorArray(int i, STXstring sTXstring);

    void xsetAuthorArray(STXstring[] sTXstringArr);
}
