package com.asugar.messageforlove.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MessageVo对象", description="")
public class MsgVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "是否发送")
    private Boolean status;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Date sendtime;

}
