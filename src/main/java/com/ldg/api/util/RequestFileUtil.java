package com.ldg.api.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class RequestFileUtil {
    public final static List<MultipartFile> getUploadFile(HttpServletRequest request)
            throws IllegalStateException, IOException {
        List<MultipartFile> rtFileList = null;
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            rtFileList = new ArrayList<MultipartFile>();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为"",说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        rtFileList.add(file);
                    }
                }
            }
        }
        return rtFileList;
    }

    /**
     * @param dirName 文件夹名
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    public final static String saveToComputer(List<MultipartFile> files, HttpServletRequest request, String dirName)
            throws IllegalStateException, IOException {
        String path = request.getServletContext().getRealPath(dirName);
        File parentFile = new File(path);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (files.size() == 1) {
            StringBuilder savedbFileName = new StringBuilder(dirName);
            MultipartFile mfile = files.get(0);
            StringBuilder fileAllPath = new StringBuilder(path);
            String saveFileName = RequestFileUtil.getSaveFileName(mfile.getOriginalFilename());
            fileAllPath.append("/").append(saveFileName);
            File saveFile = new File(fileAllPath.toString());
            mfile.transferTo(saveFile);
            savedbFileName.append("/").append(saveFileName);
            return savedbFileName.toString();// 文件夹名加文件名
        }
        if (files != null && files.size() > 0) {
            StringBuilder savedbFileNames = new StringBuilder(dirName);
            for (MultipartFile mfile : files) {
                StringBuilder savedbFileName = new StringBuilder(dirName);
                StringBuilder fileAllPath = new StringBuilder(path);
                String saveFileName = RequestFileUtil.getSaveFileName(mfile.getOriginalFilename());
                fileAllPath.append("/").append(saveFileName);
                File saveFile = new File(fileAllPath.toString());
                mfile.transferTo(saveFile);
                savedbFileName.append("/").append(saveFileName);
                savedbFileNames.append(savedbFileName).append(";");
            }
            return savedbFileNames.toString();
        }
        return null;
    }

    public final static String getSaveFileName(String sourceName) {
        int dianIndex = sourceName.indexOf(".");
        if (dianIndex != -1) {
            StringBuilder f = new StringBuilder();
            f.append(UUID.randomUUID().toString()).append(sourceName.substring(dianIndex, sourceName.length()));
            return f.toString();
        } else {
            return sourceName;
        }
    }

    public final static String getFileNameForBase64() {
        StringBuilder f = new StringBuilder();
        f.append(UUID.randomUUID().toString()).append(".jpg");
        return f.toString();
    }
}
