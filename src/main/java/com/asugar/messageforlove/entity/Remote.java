package com.asugar.messageforlove.entity;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 19:48
 */
@Data
public class Remote {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 价格
     */
    private String price;

    /**
     * 数量
     */
    private Integer amount;
}