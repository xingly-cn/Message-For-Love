package com.asugar.messageforlove.config;

import com.asugar.messageforlove.service.db.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CommonService commonService;

    @PostConstruct
    public void run() throws Exception {
        logger.info("================================Begin SystemInit===================================");

        logger.info("==========Begin 系统初始化,初始化脚本==========");

        commonService.execute();

        /**
         * 读取增量xml
         */
        commonService.readerXml();

        logger.info("==========End 系统初始化,初始化脚本==========");
    }
}
