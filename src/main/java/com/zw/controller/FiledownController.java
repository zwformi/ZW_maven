package com.zw.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zw.dao.StudentDao;
import com.zw.entity.File;

@Controller("filedowm")
public class FiledownController {
	@Resource
	private StudentDao studentdao;
	
	
	@RequestMapping(value = "/dowmLoad/{id}", method = RequestMethod.GET)
	public void downLoad(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") String id) throws IOException, ServletException {
		Integer Id = Integer.valueOf(id);
		List<File> lf = studentdao.queryFileById(Id);
		String fileName = (String)lf.get(0).getFilepath(); 
		
		//得到要下载的文件名
        // String fileName = request.getParameter("filename");  //23239283-92489-阿凡达.avi
        // fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
         //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
         String fileSaveRootPath=request.getSession().getServletContext().getRealPath("/upload");
         //通过文件名找出文件的所在目录
         //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
         String path = fileSaveRootPath +"\\"+ fileName;
         //得到要下载的文件
         //File file = new File(path + "\\" + fileName);
         java.io.File file = new  java.io.File(path);
         //如果文件不存在
         if(!file.exists()){
             request.setAttribute("message", "您要下载的资源已被删除！！"); 
             request.getRequestDispatcher("/index.jsp").forward(request, response);
             return;
         }
         //处理文件名
         String realname = fileName.substring(fileName.indexOf("_")+1);
         //设置响应头，控制浏览器下载该文件
         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
         //读取要下载的文件，保存到文件输入流
         FileInputStream in = new FileInputStream(path);
         //创建输出流
         OutputStream out = response.getOutputStream();
         //创建缓冲区
         byte buffer[] = new byte[1024];
         int len = 0;
         //循环将输入流中的内容读取到缓冲区当中
         while((len=in.read(buffer))>0){ 
             //输出缓冲区的内容到浏览器，实现文件下载
             out.write(buffer, 0, len);
         }
         //关闭文件输入流
         in.close();
         //关闭输出流
         out.close();	
	}
}
