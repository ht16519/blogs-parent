package com.xh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Name WebApplication
 * @Description
 * @Author wen
 * @Date 2019-04-22
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xh.blogs.dao.mapper"})
public class WebApplication {

    public static void main(String[] args) {
        // 设置环境变量，解决Es的netty与Netty服务本身不兼容问题
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(WebApplication.class, args);
    }

}
