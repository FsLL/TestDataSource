package com.lqj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 遥测帧描述表
 * </p>
 *
 * @author lqj
 * @since 2022-09-26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tele_frame_info")
public class TeleFrameInfoEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("frame_length")
    private Integer frameLength;

    @TableField("syn_code_con")
    private String synCodeCon;

    @TableField("frame_rate")
    private Integer frameRate;

    @TableField("pre_methods")
    private String preMethods;

    @Override
    public String toString() {
        return "TeleFrameInfoEntity{" +
                "id=" + id +
                ", frameLength=" + frameLength +
                ", synCodeCon='" + synCodeCon + '\'' +
                ", frameRate=" + frameRate +
                ", preMethods='" + preMethods + '\'' +
                '}';
    }
}
