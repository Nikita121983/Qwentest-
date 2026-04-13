package com.glmreader.android.export;

import android.content.Context;
import android.os.Environment;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.glmreader.android.data.entity.MeasurementEntity;
import com.glmreader.android.protocol.BlePacketParser;
import com.glmreader.android.protocol.InclinoLogic;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* compiled from: XlsxExporter.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/glmreader/android/export/XlsxExporter;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "export", "", "measurements", "", "Lcom/glmreader/android/data/entity/MeasurementEntity;", "formatValue", "m", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Lcom/glmreader/android/protocol/BlePacketParser$MeasurementType;", "formatCalculation", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class XlsxExporter {
    private final Context context;
    private final SimpleDateFormat dateFormat;

    /* compiled from: XlsxExporter.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes8.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BlePacketParser.MeasurementType.values().length];
            try {
                iArr[BlePacketParser.MeasurementType.AREA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.VOLUME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BlePacketParser.MeasurementType.ANGLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public XlsxExporter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", new Locale("ru"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    public final String export(List<MeasurementEntity> measurements) {
        String str;
        XSSFWorkbook xSSFWorkbook;
        XlsxExporter xlsxExporter = this;
        Intrinsics.checkNotNullParameter(measurements, "measurements");
        try {
            xSSFWorkbook = new XSSFWorkbook();
            XSSFSheet createSheet = xSSFWorkbook.createSheet("Измерения");
            XSSFRow createRow = createSheet.createRow(0);
            str = 3;
            String[] strArr = {"№", "Дата/Время", "Вид", "Результат", "Расчёт", "Угол (°)", "Точка отсчёта", "Лазер", "Устройство", "Проект"};
            int i = 0;
            int length = strArr.length;
            int i2 = 0;
            while (i2 < length) {
                createRow.createCell(i).setCellValue(strArr[i2]);
                i2++;
                i++;
            }
            List<MeasurementEntity> list = measurements;
            int i3 = 0;
            for (Object obj : list) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                MeasurementEntity measurementEntity = (MeasurementEntity) obj;
                XSSFRow createRow2 = createSheet.createRow(i3 + 1);
                BlePacketParser.MeasurementType fromDevMode = BlePacketParser.MeasurementType.INSTANCE.fromDevMode(measurementEntity.getMeasurementType());
                BlePacketParser.RefEdge fromValue = BlePacketParser.RefEdge.INSTANCE.fromValue(measurementEntity.getRefEdge());
                List<MeasurementEntity> list2 = list;
                createRow2.createCell(0).setCellValue(i3 + 1);
                try {
                    createRow2.createCell(1).setCellValue(xlsxExporter.dateFormat.format(new Date(measurementEntity.getTimestamp())));
                    createRow2.createCell(2).setCellValue(fromDevMode.getDisplayName());
                } catch (Exception e) {
                    e = e;
                    str = 0;
                    e.printStackTrace();
                    return str;
                }
                try {
                    createRow2.createCell(3).setCellValue(formatValue(measurementEntity, fromDevMode));
                    createRow2.createCell(4).setCellValue(formatCalculation(measurementEntity));
                    createRow2.createCell(5).setCellValue(measurementEntity.getAngleDeg());
                    createRow2.createCell(6).setCellValue(fromValue.getDisplayName());
                    createRow2.createCell(7).setCellValue(measurementEntity.getLaserOn() ? "Вкл" : "Выкл");
                    createRow2.createCell(8).setCellValue(measurementEntity.getDeviceName());
                    XSSFCell createCell = createRow2.createCell(9);
                    String projectId = measurementEntity.getProjectId();
                    if (projectId == null) {
                        projectId = "—";
                    }
                    createCell.setCellValue(projectId);
                    xlsxExporter = this;
                    i3 = i4;
                    list = list2;
                    str = 3;
                } catch (Exception e2) {
                    e = e2;
                    str = 0;
                    e.printStackTrace();
                    return str;
                }
            }
            int length2 = strArr.length;
            for (int i5 = 0; i5 < length2; i5++) {
                createSheet.autoSizeColumn(i5);
            }
        } catch (Exception e3) {
            e = e3;
        }
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "GlmReader_" + new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("ru")).format(new Date()) + ".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                str = 0;
                xSSFWorkbook.write(fileOutputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileOutputStream, null);
                xSSFWorkbook.close();
                return file.getAbsolutePath();
            } finally {
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
            return str;
        }
    }

    private final String formatValue(MeasurementEntity m, BlePacketParser.MeasurementType type) {
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                String format = String.format("%.2f м²", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
            case 2:
                String format2 = String.format("%.2f м³", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                return format2;
            case 3:
                String format3 = String.format("%.1f°", Arrays.copyOf(new Object[]{Double.valueOf(m.getAngleDeg())}, 1));
                Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                return format3;
            default:
                String format4 = String.format("%.3f м", Arrays.copyOf(new Object[]{Double.valueOf(m.getResultValue())}, 1));
                Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
                return format4;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final String formatCalculation(MeasurementEntity m) {
        InclinoLogic.CalculationType calculationType;
        if (m.getCalculationType() != null && m.getCalculatedValue() != null) {
            InclinoLogic inclinoLogic = InclinoLogic.INSTANCE;
            String calculationType2 = m.getCalculationType();
            if (calculationType2 != null) {
                switch (calculationType2.hashCode()) {
                    case -1331586071:
                        if (calculationType2.equals("direct")) {
                            calculationType = InclinoLogic.CalculationType.DIRECT;
                            break;
                        }
                        break;
                    case -1221029593:
                        if (calculationType2.equals("height")) {
                            calculationType = InclinoLogic.CalculationType.HEIGHT;
                            break;
                        }
                        break;
                    case 113126854:
                        if (calculationType2.equals("width")) {
                            calculationType = InclinoLogic.CalculationType.WIDTH;
                            break;
                        }
                        break;
                }
                return inclinoLogic.getCalculationDescription(calculationType, m.getComp1Value(), m.getAngleDeg(), m.getCalculatedValue().doubleValue());
            }
            calculationType = InclinoLogic.CalculationType.DIRECT;
            return inclinoLogic.getCalculationDescription(calculationType, m.getComp1Value(), m.getAngleDeg(), m.getCalculatedValue().doubleValue());
        }
        return "—";
    }
}
