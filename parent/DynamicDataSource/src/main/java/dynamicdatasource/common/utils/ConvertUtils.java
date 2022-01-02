package dynamicdatasource.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brand
 * @Description:
 * @Copyright: Copyright (c) 2022
 * @Company: Helenlyn, Inc. All Rights Reserved.
 * @date 2022/1/2 下午3:46
 * @Update Time:
 * @Updater:
 * @Update Comments:
 */
public class ConvertUtils {
    static {
        org.apache.commons.beanutils.ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    }

    /**
     * object转换成map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objToMap(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * map转换为object
     *
     * @param map
     * @param clz
     * @return
     * @throws Exception
     */
    public static Object mapToObj(Map<String, Object> map, Class<?> clz) throws Exception {
        Object obj = clz.newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    /**
     * List<HashMap>转换为List<T>
     * @param mapList
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List mapsToList(List<HashMap> mapList, Class clazz) {
        try {
            List<T> list = new ArrayList<>();
            for (HashMap hashMap : mapList) {
                T instance = (T) clazz.newInstance();
                BeanUtils.populate(instance, hashMap);
                list.add(instance);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList();
        }
    }
}