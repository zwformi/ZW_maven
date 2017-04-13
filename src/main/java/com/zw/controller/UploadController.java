package com.zw.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

@Controller
public class UploadController {
	 private static final long serialVersionUID = 1L;
	 
@RequestMapping(value="/simpleFileupload",method=RequestMethod.POST)
public void simpleFileupload(HttpServletRequest request){
    
	//1、创建一个DiskFileItemFactory工厂  
    DiskFileItemFactory factory = new DiskFileItemFactory();  
    //2、创建一个文件上传解析器  
    ServletFileUpload upload = new ServletFileUpload(factory);  
    //解决上传文件名的中文乱码  
    upload.setHeaderEncoding("UTF-8");   
    factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
    File linshi = new File("e:\\linshi");//当超过500K的时候，存到一个临时文件夹中  
    factory.setRepository(linshi);  
    upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M  
    try {  
        // 1. 得到 FileItem 的集合 items  
    	/* FileItem */
        List<FileItem> items = upload.parseRequest(request);  

        // 2. 遍历 items:  
        for (FileItem item : items) {  
            // 若是一个一般的表单域, 打印信息  
            if (item.isFormField()) {  
                String name = item.getFieldName();  
                String value = "";
				try {
					value = item.getString("utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

                System.out.println(name + ": " + value);  
                  
                  
            }  
            // 若是文件域则把文件保存到 e:\\files 目录下.  
            else {  
                String fileName = item.getName();  
                long sizeInBytes = item.getSize();  
                System.out.println(fileName);  
                System.out.println(sizeInBytes);  

                InputStream in = null;
				try {
					in = item.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                byte[] buffer = new byte[1024];  
                int len = 0;  

                fileName = "e:\\files\\" + fileName;//文件最终上传的位置  
                System.out.println(fileName);  
                OutputStream out = null;
				try {
					out = new FileOutputStream(fileName);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

                try {
					while ((len = in.read(buffer)) != -1) {  
					    out.write(buffer, 0, len);  
					}
					out.close();
					in.close();
					System.out.println("上传文件成功");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
                  
            }  
        }  

    } catch (FileUploadException e) {  
        e.printStackTrace();  
        System.out.println("上传文件失败");
    }  
	
	
	
/*	return rs;*/
}
	
	
	
	
}
