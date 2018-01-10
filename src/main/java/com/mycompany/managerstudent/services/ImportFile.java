package com.mycompany.managerstudent.services;

import com.mycompany.managerstudent.interfaces.IImportFile;
import com.mycompany.managerstudent.models.Student;
import com.mycompany.managerstudent.util.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dientt
 */
public class ImportFile implements IImportFile{
    
    @Override
    public String setPath(String folder, String name) {
        if (StringUtils.isBlank(folder)) {
            return null;
        }
        String names[] = name.split("\\.");
        String fileName = "temp";
        StringBuilder retval = new StringBuilder();
        retval.append(folder);
        retval.append(File.separator);
        retval.append(fileName);
        retval.append(".");
        retval.append(names[names.length - 1]);
        return retval.toString();
    }
}
