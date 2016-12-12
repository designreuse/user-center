package com.ychp.club.common.model.mysql;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
public interface MybatisRepository<T> {

    public void create(T t);

    public void creates(List<T> t);

    public void update(T t);

    public void delete(Long id);

    public T findById(Long id);

    public List<T> findByIds(List<Long> id);

    public List<T> findListBy(Map<String,Object> params);

    public Long countBy(Map<String,Object> params);

    public List<T> pagingBy(Map<String,Object> params);

}
