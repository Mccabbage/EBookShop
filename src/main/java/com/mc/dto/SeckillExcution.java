package com.mc.dto;

import com.mc.entity.SuccessKilled;
import com.mc.enums.SeckillStateEnum;

/**
 * 秒杀后执行结果
 * created by machao on  2018/4/16 22:27
 */
public class SeckillExcution {

    private long seckillId;
    /**
     * 秒杀执行结果状态
     */
    private int state;
    /**
     * 状态表示
     */
    private String stateInfo;

    private SuccessKilled successKilled;

    public SeckillExcution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public SeckillExcution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    @Override
    public String toString() {
        return "SeckillExcution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                '}';
    }
}
