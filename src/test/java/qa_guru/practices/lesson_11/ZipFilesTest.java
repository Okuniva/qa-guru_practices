package qa_guru.practices.lesson_11;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesTest {
    static final String projectResourcePath = "src" +
            File.separator + "test" + File.separator + "resources" + File.separator;

    @Test
    void zipFilesTest() throws IOException, CsvException {
        ZipFile zipFile = new ZipFile(projectResourcePath + "output.zip");
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            if (entry.getName().endsWith(".xls")) {
                try (InputStream is = zipFile.getInputStream(entry)) {
                    XLS xls = new XLS(is);
                    assertXLS(xls);
                }
            } else if (entry.getName().endsWith(".pdf")) {
                try (InputStream is = zipFile.getInputStream(entry)) {
                    PDF pdf = new PDF(is);
                    assertPDF(pdf);
                }
            } else if (entry.getName().endsWith(".csv")) {
                try (InputStream is = zipFile.getInputStream(entry);
                     CSVReader reader = new CSVReader(new InputStreamReader(is))) {
                    assertCSV(reader);
                }
            }
        }
    }

    void assertXLS(XLS xls) {
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(3)
                .getCell(6)
                .getStringCellValue()).contains("21/05/2015");
    }

    void assertPDF(PDF pdf) {
        assertThat(pdf.producer).contains("Nevrona Designs");
        assertThat(pdf.numberOfPages).isEqualTo(2);
    }

    void assertCSV(CSVReader reader) throws IOException, CsvException {
        List<String[]> allRows = reader.readAll();

        String verifyString = convertToUtf8String(Arrays.toString(allRows.get(1)));

        assertThat(verifyString).contains(
                "booker12",
                "9012",
                "Rachel",
                "Booker");
    }

    String convertToUtf8String(String rawString) {
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
