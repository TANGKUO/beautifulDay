package com.tk.cn.utils.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtil {
	
	private static String PDF = "text";
	private static String PIC = "pic";
	private static String FILE = "file";
	
	
	/**
	 * 可以处理文件，和图片
	 * @param files
	 * @return
	 */
	
	public static String[] parseFile(List<MultipartFile> files,Integer id,String base){
		List<String> result = new ArrayList<String>();
		for(MultipartFile mf : files){
			String s = mf.getOriginalFilename();
			//取得文件类型，
			int index = s.lastIndexOf(".");
			if(-1!= index){
				String path = getFileType(s.substring(s.lastIndexOf(".")+1),mf,id.toString(),base);
				System.out.println("保存的path是:"+path);
			}
		}
		return null;
	}
	
	private static String getFileType(String s,MultipartFile in,String id,String base){
		if(s.equalsIgnoreCase("gif")||s.equalsIgnoreCase("png")||s.equalsIgnoreCase("jpg")||s.equalsIgnoreCase("bmp")){
			return savePicture(s,in,id,base);
		}else if(s.equalsIgnoreCase("pdf")){
			return savePDF(s,in,id);
		}else{
			return FILE;
		}
	}
	
	private static String savePicture(String type,MultipartFile mf,String id,String base){
		try {
			BufferedImage img = ImageIO.read(mf.getInputStream());
			File file = new File(base+"/upload/"+id+"/"+mf.getOriginalFilename());
			if(!file.getParentFile().exists()){
				file.mkdirs();
			}
			ImageIO.write(img, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static String savePDF(String s,MultipartFile file,String id){
		return null;
	}
	
	
}
