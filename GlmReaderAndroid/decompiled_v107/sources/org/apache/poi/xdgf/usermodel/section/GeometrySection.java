package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.awt.geom.Path2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.geom.SplineCollector;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.geometry.Ellipse;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;
import org.apache.poi.xdgf.usermodel.section.geometry.InfiniteLine;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

/* loaded from: classes10.dex */
public class GeometrySection extends XDGFSection {
    GeometrySection _master;
    SortedMap<Long, GeometryRow> _rows;

    public GeometrySection(SectionType section, XDGFSheet containingSheet) {
        super(section, containingSheet);
        this._rows = new TreeMap();
        for (RowType row : section.getRowArray()) {
            if (this._rows.containsKey(Long.valueOf(row.getIX()))) {
                throw new POIXMLException("Index element '" + row.getIX() + "' already exists");
            }
            this._rows.put(Long.valueOf(row.getIX()), GeometryRowTypes.load(row));
        }
    }

    @Override // org.apache.poi.xdgf.usermodel.section.XDGFSection
    public void setupMaster(XDGFSection master) {
        this._master = (GeometrySection) master;
        for (Map.Entry<Long, GeometryRow> entry : this._rows.entrySet()) {
            GeometryRow masterRow = this._master._rows.get(entry.getKey());
            if (masterRow != null) {
                try {
                    entry.getValue().setupMaster(masterRow);
                } catch (ClassCastException e) {
                }
            }
        }
    }

    public Boolean getNoShow() {
        Boolean noShow = XDGFCell.maybeGetBoolean(this._cells, "NoShow");
        if (noShow == null) {
            if (this._master != null) {
                return this._master.getNoShow();
            }
            return false;
        }
        return noShow;
    }

    @Internal
    public static <T, S extends SortedMap<Long, T>> Collection<T> combineGeometries(S map1, S map2) {
        SortedMap<Long, T> map;
        if (map2 == null) {
            map = map1;
        } else {
            map = new TreeMap<>((SortedMap<Long, ? extends T>) map2);
            map.putAll(map1);
        }
        return map.values();
    }

    public Iterable<GeometryRow> getCombinedRows() {
        return combineGeometries(this._rows, this._master == null ? null : this._master._rows);
    }

    public Path2D.Double getPath(XDGFShape parent) {
        GeometryRow row;
        Iterator<GeometryRow> rows = getCombinedRows().iterator();
        GeometryRow first = rows.hasNext() ? rows.next() : null;
        if (first instanceof Ellipse) {
            return ((Ellipse) first).getPath();
        }
        if (first instanceof InfiniteLine) {
            return ((InfiniteLine) first).getPath();
        }
        if (first instanceof SplineStart) {
            throw new POIXMLException("SplineStart must be preceded by another type");
        }
        Path2D.Double path = new Path2D.Double();
        SplineCollector renderer = null;
        while (true) {
            if (first != null) {
                row = first;
                first = null;
            } else if (rows.hasNext()) {
                row = rows.next();
            } else {
                if (renderer != null) {
                    renderer.addToPath(path, parent);
                }
                return path;
            }
            if (row instanceof SplineStart) {
                if (renderer != null) {
                    throw new POIXMLException("SplineStart found multiple times!");
                }
                renderer = new SplineCollector((SplineStart) row);
            } else if (row instanceof SplineKnot) {
                if (renderer == null) {
                    throw new POIXMLException("SplineKnot found without SplineStart!");
                }
                renderer.addKnot((SplineKnot) row);
            } else {
                if (renderer != null) {
                    renderer.addToPath(path, parent);
                    renderer = null;
                }
                row.addToPath(path, parent);
            }
        }
    }
}
