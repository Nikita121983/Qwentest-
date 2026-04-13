package org.apache.poi.xssf.usermodel;

import androidx.core.net.MailTo;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;

/* loaded from: classes10.dex */
public class XSSFHyperlink implements Hyperlink, Duplicatable {
    private final CTHyperlink _ctHyperlink;
    private final PackageRelationship _externalRel;
    private String _location;
    private final HyperlinkType _type;

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFHyperlink(HyperlinkType type) {
        this._type = type;
        this._ctHyperlink = CTHyperlink.Factory.newInstance();
        this._externalRel = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XSSFHyperlink(CTHyperlink ctHyperlink, PackageRelationship hyperlinkRel) {
        this._ctHyperlink = ctHyperlink;
        this._externalRel = hyperlinkRel;
        if (this._externalRel == null) {
            if (ctHyperlink.getLocation() != null) {
                this._type = HyperlinkType.DOCUMENT;
                this._location = ctHyperlink.getLocation();
                return;
            } else {
                if (ctHyperlink.getId() != null) {
                    throw new IllegalStateException("The hyperlink for cell " + ctHyperlink.getRef() + " references relation " + ctHyperlink.getId() + ", but that didn't exist!");
                }
                this._type = HyperlinkType.DOCUMENT;
                return;
            }
        }
        URI target = this._externalRel.getTargetURI();
        this._location = target.toString();
        if (ctHyperlink.getLocation() != null) {
            this._location += "#" + ctHyperlink.getLocation();
        }
        if (this._location.startsWith("http://") || this._location.startsWith("https://") || this._location.startsWith("ftp://")) {
            this._type = HyperlinkType.URL;
        } else if (this._location.startsWith(MailTo.MAILTO_SCHEME)) {
            this._type = HyperlinkType.EMAIL;
        } else {
            this._type = HyperlinkType.FILE;
        }
    }

    @Internal
    public XSSFHyperlink(Hyperlink other) {
        if (other instanceof XSSFHyperlink) {
            XSSFHyperlink xlink = (XSSFHyperlink) other;
            this._type = xlink.getType();
            this._location = xlink._location;
            this._externalRel = xlink._externalRel;
            this._ctHyperlink = (CTHyperlink) xlink._ctHyperlink.copy();
            return;
        }
        this._type = other.getType();
        this._location = other.getAddress();
        this._externalRel = null;
        this._ctHyperlink = CTHyperlink.Factory.newInstance();
        this._ctHyperlink.setDisplay(other.getLabel());
        setFirstColumn(other.getFirstColumn());
        setLastColumn(other.getLastColumn());
        setFirstRow(other.getFirstRow());
        setLastRow(other.getLastRow());
    }

    @Internal
    public CTHyperlink getCTHyperlink() {
        return this._ctHyperlink;
    }

    public boolean needsRelationToo() {
        return this._type != HyperlinkType.DOCUMENT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void generateRelationIfNeeded(PackagePart sheetPart) {
        if (this._externalRel == null && needsRelationToo()) {
            PackageRelationship rel = sheetPart.addExternalRelationship(this._location, XSSFRelation.SHEET_HYPERLINKS.getRelation());
            this._ctHyperlink.setId(rel.getId());
        }
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public HyperlinkType getType() {
        return this._type;
    }

    public String getCellRef() {
        return this._ctHyperlink.getRef();
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getAddress() {
        return this._location;
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public String getLabel() {
        return this._ctHyperlink.getDisplay();
    }

    public String getLocation() {
        return this._ctHyperlink.getLocation();
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setLabel(String label) {
        this._ctHyperlink.setDisplay(label);
    }

    public void setLocation(String location) {
        this._ctHyperlink.setLocation(location);
    }

    @Override // org.apache.poi.common.usermodel.Hyperlink
    public void setAddress(String address) {
        validate(address);
        this._location = address;
        if (this._type == HyperlinkType.DOCUMENT) {
            setLocation(address);
        }
    }

    private void validate(String address) {
        switch (this._type) {
            case EMAIL:
            case FILE:
            case URL:
                try {
                    new URI(address);
                    return;
                } catch (URISyntaxException e) {
                    throw new IllegalArgumentException("Address of hyperlink must be a valid URI", e);
                }
            case DOCUMENT:
                return;
            default:
                throw new IllegalStateException("Invalid Hyperlink type: " + this._type);
        }
    }

    @Internal
    public void setCellReference(String ref) {
        this._ctHyperlink.setRef(ref);
    }

    @Internal
    public void setCellReference(CellReference ref) {
        setCellReference(ref.formatAsString());
    }

    private CellReference buildFirstCellReference() {
        return buildCellReference(false);
    }

    private CellReference buildLastCellReference() {
        return buildCellReference(true);
    }

    private CellReference buildCellReference(boolean lastCell) {
        String ref = this._ctHyperlink.getRef();
        if (ref == null) {
            ref = "A1";
        }
        if (ref.contains(":")) {
            AreaReference area = new AreaReference(ref, SpreadsheetVersion.EXCEL2007);
            return lastCell ? area.getLastCell() : area.getFirstCell();
        }
        return new CellReference(ref);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getFirstColumn() {
        return buildFirstCellReference().getCol();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getLastColumn() {
        return buildLastCellReference().getCol();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getFirstRow() {
        return buildFirstCellReference().getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public int getLastRow() {
        return buildLastCellReference().getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setFirstColumn(int col) {
        int lastColumn = getLastColumn();
        if (col > lastColumn) {
            lastColumn = col;
        }
        String firstCellRef = CellReference.convertNumToColString(col) + (getFirstRow() + 1);
        String lastCellRef = CellReference.convertNumToColString(lastColumn) + (getLastRow() + 1);
        setCellRange(firstCellRef + ":" + lastCellRef);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setLastColumn(int col) {
        int firstColumn = getFirstColumn();
        if (col < firstColumn) {
            firstColumn = col;
        }
        String firstCellRef = CellReference.convertNumToColString(firstColumn) + (getFirstRow() + 1);
        String lastCellRef = CellReference.convertNumToColString(col) + (getLastRow() + 1);
        setCellRange(firstCellRef + ":" + lastCellRef);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setFirstRow(int row) {
        int lastRow = getLastRow();
        if (row > lastRow) {
            lastRow = row;
        }
        String firstCellRef = CellReference.convertNumToColString(getFirstColumn()) + (row + 1);
        String lastCellRef = CellReference.convertNumToColString(getLastColumn()) + (lastRow + 1);
        setCellRange(firstCellRef + ":" + lastCellRef);
    }

    @Override // org.apache.poi.ss.usermodel.Hyperlink
    public void setLastRow(int row) {
        int firstRow = getFirstRow();
        if (row < firstRow) {
            firstRow = row;
        }
        String firstCellRef = CellReference.convertNumToColString(getFirstColumn()) + (firstRow + 1);
        String lastCellRef = CellReference.convertNumToColString(getLastColumn()) + (row + 1);
        setCellRange(firstCellRef + ":" + lastCellRef);
    }

    private void setCellRange(String range) {
        AreaReference ref = new AreaReference(range, SpreadsheetVersion.EXCEL2007);
        if (ref.isSingleCell()) {
            setCellReference(ref.getFirstCell());
        } else {
            setCellReference(ref.formatAsString());
        }
    }

    public String getTooltip() {
        return this._ctHyperlink.getTooltip();
    }

    public void setTooltip(String text) {
        this._ctHyperlink.setTooltip(text);
    }

    @Override // org.apache.poi.common.Duplicatable
    public Duplicatable copy() {
        return new XSSFHyperlink(this);
    }
}
