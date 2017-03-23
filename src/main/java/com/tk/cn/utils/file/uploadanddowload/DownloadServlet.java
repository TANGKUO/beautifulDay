package com.tk.cn.utils.file.uploadanddowload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��ȡ�ļ�����·��
		String path = getServletContext().getRealPath("/") + "images/";
		String filename = req.getParameter("filename");
		File file = new File(path + filename);
		if(file.exists()){
			//������Ӧ����application/octet-stream
			resp.setContentType("application/x-msdownload");
			//����ͷ��Ϣ
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream ouputStream = resp.getOutputStream();
			byte b[] = new byte[1024];
			int n ;
			while((n = inputStream.read(b)) != -1){
				ouputStream.write(b,0,n);
			}
			//�ر������ͷ���Դ
			ouputStream.close();
			inputStream.close();
			
			
		}else{
			req.setAttribute("errorResult", "�ļ�����������ʧ�ܣ�");
			RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/01.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}

}
