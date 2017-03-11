package com.tk.cn.utils.file;

public class Dimension {
    private int base;
    private int x,y;

    private Dimension(int x,int y,int base){
        this.x = x;
        this.y = y;
        this.base = base;

    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Dimension square(int oldWidth,int oldHeight){
        int x,y;
        int base;
        //取得最小的做正方形
        if(oldWidth>oldHeight){
            base = oldHeight;
            y=0;
            x=(oldWidth-oldHeight)/2;
        }else{
            base = oldWidth;
            x=0;
            y=(oldHeight-oldWidth)/2;

        }
        return new Dimension(x,y,base);
    }
}
