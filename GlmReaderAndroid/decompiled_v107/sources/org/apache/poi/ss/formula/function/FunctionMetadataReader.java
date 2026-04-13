package org.apache.poi.ss.formula.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.IOUtils;

/* loaded from: classes10.dex */
final class FunctionMetadataReader {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final String ELLIPSIS = "...";
    private static final String METADATA_FILE_NAME = "functionMetadata.txt";
    private static final String METADATA_FILE_NAME_CETAB = "functionMetadataCetab.txt";
    private static int MAX_RECORD_LENGTH = 100000;
    private static final Pattern TAB_DELIM_PATTERN = Pattern.compile("\t");
    private static final Pattern SPACE_DELIM_PATTERN = Pattern.compile(StringUtils.SPACE);
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String[] DIGIT_ENDING_FUNCTION_NAMES = {"LOG10", "ATAN2", "DAYS360", "SUMXMY2", "SUMX2MY2", "SUMX2PY2", "A1.R1C1"};
    private static final Set<String> DIGIT_ENDING_FUNCTION_NAMES_SET = new HashSet(Arrays.asList(DIGIT_ENDING_FUNCTION_NAMES));

    FunctionMetadataReader() {
    }

    public static void setMaxRecordLength(int length) {
        MAX_RECORD_LENGTH = length;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public static FunctionMetadataRegistry createRegistry() {
        FunctionDataBuilder fdb = new FunctionDataBuilder(800);
        readResourceFile(fdb, METADATA_FILE_NAME);
        return fdb.build();
    }

    public static FunctionMetadataRegistry createRegistryCetab() {
        FunctionDataBuilder fdb = new FunctionDataBuilder(800);
        readResourceFile(fdb, METADATA_FILE_NAME_CETAB);
        return fdb.build();
    }

    /* JADX WARN: Finally extract failed */
    private static void readResourceFile(FunctionDataBuilder fdb, String resourceFile) {
        try {
            InputStream is = FunctionMetadataReader.class.getResourceAsStream(resourceFile);
            try {
                if (is == null) {
                    throw new IllegalStateException("resource '" + resourceFile + "' not found");
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                while (true) {
                    try {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.length() >= 1 && line.charAt(0) != '#') {
                            String trimLine = line.trim();
                            if (trimLine.length() >= 1) {
                                processLine(fdb, line);
                            }
                        }
                    } finally {
                    }
                }
                br.close();
                if (is != null) {
                    is.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } finally {
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void processLine(FunctionDataBuilder fdb, String line) {
        String[] parts = TAB_DELIM_PATTERN.split(line, -2);
        if (parts.length != 8) {
            throw new IllegalStateException("Bad line format '" + line + "' - expected 8 data fields delimited by tab, but had " + parts.length + ": " + Arrays.toString(parts));
        }
        int functionIndex = parseInt(parts[0]);
        String functionName = parts[1];
        int minParams = parseInt(parts[2]);
        int maxParams = parseInt(parts[3]);
        byte returnClassCode = parseReturnTypeCode(parts[4]);
        byte[] parameterClassCodes = parseOperandTypeCodes(parts[5]);
        boolean hasNote = !parts[7].isEmpty();
        validateFunctionName(functionName);
        fdb.add(functionIndex, functionName, minParams, maxParams, returnClassCode, parameterClassCodes, hasNote);
    }

    private static byte parseReturnTypeCode(String code) {
        if (code.isEmpty()) {
            return (byte) 0;
        }
        return parseOperandTypeCode(code);
    }

    private static byte[] parseOperandTypeCodes(String codes) {
        if (codes.length() < 1) {
            return EMPTY_BYTE_ARRAY;
        }
        if (isDash(codes)) {
            return EMPTY_BYTE_ARRAY;
        }
        String[] array = SPACE_DELIM_PATTERN.split(codes);
        int nItems = array.length;
        if (ELLIPSIS.equals(array[nItems - 1])) {
            nItems--;
        }
        byte[] result = IOUtils.safelyAllocate(nItems, getMaxRecordLength());
        for (int i = 0; i < nItems; i++) {
            result[i] = parseOperandTypeCode(array[i]);
        }
        return result;
    }

    private static boolean isDash(String codes) {
        return codes.length() == 1 && codes.charAt(0) == '-';
    }

    private static byte parseOperandTypeCode(String code) {
        if (code.length() != 1) {
            throw new IllegalStateException("Bad operand type code format '" + code + "' expected single char");
        }
        switch (code.charAt(0)) {
            case 'A':
                return Ptg.CLASS_ARRAY;
            case 'R':
                return (byte) 0;
            case 'V':
                return (byte) 32;
            default:
                throw new IllegalArgumentException("Unexpected operand type code '" + code + "' (" + ((int) code.charAt(0)) + ")");
        }
    }

    private static void validateFunctionName(String functionName) {
        int len = functionName.length();
        int ix = len - 1;
        if (!Character.isDigit(functionName.charAt(ix))) {
            return;
        }
        while (ix >= 0 && Character.isDigit(functionName.charAt(ix))) {
            ix--;
        }
        if (DIGIT_ENDING_FUNCTION_NAMES_SET.contains(functionName)) {
        } else {
            throw new IllegalStateException("Invalid function name '" + functionName + "' (is footnote number incorrectly appended)");
        }
    }

    private static int parseInt(String valStr) {
        try {
            return Integer.parseInt(valStr);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Value '" + valStr + "' could not be parsed as an integer");
        }
    }
}
