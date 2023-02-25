package com.asugar.messageforlove.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 19:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Remote对象", description="")
public class Remote extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value ="数量")
    private Integer amount;
}