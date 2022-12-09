package com.yaroshevich.app.util;

import com.yaroshevich.app.dao.EmployeeDao;
import com.yaroshevich.app.model.Employee;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeExcelGenerator {

    public void generateExcel(ServletOutputStream out) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            EmployeeDao dao = new EmployeeDao();
            ArrayList<Employee> employees = (ArrayList<Employee>) dao.getAll();

            Map<Integer, String> fields = new HashMap<Integer, String>() {{
                put(0, "Имя");
                put(1, "Фамилия");
                put(2, "Отчество");
                put(3, "Возраст");
                put(4, "Адрес");
                put(5, "Район");
                put(6, "Регион");
                put(7, "Начало смены");
                put(8, "Конец смены");
            }};

            // Создание листа
            XSSFSheet sheet = workbook.createSheet("Сотрудники");
            Row titleRow = sheet.createRow(0);

            // Стили для строки с заголовками колонок
            CellStyle cellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            cellStyle.setFont(font);

            // Создание строки с заголовками
            for (Map.Entry<Integer, String> entry : fields.entrySet()) {
                Cell cell = titleRow.createCell(entry.getKey());
                cell.setCellValue(entry.getValue());
                cell.setCellStyle(cellStyle);
            }

            // Заполнение таблицы данными
            int rowIndex = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowIndex);
                row.createCell(0).setCellValue(employee.getFirstName());
                row.createCell(1).setCellValue(employee.getSecondName());
                row.createCell(2).setCellValue(employee.getPatronymic());
                row.createCell(3).setCellValue(employee.getAge());
                row.createCell(4).setCellValue(employee.getAddress().getAddress());
                row.createCell(5).setCellValue(employee.getAddress().getDistrict());
                row.createCell(6).setCellValue(employee.getAddress().getRegion());
                row.createCell(7).setCellValue(employee.getShift().getStart());
                row.createCell(8).setCellValue(employee.getShift().getEnd());

                rowIndex++;
            }

            // Авторазмер для колонок, учитывая самую большую запись
            for (int i = 0; i < fields.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}