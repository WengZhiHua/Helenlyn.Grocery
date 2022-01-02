package dynamicdatasource.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * @author brand
 * @Description: JPAQueryFactory Bean 配置
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午2:57
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Component
public class JpaConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return new JPAQueryFactory(entityManager);
    }
}