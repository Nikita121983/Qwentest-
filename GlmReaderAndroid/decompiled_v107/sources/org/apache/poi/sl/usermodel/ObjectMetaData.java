package org.apache.poi.sl.usermodel;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;

/* loaded from: classes10.dex */
public interface ObjectMetaData {
    ClassID getClassID();

    String getObjectName();

    String getOleEntry();

    String getProgId();

    /* loaded from: classes10.dex */
    public enum Application {
        EXCEL_V8("Worksheet", "Excel.Sheet.8", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.EXCEL_V8),
        EXCEL_V12("Worksheet", "Excel.Sheet.12", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.EXCEL_V12),
        WORD_V8("Document", "Word.Document.8", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.WORD_V8),
        WORD_V12("Document", "Word.Document.12", ExtractorFactory.OOXML_PACKAGE, ClassIDPredefined.WORD_V12),
        PDF("PDF", "AcroExch.Document", "Contents", ClassIDPredefined.PDF),
        CUSTOM(null, null, null, null);

        ClassID classId;
        String objectName;
        String oleEntry;
        String progId;

        Application(String objectName, String progId, String oleEntry, ClassIDPredefined classId) {
            this.objectName = objectName;
            this.progId = progId;
            this.classId = classId == null ? null : classId.getClassID();
            this.oleEntry = oleEntry;
        }

        public static Application lookup(String progId) {
            for (Application a : values()) {
                if (a.progId != null && a.progId.equals(progId)) {
                    return a;
                }
            }
            return null;
        }

        public ObjectMetaData getMetaData() {
            return new ObjectMetaData() { // from class: org.apache.poi.sl.usermodel.ObjectMetaData.Application.1
                @Override // org.apache.poi.sl.usermodel.ObjectMetaData
                public String getObjectName() {
                    return Application.this.objectName;
                }

                @Override // org.apache.poi.sl.usermodel.ObjectMetaData
                public String getProgId() {
                    return Application.this.progId;
                }

                @Override // org.apache.poi.sl.usermodel.ObjectMetaData
                public String getOleEntry() {
                    return Application.this.oleEntry;
                }

                @Override // org.apache.poi.sl.usermodel.ObjectMetaData
                public ClassID getClassID() {
                    return Application.this.classId;
                }
            };
        }
    }
}
