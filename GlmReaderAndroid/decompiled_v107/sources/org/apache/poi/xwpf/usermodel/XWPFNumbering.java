package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.NumberingDocument;

/* loaded from: classes10.dex */
public class XWPFNumbering extends POIXMLDocumentPart {
    protected List<XWPFAbstractNum> abstractNums;
    private CTNumbering ctNumbering;
    boolean isNew;
    protected List<XWPFNum> nums;

    public XWPFNumbering(PackagePart part) {
        super(part);
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.isNew = true;
    }

    public XWPFNumbering() {
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.abstractNums = new ArrayList();
        this.nums = new ArrayList();
        this.isNew = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() throws IOException {
        InputStream is = getPackagePart().getInputStream();
        try {
            try {
                NumberingDocument numberingDoc = NumberingDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.ctNumbering = numberingDoc.getNumbering();
                for (CTNum ctNum : this.ctNumbering.getNumArray()) {
                    this.nums.add(new XWPFNum(ctNum, this));
                }
                for (CTAbstractNum ctAbstractNum : this.ctNumbering.getAbstractNumArray()) {
                    this.abstractNums.add(new XWPFAbstractNum(ctAbstractNum, this));
                }
                this.isNew = false;
            } catch (XmlException e) {
                throw new POIXMLException();
            }
        } finally {
            is.close();
        }
    }

    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTNumbering.type.getName().getNamespaceURI(), "numbering"));
        PackagePart part = getPackagePart();
        if (this.ctNumbering != null) {
            OutputStream out = part.getOutputStream();
            try {
                this.ctNumbering.save(out, xmlOptions);
                if (out != null) {
                    out.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
    }

    public void setNumbering(CTNumbering numbering) {
        this.ctNumbering = numbering;
    }

    public boolean numExist(BigInteger numID) {
        for (XWPFNum num : this.nums) {
            if (num.getCTNum().getNumId().equals(numID)) {
                return true;
            }
        }
        return false;
    }

    public BigInteger addNum(XWPFNum num) {
        this.ctNumbering.addNewNum();
        int pos = this.ctNumbering.sizeOfNumArray() - 1;
        this.ctNumbering.setNumArray(pos, num.getCTNum());
        this.nums.add(num);
        return num.getCTNum().getNumId();
    }

    public BigInteger addNum(BigInteger abstractNumID) {
        CTNum ctNum = this.ctNumbering.addNewNum();
        ctNum.addNewAbstractNumId();
        ctNum.getAbstractNumId().setVal(abstractNumID);
        ctNum.setNumId(BigInteger.valueOf(this.nums.size() + 1));
        XWPFNum num = new XWPFNum(ctNum, this);
        this.nums.add(num);
        return ctNum.getNumId();
    }

    public void addNum(BigInteger abstractNumID, BigInteger numID) {
        CTNum ctNum = this.ctNumbering.addNewNum();
        ctNum.addNewAbstractNumId();
        ctNum.getAbstractNumId().setVal(abstractNumID);
        ctNum.setNumId(numID);
        XWPFNum num = new XWPFNum(ctNum, this);
        this.nums.add(num);
    }

    public XWPFNum getNum(BigInteger numID) {
        for (XWPFNum num : this.nums) {
            if (num.getCTNum().getNumId().equals(numID)) {
                return num;
            }
        }
        return null;
    }

    public XWPFAbstractNum getAbstractNum(BigInteger abstractNumID) {
        for (XWPFAbstractNum abstractNum : this.abstractNums) {
            if (abstractNum.getAbstractNum().getAbstractNumId().equals(abstractNumID)) {
                return abstractNum;
            }
        }
        return null;
    }

    public BigInteger getIdOfAbstractNum(XWPFAbstractNum abstractNum) {
        CTAbstractNum copy = (CTAbstractNum) abstractNum.getCTAbstractNum().copy();
        XWPFAbstractNum newAbstractNum = new XWPFAbstractNum(copy, this);
        for (int i = 0; i < this.abstractNums.size(); i++) {
            newAbstractNum.getCTAbstractNum().setAbstractNumId(BigInteger.valueOf(i));
            newAbstractNum.setNumbering(this);
            if (newAbstractNum.getCTAbstractNum().valueEquals(this.abstractNums.get(i).getCTAbstractNum())) {
                return newAbstractNum.getCTAbstractNum().getAbstractNumId();
            }
        }
        return null;
    }

    public BigInteger addAbstractNum(XWPFAbstractNum abstractNum) {
        int pos = this.abstractNums.size();
        if (abstractNum.getAbstractNum() != null) {
            CTAbstractNum ctAbstractNum = this.ctNumbering.addNewAbstractNum();
            ctAbstractNum.set(abstractNum.getAbstractNum());
            abstractNum.setCtAbstractNum(ctAbstractNum);
        } else {
            abstractNum.setCtAbstractNum(this.ctNumbering.addNewAbstractNum());
            BigInteger id = findNextAbstractNumberingId();
            abstractNum.getAbstractNum().setAbstractNumId(id);
            this.ctNumbering.setAbstractNumArray(pos, abstractNum.getAbstractNum());
            abstractNum.setCtAbstractNum(this.ctNumbering.getAbstractNumArray(pos));
        }
        this.abstractNums.add(abstractNum);
        return abstractNum.getCTAbstractNum().getAbstractNumId();
    }

    private BigInteger findNextAbstractNumberingId() {
        long maxId = 0;
        for (XWPFAbstractNum num : this.abstractNums) {
            maxId = Math.max(maxId, num.getAbstractNum().getAbstractNumId().longValue());
        }
        return BigInteger.valueOf(1 + maxId);
    }

    public boolean removeAbstractNum(BigInteger abstractNumID) {
        Iterator<XWPFAbstractNum> it = this.abstractNums.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            XWPFAbstractNum abstractNum = it.next();
            BigInteger foundNumId = abstractNum.getAbstractNum().getAbstractNumId();
            if (abstractNumID.equals(foundNumId)) {
                this.abstractNums.remove(abstractNum);
                break;
            }
        }
        for (int i = 0; i < this.ctNumbering.sizeOfAbstractNumArray(); i++) {
            CTAbstractNum ctAbstractNum = this.ctNumbering.getAbstractNumArray(i);
            BigInteger foundNumId2 = ctAbstractNum.getAbstractNumId();
            if (abstractNumID.equals(foundNumId2)) {
                this.ctNumbering.removeAbstractNum(i);
                return true;
            }
        }
        return false;
    }

    public BigInteger getAbstractNumID(BigInteger numID) {
        XWPFNum num = getNum(numID);
        if (num == null || num.getCTNum() == null || num.getCTNum().getAbstractNumId() == null) {
            return null;
        }
        return num.getCTNum().getAbstractNumId().getVal();
    }

    public List<XWPFAbstractNum> getAbstractNums() {
        return Collections.unmodifiableList(this.abstractNums);
    }

    public List<XWPFNum> getNums() {
        return Collections.unmodifiableList(this.nums);
    }
}
