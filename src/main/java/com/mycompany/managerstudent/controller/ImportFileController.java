/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.managerstudent.controller;

import com.mycompany.managerstudent.interfaces.IImportFile;
import com.mycompany.managerstudent.interfaces.IUpload;
import com.mycompany.managerstudent.services.ImportFile;
import com.mycompany.managerstudent.services.UploadImageService;
import com.mycompany.managerstudent.util.Constants;
import com.mycompany.managerstudent.util.SlackUtils;
import com.mycompany.managerstudent.util.Utils;
import com.nct.framework.common.LogUtil;
import com.nct.framework.util.JSONUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

/**
 *
 * @author anhlnt
 */

public class ImportFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
    private static final org.apache.log4j.Logger logger = LogUtil.getLogger(UploadImageController.class);
    private static final String PASSING_URL_IMAGE = "url_file";
    private static final String TAG = UploadImageController.class.getName();
    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute("/");
        fileFactory.setRepository(filesDir);
        this.uploader = new ServletFileUpload(fileFactory);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        String result = null;
        try {
            List<FileItem> fileItemsList = uploader.parseRequest(request);
            Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            IImportFile uploadApk = new ImportFile();
            while (fileItemsIterator.hasNext()) {
                FileItem fileItem = fileItemsIterator.next();
//                String folderCreate = uploadApk.createFolder();
                
                    String pathResource = Constants.IMPORT_RESOURCE;
                    
                    File file = new File(uploadApk.setPath(pathResource, fileItem.getName()));
                    StringBuilder imgURL = new StringBuilder();
                    imgURL.append(Constants.STATIC_FOLDER);
//                    imgURL.append(folderCreate);
                    imgURL.append(File.separator);
                    imgURL.append(Constants.IMG_FOLDER);
                    imgURL.append(file.getName());
                    fileItem.write(file);
                    result = imgURL.toString();
                
            }

        } catch (FileUploadException e) {
            logger.error("error", e);
            result = e.getMessage();
            SlackUtils.printDebug(TAG, e);
        } catch (Exception e) {
            logger.error("error", e);
            result = e.getMessage();
            SlackUtils.printDebug(TAG, e);
        }
        try (PrintWriter out = response.getWriter()) {
            JSONObject obj = new JSONObject();
            obj.put(PASSING_URL_IMAGE, JSONUtil.Serialize(result));
            out.print(obj);
            out.flush();
        }

    }
}
