package com.example.administrator.myparkingos.constant;

/**
 * Created by Administrator on 2017-04-24.
 */
public enum QueueMessageTypeEnum
{
    QUEUE_CAR_IN_TYPE_AUTO,           // 手动入场
    QUEUE_CAR_IN_TYPE_AUTO_NOPLATE,   // 无牌车手动入场
    QUEUE_CAR_IN_TYPE_RECOGNITION,     // 车牌识别入场

    QUEUE_CAR_OUT_TYPE_AUTO,          // 手动出场
    QUEUE_CAR_OUT_TYPE_AUTO_NOPLATE,   // 无牌车手动出场
    QUEUE_CAR_OUT_TYPE_RECOGNITION,     // 车牌识别出场

    QUEUE_CAR_INOUT_TYPE_RECOGNITION,   // 相机自动识别


    QUEUE_BLACKLIST,
    QUEUE_NOTHISLANEPERMISSION,
    QUEUE_BEOVERDUE,

    QUEUE_OPENGATE,
    QUEUE_VOICEINYW,
    QUEUE_CONFIRMCUTOFF,
    QUEUE_PERSONALFULL,
    QUEUE_PROHIBITCURRENT,
    QUEUE_PROHIBITCUTOFF,
    QUEUE_CARFULL,
    QUEUE_BALANCENOTENOUGH,
    QUEUE_TEMPORARYCARNOTINSMALL,

    QUEUE_CARFULLCONFIRMCUTOFF,
    queue_MthBeOverdueToTmpCharge

}
