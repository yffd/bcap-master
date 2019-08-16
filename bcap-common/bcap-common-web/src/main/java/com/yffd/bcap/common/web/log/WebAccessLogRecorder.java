package com.yffd.bcap.common.web.log;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAccessLogRecorder implements WebAccessRecorder {
    private static final Logger LOG = LoggerFactory.getLogger(WebAccessLogRecorder.class);

    @Override
    public void recorde(WebAccessMsg logMsg) {
        LOG.info(JSON.toJSONString(logMsg));
    }

}
