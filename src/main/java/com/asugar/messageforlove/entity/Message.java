package com.asugar.messageforlove.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Message对象", description="")
public class Message extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信用户ID")
    private String uid;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "是否发送")
    private Boolean status;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;
}
