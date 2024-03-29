package com.youyu.cotenant.common;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CotenantConstants {
    interface DATE_FORMATTER {
        String DATETIME_IN_CHINESE = "yyyy年MM月dd日 HH:mm:ss";
    }

    //默认分页数据大小
    int DEFAULT_PAGE_SIZE = 20;
    //默认起始页
    int DEFAULT_PAGE_OFFSET = 0;

    String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    String DATE_TIME = "yyyy-MM-dd";

    String DEFAULT_PASSWORD = "123456";

    String CODE_CACHE = "code_";

    /*
    用户审核状态
     */
    interface USER_STATUS {
        int DEFAULT_STATUS = 0; //待审核
        int PASS_STATUS = 10; //审核通过
        int CANCEL_STATUS = 20; //审核拒绝
        int NOT_USER_STATUS = 30; //未补全信息
        int NOT_LOGIN = 40;//未登录
        Set<Integer> userStatus = Stream.of(DEFAULT_STATUS, PASS_STATUS, CANCEL_STATUS).collect(Collectors.toSet());
    }

    /*
    租房成员审核状态
     */
    interface EXAMINE_STATUS {
        int PASS_DEFAULT_STATUS = 0;  //申请状态
        int PASS = 10;  //通过
        int UNPASS = 20;  //未通过(拒绝)
        int CANCEL = 30;  //取消(退团)
    }

    /*
    用户类型
     */
    interface USER_TYPE {
        int PERSONAL = 0;  //个人

        int LANDLORD = 1;  //房东
    }

    /*
    租房类型
     */
    interface COTENANT_TYPE {
        int TYPE_1 = 1;  //预租房

        int TYPE_2 = 2;  //合租房

        int TYPE_3 = 3;  //房东直租
    }

    /*
    租房角色
     */
    interface GROUP_ROLE {
        int LEADER = 1;  //团长
        int MEMBER = 2;  //团员
    }

    /*
    租房状态
     */
    interface GROUP_STATUS {
        int DEFAULT_STATUS = 0; //审核中
        int PASS_STATUS = 30; //租房进行中
        int UNPASS_STATUS = 40; //审核拒绝
        int COMPLETE_STATUS = 10; //完成
        int CANCEL_STATUS = 20; //解散(取消)
        Set<Integer> groupStatus = Stream.of(PASS_STATUS, UNPASS_STATUS).collect(Collectors.toSet());

    }

    enum unreadActionType {
        MESSAGE(1),
        CART(2);

        int code;

        unreadActionType(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    String UNREAD_GROUP_KEY = "unread_message_key_";

    String UNREAD_MSG_COUNT = "unread_msg_count";

    /**
     * 即时通讯发送内容key
     */
    String CHAT_RECEIVE_KEY = "chat_receive_key_";

    String DOWNLOAD_URL = "https://service.dcloud.net.cn/build/download/f4187b60-24af-11ea-b536-91ba8bd01347";

}
