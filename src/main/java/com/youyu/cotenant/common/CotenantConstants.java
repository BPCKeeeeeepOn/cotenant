package com.youyu.cotenant.common;

public interface CotenantConstants {
    interface DATE_FORMATTER {
        String DATETIME_IN_CHINESE = "yyyy年MM月dd日 HH:mm:ss";
    }

    //默认分页数据大小
    int DEFAULT_PAGE_SIZE = 20;
    //默认起始页
    int DEFAULT_PAGE_OFFSET = 0;

    String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    String DEFAULT_PASSWORD = "123456";

    String CODE_CACHE = "code_";

    /*
    用户审核状态
     */
    interface USER_STATUS {
        int DEFAULT_STATUS = 0;
        int PASS_STATUS = 10;
        int CANCEL_STATUS = 20;
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
    租房类型
     */
    interface COTENANT_TYPE {
        int TYPE_1 = 1;  //预租房

        int TYPE_2 = 2;  //合租房
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
        int DEFAULT_STATUS = 0; //租房中
        int COMPLETE_STATUS = 10; //完成
        int CANCEL_STATUS = 20; //解散(取消)
    }

    enum unreadActionType {
        CHAT(1),
        MESSAGE(2);

        int code;

        unreadActionType(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    String UNREAD_MESSAGE_KEY = "unread_message_key_";

    String UNREAD_MSG_COUNT = "unread_msg_count";

    /**
     * 即时通讯发送内容key
     */
    String CHAT_RECEIVE_KEY = "chat_receive_key_";

}
