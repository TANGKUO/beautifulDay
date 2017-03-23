package com.tk.cn.utils.file.uploadanddowload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��request���л�ȡ����Ϣ
		InputStream fileSource = req.getInputStream();
		String tempFileName = "E:/tempFile";
		//tempFileָ����ʱ�ļ�
		File tempFile = new File(tempFileName);
		//outputStram�ļ������ָ�������ʱ�ļ�
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[1024];
		int n;
		while(( n = fileSource.read(b)) != -1){
			outputStream.write(b, 0, n);
		}
		//�ر��������������
		outputStream.close();
		fileSource.close();
		
		//��ȡ�ϴ��ļ�������
		RandomAccessFile randomFile = new RandomAccessFile(tempFile,"r");
		randomFile.readLine();
		String str = randomFile.readLine();
		int beginIndex = str.lastIndexOf("\\") + 1;
		int endIndex = str.lastIndexOf("\"");
		String filename = str.substring(beginIndex, endIndex);
		System.out.println("filename:" + filename);
		
		//���¶�λ�ļ�ָ�뵽�ļ�ͷ
		randomFile.seek(0);
		long startPosition = 0;
		int i = 1;
		//��ȡ�ļ����� ��ʼλ��
		while(( n = randomFile.readByte()) != -1 && i <=4){
			if(n == '\n'){
				startPosition = randomFile.getFilePointer();
				i ++;
			}
		}
		startPosition = randomFile.getFilePointer() -1;
		//��ȡ�ļ����� ����λ��
		randomFile.seek(randomFile.length());
		long endPosition = randomFile.getFilePointer();
		int j = 1;
		while(endPosition >=0 && j<=2){
			endPosition--;
			randomFile.seek(endPosition);
			if(randomFile.readByte() == '\n'){
				j++;
			}
		}
		endPosition = endPosition -1;
		
		//���ñ����ϴ��ļ���·��
		String realPath = getServletContext().getRealPath("/") + "images";
		File fileupload = new File(realPath);
		if(!fileupload.exists()){
			fileupload.mkdir();
		}
		File saveFile = new File(realPath,filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
		//����ʱ�ļ����ж�ȡ�ļ����ݣ�������ֹλ�û�ȡ��
		randomFile.seek(startPosition);
		while(startPosition < endPosition){
			randomAccessFile.write(randomFile.readByte());
			startPosition = randomFile.getFilePointer();
		}
		//�ر������������ɾ����ʱ�ļ�
		randomAccessFile.close();
		randomFile.close();
		tempFile.delete();
		
		req.setAttribute("result", "�ϴ��ɹ���");
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/01.jsp");
		dispatcher.forward(req, resp);
	}
}
