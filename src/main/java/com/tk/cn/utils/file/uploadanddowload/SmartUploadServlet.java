package com.tk.cn.utils.file.uploadanddowload;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SmartUploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//�����ϴ��ļ�����·��
		String filePath = getServletContext().getRealPath("/") + "images";
		File file = new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		
		/*SmartUpload su = new SmartUpload();
		//��ʼ������
		su.initialize(getServletConfig(), req, resp);
		//�����ϴ��ļ���С
		su.setMaxFileSize(1024*1024*10);
		//���������ļ��Ĵ�С
		su.setTotalMaxFileSize(1024*1024*100);
		//���������ϴ��ļ�����
		su.setAllowedFilesList("txt,jpg,gif");
		String result = "�ϴ��ɹ���";
		//���ý�ֹ�ϴ����ļ�����
		try {
			su.setDeniedFilesList("rar,jsp,js");
			//�ϴ��ļ�
			su.upload();
			
			int count = su.save(filePath);
			System.out.println("�ϴ��ɹ�" +  count + "���ļ���");
		} catch (Exception e) {
			result = "�ϴ�ʧ�ܣ�";
			e.printStackTrace();
		}
		
		for(int i =0; i < su.getFiles().getCount(); i++){
			com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
			System.out.println("---------------------------");
			System.out.println("������name����ֵ��" + tempFile.getFieldName());
			System.out.println("�ϴ��ļ�����" + tempFile.getFieldName());
			System.out.println("�ϴ��ļ�����:" + tempFile.getSize());
			System.out.println("�ϴ��ļ�����չ����" + tempFile.getFileExt());
			System.out.println("�ϴ��ļ���ȫ����" + tempFile.getFilePathName());
			System.out.println("---------------------------");
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("jsp/02.jsp").forward(req, resp);
	*/
	
	}

}
