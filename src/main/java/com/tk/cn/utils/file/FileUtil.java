package com.tk.cn.utils.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	public static final String basePath=FileURl.basePath;
	
	public static String saveFile(String relativePath,String child,MultipartFile sound) throws IOException{
		
    	if (sound != null&&sound.getSize()>0) {
            long fileNum =  System.currentTimeMillis();
            String type = sound.getOriginalFilename();
            int index =type.lastIndexOf(".");
            String realName = UploadProperties.uploadBase+child+"/"+fileNum + type.substring(index);
            logger.debug("文件要保存的文件名是:"+realName);
            File realSave = new File(relativePath,realName);
            if (!realSave.getParentFile().exists()) realSave.getParentFile().mkdirs();
            FileUtils.copyInputStreamToFile(sound.getInputStream(), realSave);
            return basePath+realName;
            
        }else{
        	return "";
        }
		
	}
	
	private static String getFileType(String type){
		return type.substring(type.lastIndexOf("."));
	}
	
	public static String saveMessageAudio(String relativePath,MultipartFile sound) throws IOException{
		
		if(null!=sound&&sound.getSize()>0){
			long currentTime = System.nanoTime();
			String realName = UploadProperties.messageAudio+currentTime+getFileType(sound.getOriginalFilename());
			
			File saveFile = new File(relativePath,realName);
			
			if(!saveFile.getParentFile().exists()) saveFile.getParentFile().mkdirs();
			
			FileUtils.copyInputStreamToFile(sound.getInputStream(), saveFile);
			
			logger.debug("聊天保存的录音为:"+realName);
			
			return realName;
		}
		return null;
	}
	
	public static String saveMessagePicture(String relativePath,MultipartFile file) throws IOException{
		long currentTime = System.nanoTime();
		
        String realName = UploadProperties.messagePicture+currentTime+PictureUtil.PIC_JPEG;
        
        BufferedImage image = ImageIO.read(file.getInputStream());
        
        File realSave = new File(relativePath,realName);
        
        if (!realSave.getParentFile().exists()) realSave.getParentFile().mkdirs();
        
        ZoomPicture.savePicture(image, realSave);
        
        logger.debug("聊天保存的图片为:"+realName);
		
		return realName;
	}

	
}
