package com.asugar.messageforlove.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserVo对象", description = "")
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "微信用户ID")
    private String uid;

}
