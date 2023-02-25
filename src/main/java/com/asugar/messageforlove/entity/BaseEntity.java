package com.asugar.messageforlove.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

/**
 * Created by IntelliJ IDEA.
 * @Author : 镜像
 * @create 2023/2/25 19:20
 */
@Data
@ToString
public class BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
}
