package com.asugar.messageforlove.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * @Author : 镜像
 * @create 2023/2/25 19:19
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserRemote extends BaseEntity{

    @ApiModelProperty(value="用户id")
    private Long userId;

    @ApiModelProperty(value="商品id")
    private Long remoteId;

    @ApiModelProperty(value="是否使用")
    private Boolean isUse;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    @ApiModelProperty(value="修改时间")
    private Date updateTime;
}
