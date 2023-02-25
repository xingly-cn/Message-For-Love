package com.asugar.messageforlove.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "可发送短信数量")
    private Integer cost;

    @ApiModelProperty(value = "每日发送上线")
    private Integer maxNum;

    @ApiModelProperty(value = "是否会员")
    private Boolean vip;

    @ApiModelProperty(value = "注册时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;
}
