package org.apache.xmlbeans.impl.inst2xsd;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.util.TypeSystemHolder;
import org.apache.xmlbeans.impl.tool.CommandLine;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

/* loaded from: classes11.dex */
public class Inst2Xsd {
    public static void main(String[] args) {
        int i;
        Inst2XsdOptions inst2XsdOptions;
        String outPrefix;
        String str;
        CommandLine cl;
        Set<String> flags;
        String[] badopts;
        if (args != null) {
            if (args.length != 0) {
                Set<String> flags2 = new HashSet<>();
                flags2.add("h");
                flags2.add("help");
                flags2.add("usage");
                flags2.add("license");
                flags2.add("version");
                flags2.add("verbose");
                flags2.add("validate");
                Set<String> opts = new HashSet<>();
                opts.add("design");
                opts.add("simple-content-types");
                opts.add("enumerations");
                opts.add("outDir");
                opts.add("outPrefix");
                CommandLine cl2 = new CommandLine(args, flags2, opts);
                Inst2XsdOptions inst2XsdOptions2 = new Inst2XsdOptions();
                if (cl2.getOpt("license") != null) {
                    CommandLine.printLicense();
                    System.exit(0);
                    return;
                }
                if (cl2.getOpt("version") != null) {
                    CommandLine.printVersion();
                    System.exit(0);
                    return;
                }
                if (cl2.getOpt("h") == null && cl2.getOpt("help") == null) {
                    if (cl2.getOpt("usage") == null) {
                        String[] badopts2 = cl2.getBadOpts();
                        if (badopts2.length > 0) {
                            for (String str2 : badopts2) {
                                System.out.println("Unrecognized option: " + str2);
                            }
                            printHelp();
                            System.exit(0);
                            return;
                        }
                        String design = cl2.getOpt("design");
                        if (design == null) {
                            inst2XsdOptions = inst2XsdOptions2;
                        } else if (design.equals("vb")) {
                            inst2XsdOptions = inst2XsdOptions2;
                            inst2XsdOptions.setDesign(3);
                        } else {
                            inst2XsdOptions = inst2XsdOptions2;
                            if (design.equals("rd")) {
                                inst2XsdOptions.setDesign(1);
                            } else if (design.equals("ss")) {
                                inst2XsdOptions.setDesign(2);
                            } else {
                                printHelp();
                                System.exit(0);
                                return;
                            }
                        }
                        String simpleContent = cl2.getOpt("simple-content-types");
                        if (simpleContent != null) {
                            if (simpleContent.equals("smart")) {
                                inst2XsdOptions.setSimpleContentTypes(1);
                            } else if (simpleContent.equals(TypedValues.Custom.S_STRING)) {
                                inst2XsdOptions.setSimpleContentTypes(2);
                            } else {
                                printHelp();
                                System.exit(0);
                                return;
                            }
                        }
                        String enumerations = cl2.getOpt("enumerations");
                        if (enumerations != null) {
                            if (enumerations.equals("never")) {
                                inst2XsdOptions.setUseEnumerations(1);
                            } else {
                                try {
                                    int intVal = Integer.parseInt(enumerations);
                                    inst2XsdOptions.setUseEnumerations(intVal);
                                } catch (NumberFormatException e) {
                                    printHelp();
                                    System.exit(0);
                                    return;
                                }
                            }
                        }
                        File outDir = new File(cl2.getOpt("outDir") == null ? "." : cl2.getOpt("outDir"));
                        String outPrefix2 = cl2.getOpt("outPrefix");
                        if (outPrefix2 != null) {
                            outPrefix = outPrefix2;
                        } else {
                            outPrefix = "schema";
                        }
                        inst2XsdOptions.setVerbose(cl2.getOpt("verbose") != null);
                        boolean validate = cl2.getOpt("validate") != null;
                        File[] xmlFiles = cl2.filesEndingWith(".xml");
                        XmlObject[] xmlInstances = new XmlObject[xmlFiles.length];
                        if (xmlInstances.length == 0) {
                            printHelp();
                            System.exit(0);
                            return;
                        }
                        int i2 = 0;
                        while (true) {
                            try {
                                int i3 = xmlFiles.length;
                                if (i2 >= i3) {
                                    break;
                                }
                                try {
                                    xmlInstances[i2] = XmlObject.Factory.parse(xmlFiles[i2]);
                                    i2++;
                                } catch (IOException e2) {
                                    e = e2;
                                    System.err.println("Could not read file: '" + xmlFiles[i2].getName() + "'. " + e.getMessage());
                                    return;
                                } catch (XmlException e3) {
                                    e = e3;
                                    str = "'. ";
                                    System.err.println("Invalid xml file: '" + xmlFiles[i2].getName() + str + e.getMessage());
                                    return;
                                }
                            } catch (IOException e4) {
                                e = e4;
                            } catch (XmlException e5) {
                                e = e5;
                                str = "'. ";
                            }
                        }
                        SchemaDocument[] schemaDocs = inst2xsd(xmlInstances, inst2XsdOptions);
                        int i4 = 0;
                        while (true) {
                            try {
                                int i5 = schemaDocs.length;
                                if (i4 >= i5) {
                                    break;
                                }
                                SchemaDocument schema = schemaDocs[i4];
                                if (!inst2XsdOptions.isVerbose()) {
                                    cl = cl2;
                                    flags = flags2;
                                    badopts = badopts2;
                                } else {
                                    cl = cl2;
                                    try {
                                        flags = flags2;
                                    } catch (IOException e6) {
                                        e = e6;
                                    }
                                    try {
                                        badopts = badopts2;
                                        try {
                                            System.out.println("----------------------\n\n" + schema);
                                        } catch (IOException e7) {
                                            e = e7;
                                            System.err.println("Could not write file: '" + outDir + File.pathSeparator + outPrefix + i4 + ".xsd'. " + e.getMessage());
                                            return;
                                        }
                                    } catch (IOException e8) {
                                        e = e8;
                                        System.err.println("Could not write file: '" + outDir + File.pathSeparator + outPrefix + i4 + ".xsd'. " + e.getMessage());
                                        return;
                                    }
                                }
                                schema.save(new File(outDir, outPrefix + i4 + ".xsd"), new XmlOptions().setSavePrettyPrint());
                                i4++;
                                cl2 = cl;
                                flags2 = flags;
                                badopts2 = badopts;
                            } catch (IOException e9) {
                                e = e9;
                            }
                        }
                        if (validate) {
                            validateInstances(schemaDocs, xmlInstances);
                            return;
                        }
                        return;
                    }
                }
                printHelp();
                System.exit(0);
                return;
            }
            i = 0;
        } else {
            i = 0;
        }
        printHelp();
        System.exit(i);
    }

    private static void printHelp() {
        System.out.println("Generates XMLSchema from instance xml documents.");
        System.out.println("Usage: inst2xsd [opts] [instance.xml]*");
        System.out.println("Options include:");
        System.out.println("    -design [rd|ss|vb] - XMLSchema design type");
        System.out.println("             rd  - Russian Doll Design - local elements and local types");
        System.out.println("             ss  - Salami Slice Design - global elements and local types");
        System.out.println("             vb  - Venetian Blind Design (default) - local elements and global complex types");
        System.out.println("    -simple-content-types [smart|string] - Simple content types detection (leaf text). Smart is the default");
        System.out.println("    -enumerations [never|NUMBER] - Use enumerations. Default value is 10.");
        System.out.println("    -outDir [dir] - Directory for output files. Default is '.'");
        System.out.println("    -outPrefix [file_name_prefix] - Prefix for output file names. Default is 'schema'");
        System.out.println("    -validate - Validates input instances agaist generated schemas.");
        System.out.println("    -verbose - print more informational messages");
        System.out.println("    -license - print license information");
        System.out.println("    -help - help imformation");
    }

    private Inst2Xsd() {
    }

    public static SchemaDocument[] inst2xsd(Reader[] instReaders, Inst2XsdOptions options) throws IOException, XmlException {
        XmlObject[] instances = new XmlObject[instReaders.length];
        for (int i = 0; i < instReaders.length; i++) {
            instances[i] = XmlObject.Factory.parse(instReaders[i]);
        }
        return inst2xsd(instances, options);
    }

    public static SchemaDocument[] inst2xsd(XmlObject[] instances, Inst2XsdOptions options) {
        XsdGenStrategy strategy;
        if (options == null) {
            options = new Inst2XsdOptions();
        }
        TypeSystemHolder typeSystemHolder = new TypeSystemHolder();
        switch (options.getDesign()) {
            case 1:
                strategy = new RussianDollStrategy();
                break;
            case 2:
                strategy = new SalamiSliceStrategy();
                break;
            case 3:
                strategy = new VenetianBlindStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown design.");
        }
        strategy.processDoc(instances, options, typeSystemHolder);
        if (options.isVerbose()) {
            System.out.println("typeSystemHolder.toString(): " + typeSystemHolder);
        }
        SchemaDocument[] sDocs = typeSystemHolder.getSchemaDocuments();
        return sDocs;
    }

    private static boolean validateInstances(SchemaDocument[] sDocs, XmlObject[] instances) {
        Collection<XmlError> compErrors = new ArrayList<>();
        XmlOptions schemaOptions = new XmlOptions();
        schemaOptions.setErrorListener(compErrors);
        try {
            SchemaTypeLoader sLoader = XmlBeans.loadXsd(sDocs, schemaOptions);
            System.out.println("\n-------------------");
            boolean result = true;
            for (int i = 0; i < instances.length; i++) {
                try {
                    XmlObject xobj = sLoader.parse(instances[i].newXMLStreamReader(), (SchemaType) null, new XmlOptions().setLoadLineNumbers());
                    Collection<XmlError> errors = new ArrayList<>();
                    if (xobj.schemaType() == XmlObject.type) {
                        System.out.println(instances[i].documentProperties().getSourceName() + " NOT valid.  ");
                        System.out.println("  Document type not found.");
                        result = false;
                    } else if (xobj.validate(new XmlOptions().setErrorListener(errors))) {
                        System.out.println("Instance[" + i + "] valid - " + instances[i].documentProperties().getSourceName());
                    } else {
                        System.out.println("Instance[" + i + "] NOT valid - " + instances[i].documentProperties().getSourceName());
                        for (XmlError xe : errors) {
                            System.out.println(xe.getLine() + ":" + xe.getColumn() + StringUtils.SPACE + xe.getMessage());
                        }
                        result = false;
                    }
                } catch (XmlException e) {
                    System.out.println("Error:\n" + instances[i].documentProperties().getSourceName() + " not loadable: " + e);
                    e.printStackTrace(System.out);
                    result = false;
                }
            }
            return result;
        } catch (Exception e2) {
            if (compErrors.isEmpty() || !(e2 instanceof XmlException)) {
                e2.printStackTrace(System.out);
            }
            System.out.println("\n-------------------\n\nInvalid schemas.");
            for (XmlError xe2 : compErrors) {
                System.out.println(xe2.getLine() + ":" + xe2.getColumn() + StringUtils.SPACE + xe2.getMessage());
            }
            return false;
        }
    }
}
