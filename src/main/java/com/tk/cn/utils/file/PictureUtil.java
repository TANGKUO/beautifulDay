package com.tk.cn.utils.file;

import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>Title: PictureUtil</p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午8:42:31
 */
public class PictureUtil {

    public static final String PIC_JPEG = ".jpg";
    public static final double MAX_PX = 500;
    public static final int MAX_SIZE = 500;
    public static final int base = 1024;
    public static final String WRONG_MESSAGE = "图片不能超出" + MAX_SIZE + "KB";
    public static final String NO_PICS_MESSAGE = "没有选择图片";
    public static final String basePath=FileURl.basePath;
    
    public static String savePicture(String relativePath, MultipartFile... picture) throws IOException {
        return savePicture(relativePath,true,picture);
    }

    public static String savePicture(String relativePath, boolean isZoom, MultipartFile... picture) throws IOException {
        StringBuilder fileName = new StringBuilder("");
        for (int i = 0; i < picture.length; i++) {
            if (picture[i] != null) {
                //如果图片超出大小，将返回
                System.out.println("上传图片的大小为" + picture[i].getSize());
                if ((picture[i].getSize() / base) > MAX_SIZE) throw new PictureMaxSizeException();
                long fileNum = System.currentTimeMillis() + i;
                String realName = UploadProperties.uploadBase+fileNum + PictureUtil.PIC_JPEG;
                String tempName = UploadProperties.uploadBase+fileNum + "b" + PictureUtil.PIC_JPEG;
                String tempSmall = relativePath + "/" + fileNum + PictureUtil.PIC_JPEG;
                BufferedImage image = ImageIO.read(picture[i].getInputStream());
                File saveFile = new File(new File(relativePath), tempName);
                if (!saveFile.getParentFile().exists()) saveFile.getParentFile().mkdirs();
                ZoomPicture.savePicture(image, saveFile);
                if (isZoom) ZoomPicture.zoomPicture(saveFile, tempSmall, PictureUtil.MAX_PX);
                fileName.append(",").append(realName);
            }
        }
        if (fileName.length() > 1)
            return fileName.substring(1);
        else return "";
    }
    
    
    public static String savePicture(String relativePath,String childPath,MultipartFile picture) throws IOException{
        
        	if (picture != null&&picture.getSize()>0) {
                long fileNum = System.currentTimeMillis();;
                String realName = UploadProperties.uploadBase+childPath+"/"+fileNum + PictureUtil.PIC_JPEG;
                String tempName = UploadProperties.uploadBase+childPath+"/"+fileNum + "b" + PictureUtil.PIC_JPEG;
                
                BufferedImage image = ImageIO.read(picture.getInputStream());
                
                File realSave = new File(relativePath,realName);
                File tempSave = new File(relativePath,tempName);
                
                if (!realSave.getParentFile().exists()) realSave.getParentFile().mkdirs();
                
                ZoomPicture.savePicture(image, realSave,true);
                ZoomPicture.savePicture(image, tempSave,false);
                
                return realName;
                
            }else{
            	return null;
            }
    }
    
    
    public static String savePicture(String relativePath,String childPath,List<MultipartFile> picture) throws IOException{
    	
    	StringBuilder fileName = new StringBuilder();
        System.out.println("---------------------size is :"+picture.size());
        for(MultipartFile file : picture){
        	if (file != null&&file.getSize()>0) {
        		System.out.println("----------------->开始保存:"+file.getOriginalFilename());
        		System.out.println("------------------>文件大小:"+file.getSize());
                long fileNum = System.currentTimeMillis();
                String realName = UploadProperties.uploadBase+childPath+"/"+fileNum + PictureUtil.PIC_JPEG;
                String tempName = UploadProperties.uploadBase+childPath+"/"+fileNum + "b" + PictureUtil.PIC_JPEG;
                
                BufferedImage image = ImageIO.read(file.getInputStream());
                
                File realSave = new File(relativePath,realName);
                File tempSave = new File(relativePath,tempName);
                
                if (!realSave.getParentFile().exists()) realSave.getParentFile().mkdirs();
                
//                ZoomPicture.savePicture(image, realSave,true);
//                ZoomPicture.savePicture(image, tempSave,false);
                ZoomPicture.savePictureNoZip(image, realSave);
                fileName.append(",").append(basePath).append(realName);
            }
        }
        if(fileName.length()>1)
        	return fileName.substring(1).toString();
    	return null;
    }
    
    

}
