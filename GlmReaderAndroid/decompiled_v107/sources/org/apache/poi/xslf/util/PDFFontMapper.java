package org.apache.poi.xslf.util;

import de.rototor.pdfbox.graphics2d.IPdfBoxGraphics2DFontTextDrawer;
import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.commons.lang3.SystemProperties;
import org.apache.pdfbox.pdmodel.font.PDFont;

/* loaded from: classes10.dex */
public class PDFFontMapper extends PdfBoxGraphics2DFontTextDrawer {
    private static final String DEFAULT_TTF_PATTERN = ".*\\.tt[fc]";
    private static final String FONTDIRS_MAC = "$HOME/Library/Fonts;/Library/Fonts;/Network/Library/Fonts;/System/Library/Fonts;/System Folder/Fonts";
    private static final String FONTDIRS_UNX = "/usr/share/fonts;/usr/local/share/fonts;$HOME/.fonts";
    private static final String FONTDIRS_WIN = "C:\\Windows\\Fonts";
    private final Map<String, File> fonts = new HashMap();
    private final Set<String> registered = new HashSet();

    public static /* synthetic */ File $r8$lambda$3UDCRuryPMpPdeZ_mA8WA5301tk(String str) {
        return new File(str);
    }

    public PDFFontMapper(String fontDir, String fontTtf) {
        registerFonts(fontDir, fontTtf);
    }

    private void registerFonts(String fontDir, String fontTtf) {
        if (fontDir == null) {
            String OS = System.getProperty(SystemProperties.OS_NAME, "generic").toLowerCase(Locale.ROOT);
            if (OS.contains("mac") || OS.contains("darwin")) {
                fontDir = FONTDIRS_MAC;
            } else if (OS.contains("win")) {
                fontDir = FONTDIRS_WIN;
            } else {
                fontDir = FONTDIRS_UNX;
            }
        }
        String fd = fontDir.replace("$HOME", System.getProperty("user.home"));
        final LinkedList<File> dirs = new LinkedList<>();
        Stream filter = Stream.of((Object[]) fd.split(";")).map(new Function() { // from class: org.apache.poi.xslf.util.PDFFontMapper$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PDFFontMapper.$r8$lambda$3UDCRuryPMpPdeZ_mA8WA5301tk((String) obj);
            }
        }).filter(new Predicate() { // from class: org.apache.poi.xslf.util.PDFFontMapper$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isDirectory;
                isDirectory = ((File) obj).isDirectory();
                return isDirectory;
            }
        });
        dirs.getClass();
        filter.forEach(new Consumer() { // from class: org.apache.poi.xslf.util.PDFFontMapper$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                dirs.add((File) obj);
            }
        });
        final Pattern p = Pattern.compile(fontTtf == null ? DEFAULT_TTF_PATTERN : fontTtf);
        while (!dirs.isEmpty()) {
            File[] ttfs = dirs.removeFirst().listFiles(new FilenameFilter() { // from class: org.apache.poi.xslf.util.PDFFontMapper$$ExternalSyntheticLambda3
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    return PDFFontMapper.lambda$registerFonts$0(dirs, p, file, str);
                }
            });
            if (ttfs != null) {
                for (File f : ttfs) {
                    try {
                        Font font = Font.createFont(0, f);
                        this.fonts.put(font.getFontName(Locale.ROOT), f);
                    } catch (IOException e) {
                    } catch (FontFormatException e2) {
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$registerFonts$0(LinkedList dirs, Pattern p, File f, String n) {
        File f2 = new File(f, n);
        if (f2.isDirectory()) {
            dirs.add(f2);
            return false;
        }
        return p.matcher(n).matches();
    }

    protected PDFont mapFont(Font font, IPdfBoxGraphics2DFontTextDrawer.IFontTextDrawerEnv env) throws IOException, FontFormatException {
        String name = font.getFontName(Locale.ROOT);
        if (!this.registered.contains(name)) {
            this.registered.add(name);
            File f = this.fonts.get(name);
            if (f != null) {
                super.registerFont(name, f);
            }
        }
        return super.mapFont(font, env);
    }
}
