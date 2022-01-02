package dynamicdatasource.modules.userinfo.dao.impl;

import dynamicdatasource.modules.userinfo.dao.UserInfoDao;
import dynamicdatasource.modules.userinfo.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dynamicdatasource.common.utils.ConvertUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author brand
 * @Description: 用户明细信息：Dao层实现类
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:22
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserInfoDaoImpl implements UserInfoDao {

    /**
     * 注入jpa实体管理器，执行持久化操作
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 根据用户工号获取用户信息
     *
     * @param userCode 用户工号
     * @return
     */
    @Override
    public UserInfoDto getUserInfo(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            System.out.println("用户编号不能为空！");
            return null;
        }

        String sql = String.format(" select userCode,userName,userSex from userinfo where usercode='%s'; ", userCode);
        List userdt = entityManager.createNativeQuery(String.format(sql)).unwrap(NativeQueryImpl.class).
                setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
        List<UserInfoDto> userDtos = ConvertUtils.mapsToList(userdt, UserInfoDto.class);
        if (CollectionUtils.isNotEmpty(userDtos)) {
            return userDtos.get(0);
        }
        return null;
    }
}