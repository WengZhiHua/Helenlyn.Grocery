package dynamicdatasource.config;

import dynamicdatasource.dto.DataSourceCore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author brand
 * @Description: 映射yaml配置的数据源核心参数：project.mutil-data-ore
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:47
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@ConfigurationProperties(prefix = "spring.mutildata")
@Component
@Getter
@Setter
public class DataSourceCoreConfig {
    private HashMap<String, DataSourceCore> mutilDataCore;
}