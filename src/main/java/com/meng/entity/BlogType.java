package com.meng.entity;

import java.io.Serializable;

/**
 * @Description 博客类别实体类
 *
 */
public class BlogType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String typeName;
    private Integer orderNum;
    
    public BlogType(Integer id, String typeName, Integer orderNum) {
        this.id = id;
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public BlogType(String typeName, Integer orderNum) {
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "BlogType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
    
}
