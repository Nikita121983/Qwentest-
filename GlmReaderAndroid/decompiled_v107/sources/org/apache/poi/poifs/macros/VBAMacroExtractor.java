package org.apache.poi.poifs.macros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Map;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class VBAMacroExtractor {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Use:");
            System.err.println("   VBAMacroExtractor <office.doc> [output]");
            System.err.println();
            System.err.println("If an output directory is given, macros are written there");
            System.err.println("Otherwise they are output to the screen");
            System.exit(1);
        }
        File input = new File(args[0]);
        File output = null;
        if (args.length > 1) {
            output = new File(args[1]);
        }
        VBAMacroExtractor extractor = new VBAMacroExtractor();
        extractor.extract(input, output);
    }

    public void extract(File input, File outputDir, String extension) throws IOException {
        if (!input.exists()) {
            throw new FileNotFoundException(input.toString());
        }
        System.err.print("Extracting VBA Macros from " + input + " to ");
        if (outputDir != null) {
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                throw new IOException("Output directory " + outputDir + " could not be created");
            }
            System.err.println(outputDir);
        } else {
            System.err.println("STDOUT");
        }
        VBAMacroReader reader = new VBAMacroReader(input);
        try {
            Map<String, String> macros = reader.readMacros();
            reader.close();
            for (Map.Entry<String, String> entry : macros.entrySet()) {
                String moduleName = entry.getKey();
                String moduleCode = entry.getValue();
                if (outputDir == null) {
                    System.out.println("---------------------------------------");
                    System.out.println(moduleName);
                    System.out.println();
                    System.out.println(moduleCode);
                } else {
                    File out = IOUtils.newFile(outputDir, moduleName + extension);
                    OutputStream fout = Files.newOutputStream(out.toPath(), new OpenOption[0]);
                    try {
                        OutputStreamWriter fwriter = new OutputStreamWriter(fout, StringUtil.UTF8);
                        try {
                            fwriter.write(moduleCode);
                            fwriter.close();
                            if (fout != null) {
                                fout.close();
                            }
                            System.out.println("Extracted " + out);
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                try {
                                    fwriter.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                                throw th2;
                            }
                        }
                    } catch (Throwable th4) {
                        try {
                            throw th4;
                        } catch (Throwable th5) {
                            if (fout != null) {
                                try {
                                    fout.close();
                                } catch (Throwable th6) {
                                    th4.addSuppressed(th6);
                                }
                            }
                            throw th5;
                        }
                    }
                }
            }
            if (outputDir == null) {
                System.out.println("---------------------------------------");
            }
        } catch (Throwable th7) {
            try {
                throw th7;
            } catch (Throwable th8) {
                try {
                    reader.close();
                } catch (Throwable th9) {
                    th7.addSuppressed(th9);
                }
                throw th8;
            }
        }
    }

    public void extract(File input, File outputDir) throws IOException {
        extract(input, outputDir, ".vba");
    }
}
