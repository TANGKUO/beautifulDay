package com.tk.cn.entity;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午3:35:46
 */
public class Menu {
    private String id;
    private String text;
    private MenuTree menuTree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MenuTree getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(MenuTree menuTree) {
        this.menuTree = menuTree;
    }
}
