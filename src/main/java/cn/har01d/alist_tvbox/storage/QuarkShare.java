package cn.har01d.alist_tvbox.storage;

import cn.har01d.alist_tvbox.entity.DriverAccount; // 引入 DriverAccount
import cn.har01d.alist_tvbox.entity.Share;
import org.apache.commons.lang3.StringUtils; // 引入 StringUtils

public class QuarkShare extends Storage {
    public QuarkShare(Share share) {
        super(share, "QuarkShare");
        setWebdavPolicy("native_proxy");
        addAddition("share_id", share.getShareId());
        addAddition("share_pwd", share.getPassword());
        addAddition("root_folder_id", share.getFolderId());
        addAddition("order_by", "file_name");
        addAddition("order_direction", "asc");
        buildAddition();
    }

    // 新增构造函数，接收 DriverAccount
    public QuarkShare(Share share, DriverAccount account) {
        super(share, "QuarkShare");
        setWebdavPolicy("native_proxy");
        addAddition("share_id", share.getShareId());
        addAddition("share_pwd", share.getPassword());
        addAddition("root_folder_id", share.getFolderId());
        addAddition("order_by", "file_name");
        addAddition("order_direction", "asc");
        
        // 如果传入了 QuarkTV 账号，使用其 refresh_token
        if (account != null) {
            if (StringUtils.isNotBlank(account.getToken())) {
                addAddition("refresh_token", account.getToken());
            }
            // 如果有 cookie 也可以添加，但 QuarkTV 主要是 token
            if (StringUtils.isNotBlank(account.getCookie())) {
                addAddition("cookie", account.getCookie());
            }
        }
        buildAddition();
    }
}
