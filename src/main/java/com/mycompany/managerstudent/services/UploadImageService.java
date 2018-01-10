package com.mycompany.managerstudent.services;

import com.mycompany.managerstudent.interfaces.IUpload;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.SlackUtils;
import com.mycompany.managerstudent.util.Utils;
import java.io.File;
import java.util.Calendar;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author dientt
 */
public class UploadImageService implements IUpload {

    private static final String TAG = UploadImageService.class.getName();

    @Override
    public String createFolder() {
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            String pathImageURL = year + File.separator + month + File.separator + day;
            File fileUpload = new File(Constants.UPLOAD_RESOURCE + File.separator + pathImageURL);

            if (!fileUpload.exists()) {
                fileUpload.mkdirs();
            }
            return pathImageURL;
        } catch (Exception e) {
            SlackUtils.printDebug(TAG, e);
        }
        return null;
    }

    @Override
    public String setPath(String folder, String name) {
        if (StringUtils.isBlank(folder)) {
            return null;
        }
        String names[] = name.split("\\.");
        String fileName = Utils.encryptMD5(System.currentTimeMillis() + RandomStringUtils.random(8, true, true)).substring(0, 10);
        StringBuilder retval = new StringBuilder();
        retval.append(folder);
        retval.append(File.separator);
        retval.append(fileName);
        retval.append(".");
        retval.append(names[names.length - 1]);
        return retval.toString();
    }

}
