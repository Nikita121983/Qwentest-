package org.apache.poi.ooxml.dev;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;

/* loaded from: classes10.dex */
public class OOXMLLister implements Closeable {
    private final OPCPackage container;
    private final PrintStream disp;

    public OOXMLLister(OPCPackage container) {
        this(container, System.out);
    }

    public OOXMLLister(OPCPackage container, PrintStream disp) {
        this.container = container;
        this.disp = disp;
    }

    public static long getSize(PackagePart part) throws IOException {
        InputStream in = part.getInputStream();
        try {
            byte[] b = new byte[8192];
            long size = 0;
            int read = 0;
            while (read > -1) {
                read = in.read(b);
                if (read > 0) {
                    size += read;
                }
            }
            if (in != null) {
                in.close();
            }
            return size;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void displayParts() throws InvalidFormatException, IOException {
        ArrayList<PackagePart> parts = this.container.getParts();
        Iterator<PackagePart> it = parts.iterator();
        while (it.hasNext()) {
            PackagePart part = it.next();
            this.disp.println(part.getPartName());
            this.disp.println("\t" + part.getContentType());
            if (!part.getPartName().toString().equals("/docProps/core.xml")) {
                this.disp.println("\t" + getSize(part) + " bytes");
            }
            if (!part.isRelationshipPart()) {
                this.disp.println("\t" + part.getRelationships().size() + " relations");
                Iterator<PackageRelationship> it2 = part.getRelationships().iterator();
                while (it2.hasNext()) {
                    PackageRelationship rel = it2.next();
                    displayRelation(rel, "\t  ");
                }
            }
        }
    }

    public void displayRelations() {
        PackageRelationshipCollection rels = this.container.getRelationships();
        Iterator<PackageRelationship> it = rels.iterator();
        while (it.hasNext()) {
            PackageRelationship rel = it.next();
            displayRelation(rel, "");
        }
    }

    private void displayRelation(PackageRelationship rel, String indent) {
        this.disp.println(indent + "Relationship:");
        this.disp.println(indent + "\tFrom: " + rel.getSourceURI());
        this.disp.println(indent + "\tTo:   " + rel.getTargetURI());
        this.disp.println(indent + "\tID:   " + rel.getId());
        this.disp.println(indent + "\tMode: " + rel.getTargetMode());
        this.disp.println(indent + "\tType: " + rel.getRelationshipType());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.container.close();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        if (args.length == 0) {
            System.err.println("Use:");
            System.err.println("\tjava OOXMLLister <filename>");
            System.exit(1);
        }
        File f = new File(args[0]);
        if (!f.exists()) {
            System.err.println("Error, file not found!");
            System.err.println("\t" + f);
            System.exit(2);
        }
        OOXMLLister lister = new OOXMLLister(OPCPackage.open(f.toString(), PackageAccess.READ));
        try {
            lister.disp.println(f + StringUtils.LF);
            lister.displayParts();
            lister.disp.println();
            lister.displayRelations();
            lister.close();
        } finally {
        }
    }
}
