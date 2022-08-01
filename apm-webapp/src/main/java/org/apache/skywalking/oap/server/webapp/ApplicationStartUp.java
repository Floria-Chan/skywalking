/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationStartUp {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartUp.class, args);
    }

    // 在spring cloud gateway中使用RouteLocator的Bean进行路由转发,等价于在yml配置文件中的配置
    // 配置没生效，改为代码做网关
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        // String str = "http://localhost:8096/graphql";
        return builder.routes()
            // id 表示被转发到uri地址的id名，
            .route("oap", p -> p
                // predicates，当访问的连接满足http://localhost:8096/csdn时即转发到https://blog.csdn.net
                .path("/graphql/**").uri("http://127.0.0.1:12800"))
            .build();
    }
}
