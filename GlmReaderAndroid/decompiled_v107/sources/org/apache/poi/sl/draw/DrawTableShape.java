package org.apache.poi.sl.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TableShape;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class DrawTableShape extends DrawShape {

    @Internal
    public static final int borderSize = 2;

    public DrawTableShape(TableShape<?, ?> shape) {
        super(shape);
    }

    protected Drawable getGroupShape(Graphics2D graphics) {
        if (this.shape instanceof GroupShape) {
            DrawFactory df = DrawFactory.getInstance(graphics);
            return df.getDrawable((GroupShape<?, ?>) this.shape);
        }
        return null;
    }

    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    public void applyTransform(Graphics2D graphics) {
        Drawable d = getGroupShape(graphics);
        if (d != null) {
            d.applyTransform(graphics);
        } else {
            super.applyTransform(graphics);
        }
    }

    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    public void draw(Graphics2D graphics) {
        Drawable d;
        Shape shape;
        Drawable d2 = getGroupShape(graphics);
        if (d2 != null) {
            d2.draw(graphics);
            return;
        }
        TableShape<?, ?> ts = getShape();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint(ts);
        int rows = ts.getNumberOfRows();
        int cols = ts.getNumberOfColumns();
        for (int row = 0; row < rows; row++) {
            int col = 0;
            while (col < cols) {
                TableCell<?, ?> tc = ts.getCell(row, col);
                if (tc != null && !tc.isMerged()) {
                    Paint fillPaint = drawPaint.getPaint(graphics, tc.getFillStyle().getPaint());
                    graphics.setPaint(fillPaint);
                    Rectangle2D cellAnc = tc.getAnchor();
                    DrawPaint.fillPaintWorkaround(graphics, cellAnc);
                    TableCell.BorderEdge[] values = TableCell.BorderEdge.values();
                    int length = values.length;
                    int i = 0;
                    while (i < length) {
                        TableCell.BorderEdge edge = values[i];
                        StrokeStyle stroke = tc.getBorderStyle(edge);
                        if (stroke == null) {
                            d = d2;
                        } else {
                            d = d2;
                            graphics.setStroke(getStroke(stroke));
                            Paint linePaint = drawPaint.getPaint(graphics, stroke.getPaint());
                            graphics.setPaint(linePaint);
                            double x = cellAnc.getX();
                            double y = cellAnc.getY();
                            double w = cellAnc.getWidth();
                            double h = cellAnc.getHeight();
                            switch (edge) {
                                case left:
                                    shape = new Line2D.Double(x, y, x, y + h + 2.0d);
                                    break;
                                case right:
                                    shape = new Line2D.Double(x + w, y, x + w, y + h + 2.0d);
                                    break;
                                case top:
                                    shape = new Line2D.Double(x - 2.0d, y, x + w + 2.0d, y);
                                    break;
                                default:
                                    shape = new Line2D.Double(x - 2.0d, y + h, x + w + 2.0d, y + h);
                                    break;
                            }
                            graphics.draw(shape);
                        }
                        i++;
                        d2 = d;
                    }
                }
                col++;
                d2 = d2;
            }
        }
        drawContent(graphics);
    }

    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    public void drawContent(Graphics2D graphics) {
        Drawable d = getGroupShape(graphics);
        if (d != null) {
            d.drawContent(graphics);
            return;
        }
        TableShape<?, ?> ts = getShape();
        DrawFactory df = DrawFactory.getInstance(graphics);
        int rows = ts.getNumberOfRows();
        int cols = ts.getNumberOfColumns();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                TableCell<?, ?> tc = ts.getCell(row, col);
                if (tc != null) {
                    DrawTextShape dts = df.getDrawable((TextShape<?, ?>) tc);
                    dts.drawContent(graphics);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.sl.draw.DrawShape
    public TableShape<?, ?> getShape() {
        return (TableShape) this.shape;
    }

    public void setAllBorders(Object... args) {
        TableShape<?, ?> table = getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = {TableCell.BorderEdge.top, TableCell.BorderEdge.left, null, null};
        int row = 0;
        while (row < rows) {
            int col = 0;
            while (col < cols) {
                edges[2] = col == cols + (-1) ? TableCell.BorderEdge.right : null;
                edges[3] = row == rows + (-1) ? TableCell.BorderEdge.bottom : null;
                setEdges(table.getCell(row, col), edges, args);
                col++;
            }
            row++;
        }
    }

    public void setOutsideBorders(Object... args) {
        if (args.length == 0) {
            return;
        }
        TableShape<?, ?> table = getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = new TableCell.BorderEdge[4];
        int row = 0;
        while (row < rows) {
            int col = 0;
            while (col < cols) {
                TableCell.BorderEdge borderEdge = null;
                edges[0] = col == 0 ? TableCell.BorderEdge.left : null;
                edges[1] = col == cols + (-1) ? TableCell.BorderEdge.right : null;
                edges[2] = row == 0 ? TableCell.BorderEdge.top : null;
                if (row == rows - 1) {
                    borderEdge = TableCell.BorderEdge.bottom;
                }
                edges[3] = borderEdge;
                setEdges(table.getCell(row, col), edges, args);
                col++;
            }
            row++;
        }
    }

    public void setInsideBorders(Object... args) {
        if (args.length == 0) {
            return;
        }
        TableShape<?, ?> table = getShape();
        int rows = table.getNumberOfRows();
        int cols = table.getNumberOfColumns();
        TableCell.BorderEdge[] edges = new TableCell.BorderEdge[2];
        for (int row = 0; row < rows; row++) {
            int col = 0;
            while (col < cols) {
                TableCell.BorderEdge borderEdge = null;
                edges[0] = (col <= 0 || col >= cols + (-1)) ? null : TableCell.BorderEdge.right;
                if (row > 0 && row < rows - 1) {
                    borderEdge = TableCell.BorderEdge.bottom;
                }
                edges[1] = borderEdge;
                setEdges(table.getCell(row, col), edges, args);
                col++;
            }
        }
    }

    private static void setEdges(TableCell<?, ?> cell, TableCell.BorderEdge[] edges, Object... args) {
        if (cell == null) {
            return;
        }
        for (TableCell.BorderEdge be : edges) {
            if (be != null) {
                if (args.length == 0) {
                    cell.removeBorder(be);
                } else {
                    for (Object o : args) {
                        if (o instanceof Double) {
                            cell.setBorderWidth(be, ((Double) o).doubleValue());
                        } else if (o instanceof Color) {
                            cell.setBorderColor(be, (Color) o);
                        } else if (o instanceof StrokeStyle.LineDash) {
                            cell.setBorderDash(be, (StrokeStyle.LineDash) o);
                        } else if (o instanceof StrokeStyle.LineCompound) {
                            cell.setBorderCompound(be, (StrokeStyle.LineCompound) o);
                        }
                    }
                }
            }
        }
    }
}
