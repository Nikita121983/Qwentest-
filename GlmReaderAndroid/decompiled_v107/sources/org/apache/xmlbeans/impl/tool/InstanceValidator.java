package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

/* loaded from: classes11.dex */
public class InstanceValidator {
    public static void printUsage() {
        System.out.println("Validates the specified instance against the specified schema.");
        System.out.println("Contrast with the svalidate tool, which validates using a stream.");
        System.out.println("Usage: validate [-dl] [-nopvr] [-noupa] [-license] schema.xsd instance.xml");
        System.out.println("Options:");
        System.out.println("    -dl - permit network downloads for imports and includes (default is off)");
        System.out.println("    -noupa - do not enforce the unique particle attribution rule");
        System.out.println("    -nopvr - do not enforce the particle valid (restriction) rule");
        System.out.println("    -strict - performs strict(er) validation");
        System.out.println("    -partial - allow partial schema type system");
        System.out.println("    -license - prints license information");
    }

    public static void main(String[] args) {
        System.exit(extraMain(args));
    }

    public static int extraMain(String[] args) {
        SchemaTypeLoader sLoader;
        Collection<XmlError> compErrors;
        XmlOptions schemaOptions;
        boolean dl;
        boolean nopvr;
        XmlOptions errorListener;
        boolean partial;
        String[] badopts;
        Set<String> flags = new HashSet<>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("dl");
        flags.add("noupa");
        flags.add("nopvr");
        flags.add("strict");
        flags.add("partial");
        CommandLine cl = new CommandLine(args, flags, Collections.EMPTY_SET);
        int i = 0;
        if (cl.getOpt("h") == null && cl.getOpt("help") == null && cl.getOpt("usage") == null) {
            if (args.length >= 1) {
                String[] badopts2 = cl.getBadOpts();
                if (badopts2.length > 0) {
                    for (String str : badopts2) {
                        System.out.println("Unrecognized option: " + str);
                    }
                    printUsage();
                    return 0;
                }
                if (cl.getOpt("license") != null) {
                    CommandLine.printLicense();
                    return 0;
                }
                if (cl.getOpt("version") != null) {
                    CommandLine.printVersion();
                    return 0;
                }
                if (cl.args().length == 0) {
                    return 0;
                }
                boolean dl2 = cl.getOpt("dl") != null;
                boolean nopvr2 = cl.getOpt("nopvr") != null;
                boolean noupa = cl.getOpt("noupa") != null;
                boolean strict = cl.getOpt("strict") != null;
                boolean partial2 = cl.getOpt("partial") != null;
                File[] schemaFiles = cl.filesEndingWith(".xsd");
                File[] instanceFiles = cl.filesEndingWith(".xml");
                File[] jarFiles = cl.filesEndingWith(".jar");
                List<XmlObject> sdocs = new ArrayList<>();
                int length = schemaFiles.length;
                while (i < length) {
                    Set<String> flags2 = flags;
                    File schemaFile = schemaFiles[i];
                    try {
                        partial = partial2;
                        try {
                            sdocs.add(XmlObject.Factory.parse(schemaFile, new XmlOptions().setLoadLineNumbers().setLoadMessageDigest()));
                            badopts = badopts2;
                        } catch (Exception e) {
                            e = e;
                            badopts = badopts2;
                            System.err.println(schemaFile + " not loadable: " + e);
                            i++;
                            flags = flags2;
                            badopts2 = badopts;
                            partial2 = partial;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        partial = partial2;
                    }
                    i++;
                    flags = flags2;
                    badopts2 = badopts;
                    partial2 = partial;
                }
                boolean partial3 = partial2;
                XmlObject[] schemas = (XmlObject[]) sdocs.toArray(new XmlObject[0]);
                Collection<XmlError> compErrors2 = new ArrayList<>();
                XmlOptions schemaOptions2 = new XmlOptions();
                schemaOptions2.setErrorListener(compErrors2);
                if (dl2) {
                    schemaOptions2.setCompileDownloadUrls();
                }
                if (nopvr2) {
                    schemaOptions2.setCompileNoPvrRule();
                }
                if (noupa) {
                    schemaOptions2.setCompileNoUpaRule();
                }
                if (partial3) {
                    schemaOptions2.setCompilePartialTypesystem();
                }
                if (jarFiles != null && jarFiles.length > 0) {
                    SchemaTypeLoader sLoader2 = XmlBeans.typeLoaderForResource(XmlBeans.resourceLoaderForPath(jarFiles));
                    sLoader = sLoader2;
                } else {
                    sLoader = null;
                }
                int returnCode = 0;
                if (schemas != null) {
                    try {
                        if (schemas.length > 0) {
                            sLoader = XmlBeans.compileXsd(schemas, sLoader, schemaOptions2);
                        }
                    } catch (Exception e3) {
                        if (compErrors2.isEmpty() || !(e3 instanceof XmlException)) {
                            e3.printStackTrace(System.err);
                        }
                        System.out.println("Schema invalid:" + (partial3 ? " couldn't recover from errors" : ""));
                        for (XmlError compError : compErrors2) {
                            System.out.println(compError);
                        }
                        return 10;
                    }
                }
                if (partial3 && !compErrors2.isEmpty()) {
                    returnCode = 11;
                    System.out.println("Schema invalid: partial schema type system recovered");
                    for (Iterator<XmlError> it = compErrors2.iterator(); it.hasNext(); it = it) {
                        XmlError compError2 = it.next();
                        System.out.println(compError2);
                    }
                }
                if (sLoader == null) {
                    sLoader = XmlBeans.getContextTypeLoader();
                }
                int length2 = instanceFiles.length;
                int returnCode2 = returnCode;
                int returnCode3 = 0;
                while (returnCode3 < length2) {
                    int i2 = length2;
                    File instanceFile = instanceFiles[returnCode3];
                    try {
                        XmlOptions xo = new XmlOptions();
                        xo.setLoadLineNumbersEndElement();
                        compErrors = compErrors2;
                        try {
                            XmlObject xobj = sLoader.parse(instanceFile, (SchemaType) null, xo);
                            Collection<XmlError> errors = new ArrayList<>();
                            schemaOptions = schemaOptions2;
                            dl = dl2;
                            if (xobj.schemaType() == XmlObject.type) {
                                nopvr = nopvr2;
                                System.out.println(instanceFile + " NOT valid.  ");
                                System.out.println("  Document type not found.");
                            } else {
                                nopvr = nopvr2;
                                if (strict) {
                                    errorListener = new XmlOptions().setErrorListener(errors).setValidateStrict();
                                } else {
                                    errorListener = new XmlOptions().setErrorListener(errors);
                                }
                                if (xobj.validate(errorListener)) {
                                    System.out.println(instanceFile + " valid.");
                                } else {
                                    System.out.println(instanceFile + " NOT valid.");
                                    for (XmlError error : errors) {
                                        System.out.println(error);
                                    }
                                    returnCode2 = 1;
                                }
                            }
                        } catch (Exception e4) {
                            e = e4;
                            schemaOptions = schemaOptions2;
                            dl = dl2;
                            nopvr = nopvr2;
                            System.err.println(instanceFile + " not loadable: " + e);
                            e.printStackTrace(System.err);
                            returnCode3++;
                            length2 = i2;
                            compErrors2 = compErrors;
                            schemaOptions2 = schemaOptions;
                            dl2 = dl;
                            nopvr2 = nopvr;
                        }
                    } catch (Exception e5) {
                        e = e5;
                        compErrors = compErrors2;
                        schemaOptions = schemaOptions2;
                        dl = dl2;
                        nopvr = nopvr2;
                    }
                    returnCode3++;
                    length2 = i2;
                    compErrors2 = compErrors;
                    schemaOptions2 = schemaOptions;
                    dl2 = dl;
                    nopvr2 = nopvr;
                }
                return returnCode2;
            }
        }
        printUsage();
        return 0;
    }
}
