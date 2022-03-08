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
import java.util.zip.ZipOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesTest {
    static final String projectResourcePath = File.separator + "src" +
            File.separator + "test" + File.separator + "resources" + File.separator;
    static final String systemResoursePath = System.getProperty("user.dir") + projectResourcePath;

    @Test
    void zipFilesTest() throws IOException, CsvException {
        List<String> srcFiles = Arrays.asList(
                systemResoursePath + "file_example_XLS_10.xls",
                systemResoursePath + "sample.pdf",
                systemResoursePath + "username.csv");

        String zipFileName = "output.zip";

        createZipOutFile(srcFiles, zipFileName);

        verifyZipFile(zipFileName);
    }

    void createZipOutFile(List<String> srcFiles, String resultFileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(systemResoursePath + resultFileName);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            for (String srcFile : srcFiles) {
                try(FileInputStream fis = new FileInputStream(srcFile)) {
                    ZipEntry zipEntry = new ZipEntry(srcFile);
                    zipOut.putNextEntry(zipEntry);

                    byte[] bytes = new byte[8192];
                    int length;
                    while((length = fis.read(bytes)) != -1) {
                        zipOut.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    void verifyZipFile(String fileName) throws IOException, CsvException {
        ZipFile zipFile = new ZipFile(projectResourcePath + fileName);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while(entries.hasMoreElements()){
            ZipEntry entry = entries.nextElement();
            if (entry.getName().endsWith(".xls")) {
                try (InputStream is = zipFile.getInputStream(entry)) {

                    XLS xls = new XLS(is);
                    assertThat(xls.excel
                            .getSheetAt(0)
                            .getRow(3)
                            .getCell(6)
                            .getStringCellValue()).contains("21/05/2015");
                }
            } else if (entry.getName().endsWith(".pdf")) {
                try (InputStream is = zipFile.getInputStream(entry)) {
                    PDF pdf = new PDF(is);
                    assertThat(pdf.producer).contains("Nevrona Designs");
                    assertThat(pdf.numberOfPages).isEqualTo(2);
                }
            } else if (entry.getName().endsWith(".csv")) {
                try (InputStream is = zipFile.getInputStream(entry);
                     CSVReader reader = new CSVReader(new InputStreamReader(is))) {
                    List<String[]> allRows = reader.readAll();

                    String verifyString = convertToUtf8String(Arrays.toString(allRows.get(1)));

                    assertThat(verifyString).contains(
                            "booker12",
                            "9012",
                            "Rachel",
                            "Booker");
                }
            }
        }
    }

    String convertToUtf8String(String rawString) {
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
