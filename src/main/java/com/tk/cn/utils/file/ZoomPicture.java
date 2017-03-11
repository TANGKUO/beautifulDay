package com.tk.cn.utils.file;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZoomPicture {


    public static void savePicture(BufferedImage img,File saveFile){
        savePicture(img,saveFile,true);
    }
    
    public static void savePicture(BufferedImage img,File saveFile,boolean flag){
        float size = 0.5f;
        if (!flag) size = 1;
        try {
        	System.out.println("------------------处理的文件名:>"+saveFile.getName());
            double width = img.getWidth()*size;
            double height = img.getHeight()*size;
            BufferedImage saved = new BufferedImage((int)width,(int)height,BufferedImage.TYPE_INT_RGB);
            saved.getGraphics().drawImage(img,0,0,(int)width,(int)height,null);
            ImageIO.write(saved, "jpg", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void savePictureNoZip (BufferedImage img,File saveFile){
        try {
        	System.out.println("------------------处理的文件名:>"+saveFile.getName());
            ImageIO.write(img, "jpg", saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    


	public static void zoomPicture(File inputFile, String outputPicName, final double max) {
        double ratio = 1.0;
	    try {
	    	BufferedImage Bi = ImageIO.read(inputFile);
	    	if ((Bi.getWidth() > max)) {
	    		ratio = max / Bi.getWidth();
	    	}
	    	int widthdist = (int) Math.floor(Bi.getWidth() * ratio);
            int heightdist = (int) Math.floor(Bi.getHeight() * ratio);
            Dimension de = Dimension.square(widthdist,heightdist);
            System.out.println("##-------------------------------取得的照片信息-------------------------------#");
            System.out.println("[x:"+de.getX()+"],[y:"+de.getY()+"],[base:"+de.getBase()+"]");
            System.out.println("##-------------------------------取得的照片信息-------------------------------#");
	    	BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
	    	tag.getGraphics().drawImage(Bi.getScaledInstance(widthdist, heightdist, BufferedImage.SCALE_SMOOTH), 0, 0, null);
            BufferedImage tt = tag.getSubimage(de.getX(),de.getY(),de.getBase(),de.getBase());
	    	File littleFile = new File(outputPicName);
	    	ImageIO.write(tt, "JPEG", littleFile);
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	 }


}
