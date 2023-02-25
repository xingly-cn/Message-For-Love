package com.asugar.messageforlove.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 肖念昕
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信用户ID")
    private String uid;

    @ApiModelProperty(value = "商品id")
    private String remoteId;

    @ApiModelProperty(value = "可发送短信数量")
    private Integer cost;

    @ApiModelProperty(value = "每日发送上线")
    private Integer maxNum;

    @ApiModelProperty(value = "是否会员")
    private Integer vip;

    @ApiModelProperty(value = "注册时间")
    private Date time;
}
