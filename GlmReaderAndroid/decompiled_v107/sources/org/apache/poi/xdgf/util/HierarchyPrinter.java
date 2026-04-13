package org.apache.poi.xdgf.util;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;

/* loaded from: classes10.dex */
public class HierarchyPrinter {
    public static void printHierarchy(XDGFPage page, File outDir) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File pageFile = new File(outDir, "page" + page.getPageNumber() + ProcessIdUtil.DEFAULT_PROCESSID + Util.sanitizeFilename(page.getName()) + ".txt");
        OutputStream os = Files.newOutputStream(pageFile.toPath(), new OpenOption[0]);
        try {
            PrintStream pos = new PrintStream(os, false, StandardCharsets.UTF_8.name());
            try {
                printHierarchy(page, pos);
                pos.close();
                if (os != null) {
                    os.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (os != null) {
                    try {
                        os.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void printHierarchy(XDGFPage page, final PrintStream os) {
        page.getContent().visitShapes(new ShapeVisitor() { // from class: org.apache.poi.xdgf.util.HierarchyPrinter.1
            @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitor
            public void visit(XDGFShape shape, AffineTransform globalTransform, int level) {
                for (int i = 0; i < level; i++) {
                    os.append((CharSequence) "  ");
                }
                os.println(shape + " [" + shape.getShapeType() + ", " + shape.getSymbolName() + "] " + shape.getMasterShape() + StringUtils.SPACE + shape.getTextAsString().trim());
            }
        });
    }

    public static void printHierarchy(XmlVisioDocument document, String outDirname) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File outDir = new File(outDirname);
        for (XDGFPage page : document.getPages()) {
            printHierarchy(page, outDir);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: in.vsdx outdir");
            System.exit(1);
        }
        String inFilename = args[0];
        String outDir = args[1];
        InputStream is = Files.newInputStream(Paths.get(inFilename, new String[0]), new OpenOption[0]);
        try {
            XmlVisioDocument doc = new XmlVisioDocument(is);
            printHierarchy(doc, outDir);
            if (is != null) {
                is.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
