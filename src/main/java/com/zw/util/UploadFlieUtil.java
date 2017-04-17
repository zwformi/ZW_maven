package com.zw.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

import com.zw.dao.StudentDao;
import com.zw.entity.Student;

@Component("fileUpload")
public class UploadFlieUtil {

private static final long serialVersionUID = 1L;
@Resource
private JdbcTemplate jdbcTemplate;
@SuppressWarnings("unused")
public  Map<String, Object> simpleFileupload(HttpServletRequest request){ 
	
	StudentDao studentdao = new StudentDao();
    Integer id = 0;
	Map<String, Object> rs =new HashMap<String,Object>(); 
	//1、创建一个DiskFileItemFactory工厂  
    DiskFileItemFactory factory = new DiskFileItemFactory();  
    //2、创建一个文件上传解析器  
    ServletFileUpload upload = new ServletFileUpload(factory);  
    //解决上传文件名的中文乱码  
    upload.setHeaderEncoding("UTF-8");  
    
    //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
    String savePath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
    
   //创建文件upload目录
    File uploadfile = new File(savePath);
    if(!uploadfile.exists()&&!uploadfile.isDirectory()){
        System.out.println("upload及子目录或子文件不存在！");
        uploadfile.mkdir();
    }
    
    factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
    
    String tempPath = request.getSession().getServletContext().getRealPath("/WEB-INF/uploadtemp");
    File tempfile = new File(tempPath);//当超过500K的时候，存到一个临时文件夹中  
    if(!tempfile.exists()&&!tempfile.isDirectory()){
        System.out.println("uploadtemp目录或文件不存在！");
        tempfile.mkdir();
    }
    
    factory.setRepository(tempfile);  
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
					if(name.equals("id")){
						id = Integer.valueOf(value);
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  

                System.out.println(name + ": " + value);  
                  
                  
            }  
            // 若是文件域则把文件保存到 e:\\files 目录下.  
            else {  
                String fileName = item.getName();                
                //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                //处理获取到的上传文件的文件名的路径部分，只保留文件名部分 File.separator==\
                fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
                
                //得到上传文件的扩展名
                String fileExtName = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
                Integer type = -1;
                String subPath = "";
               
                if("bmp".equals(fileExtName)||"jpeg".equals(fileExtName)||"jpg".equals(fileExtName)||"gif".equals(fileExtName)||"png".equals(fileExtName)){
                    type = 0;
                    subPath = "\\images";
                }
                else if("txt".equals(fileExtName)||"doc".equals(fileExtName)||"docx".equals(fileExtName)||"xls".equals(fileExtName)||"pdf".equals(fileExtName)||"ppt".equals(fileExtName)){
                    type = 1;
                    subPath = "\\text";
                }
                else if("mp3".equals(fileExtName)||"wma".equals(fileExtName)||"wav".equals(fileExtName)||"aac".equals(fileExtName)||"asf".equals(fileExtName)){
                    type = 2;
                    subPath = "\\audio";
                }
                else if("mp4".equals(fileExtName)||"3gp".equals(fileExtName)||"avi".equals(fileExtName)||"rmvb".equals(fileExtName)){
                    type = 3;
                    subPath = "\\video";
                }
                else if("zip".equals(fileExtName)||"rar".equals(fileExtName)||"tar".equals(fileExtName)||"jar".equals(fileExtName)){
                	type = 4;
                    subPath = "\\compress";
                }
                else{
                     Map<String, Object> message = new HashMap<String, Object>();
	 					rs.put("resultcode", 1);
	 					message.put("type", -1);
	 					message.put("error", "上传文件的类型不符合！！！");
	 					rs.put("resultmessage", message);
                     return rs;
                }
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

                String path = savePath+subPath;           
                //创建文件子目录
                File uploadfile1 = new File(path);
                if(!uploadfile1.exists()&&!uploadfile1.isDirectory()){
                    System.out.println("upload子目录或子文件不存在！");
                    uploadfile1.mkdir();
                }
                
                fileName =mkFileName(fileName);
                System.out.println(fileName);
                String newfileName = path+"\\"+fileName;//文件最终上传的位置  
                OutputStream out = null;
				try {
					out = new FileOutputStream(newfileName);
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
					String Path = "\\"+subPath+"\\\\"+fileName;
					if(type==0){
						Map<String, Object> message = new HashMap<String, Object>();
						rs.put("resultcode", 1);
						message.put("type", type);
						message.put("path", Path);
						message.put("id",id); 
						rs.put("resultmessage", message);
					}
					String aa = "images123.jpg";
					studentdao.insertFile( id ,aa, type);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Map<String, Object> message = new HashMap<String, Object>();
					rs.put("resultcode", 0);
					message.put("type", -1); 
					message.put("error", e.getMessage());
					rs.put("resultmessage", message);
					return rs;
				}  
                  
            }  
        }  

    } catch (FileUploadException e) {  
        e.printStackTrace();  
        System.out.println("上传文件失败");
        Map<String, Object> message = new HashMap<String, Object>();
        rs.put("resultcode", 0);
		message.put("type", -1);
		message.put("error", e.getMessage());
		rs.put("resultmessage", message);
		return rs;
    }  
    if(rs==null){
    	Map<String, Object> message = new HashMap<String, Object>();
        rs.put("resultcode", 1);
 		message.put("type", -1);
 		message.put("error", "文件上传成功");
 		rs.put("resultmessage", message);
    }
	return rs;
}
//生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
public static String mkFileName(String fileName){
    return UUID.randomUUID().toString()+"_"+fileName;
}
	
	
	
}
