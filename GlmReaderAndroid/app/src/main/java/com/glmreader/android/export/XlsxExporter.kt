package com.glmreader.android.export

import android.content.Context
import android.os.Environment
import com.glmreader.android.data.entity.MeasurementEntity
import com.glmreader.android.protocol.BlePacketParser
import com.glmreader.android.protocol.InclinoLogic
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Экспорт измерений в XLSX формат.
 * Использует Apache POI 5.5.1.
 */
class XlsxExporter(private val context: Context) {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale("ru"))

    /**
     * Экспорт списка измерений в XLSX файл.
     * @return путь к файлу или null при ошибке
     */
    fun export(measurements: List<MeasurementEntity>): String? {
        return try {
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Измерения")

            // Заголовки
            val headerRow = sheet.createRow(0)
            val headers = arrayOf(
                "№", "Дата/Время", "Вид", "Результат", "Расчёт",
                "Угол (°)", "Точка отсчёта", "Лазер", "Устройство", "Проект"
            )
            headers.forEachIndexed { i, h ->
                val cell = headerRow.createCell(i)
                cell.setCellValue(h)
            }

            // Данные
            measurements.forEachIndexed { index, m ->
                val row = sheet.createRow(index + 1)
                val type = BlePacketParser.MeasurementType.fromSyncMode(m.measurementType)
                val refEdge = BlePacketParser.RefEdge.fromValue(m.refEdge)

                row.createCell(0).setCellValue((index + 1).toDouble())
                row.createCell(1).setCellValue(dateFormat.format(Date(m.timestamp)))
                row.createCell(2).setCellValue(type.displayName)
                row.createCell(3).setCellValue(formatValue(m, type))
                row.createCell(4).setCellValue(formatCalculation(m))
                row.createCell(5).setCellValue(m.angleDeg)
                row.createCell(6).setCellValue(refEdge.displayName)
                row.createCell(7).setCellValue(if (m.laserOn) "Вкл" else "Выкл")
                row.createCell(8).setCellValue(m.deviceName)
                row.createCell(9).setCellValue(m.projectId ?: "—")
            }

            // Автоматическая ширина колонок
            for (i in headers.indices) {
                sheet.autoSizeColumn(i)
            }

            // Сохранение
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale("ru")).format(Date())
            val fileName = "GlmReader_$timestamp.xlsx"
            val file = File(downloadsDir, fileName)

            FileOutputStream(file).use {
                workbook.write(it)
            }
            workbook.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun formatValue(m: MeasurementEntity, type: BlePacketParser.MeasurementType): String {
        return when (type) {
            BlePacketParser.MeasurementType.AREA_FINAL -> "%.2f м²".format(m.resultValue)
            BlePacketParser.MeasurementType.VOLUME_FINAL -> "%.2f м³".format(m.resultValue)
            BlePacketParser.MeasurementType.ANGLE -> "%.1f°".format(m.angleDeg)
            else -> "%.3f м".format(m.resultValue)
        }
    }

    private fun formatCalculation(m: MeasurementEntity): String {
        return if (m.calculationType != null && m.calculatedValue != null) {
            InclinoLogic.getCalculationDescription(
                when (m.calculationType) {
                    "direct" -> InclinoLogic.CalculationType.DIRECT
                    "height" -> InclinoLogic.CalculationType.HEIGHT
                    "width" -> InclinoLogic.CalculationType.WIDTH
                    else -> InclinoLogic.CalculationType.DIRECT
                },
                m.comp1Value,
                m.angleDeg,
                m.calculatedValue
            )
        } else {
            "—"
        }
    }
}
