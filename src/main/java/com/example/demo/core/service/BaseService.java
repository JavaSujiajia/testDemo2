package com.example.demo.core.service;

import com.example.demo.core.lang.Assert;
import com.example.demo.core.lang.HttpCode;
import com.example.demo.core.lang.Session;
import com.example.demo.core.mybatis.BaseMapper;
import com.example.demo.core.utils.CommonUtils;
import com.example.demo.enums.Status;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @author sujia
 * @date 2019年11月20日 16:19
 */
public abstract class BaseService<T> implements HttpCode {


    @Autowired
    private BaseMapper<T> mapper;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseService() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T>) types[0];
        }
    }

    /**
     * @MethodName: 通过主键id查询
     * @Description: TODO
     * @Param: id
     * @Return: T
     * @Author: Administrator
     * @Date: 2019/11/20
     **/
    public T findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * @MethodName: 按条件查询
     * @Description: TODO
     * @Param: entity
     * @Return: List<T>
     * @Author: Administrator
     * @Date: 2019/11/20
     **/
    public List<T> select(T record) {
        return mapper.select(record);
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 分页查询
     * TODO 等待实现orderBy
     *
     * @param record
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<T> select(T record, int pageNum, int pageSize) {
        return select(record, pageNum, pageSize, null);
    }

    public Page<T> select(T record, int pageNum, int pageSize, Map<String, String> order) {
        Page<T> page = PageHelper.startPage(pageNum, pageSize);
        if (CommonUtils.isNotEmpty(order)) {
            page.setOrderBy(orderSql(order));
        }
        page.doSelectPage(() -> mapper.select(record));
        return page;
    }

    /**
     * 查询数量
     *
     * @param record
     * @return
     */
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    /**
     * 自定义SQL查询数量
     *
     * @param weekend
     * @return
     * @see #selectByExample(Weekend)
     */
    public int selectCountByWeekend(Weekend<?> weekend) {
        return mapper.selectCountByExample(weekend);
    }

    /**
     * 查询单条数据
     * TODO 可以在entry里面设置默认的orderBy，但以后改造为自定义
     *
     * @param record
     * @return
     */
    public T selectOne(T record) {
        List<T> list = mapper.select(record);
        return CommonUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 自定义查询条件查询单挑数据
     * weekend 可以自定义 orderBy
     *
     * @param
     * @return
     * @see #selectByExample(Weekend)
     */
    public T selectOneByWeekend(Weekend<?> weekend) {
        List<T> list = mapper.selectByExample(weekend);
        return CommonUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 自定义SQL语句
     * <p>
     * 例子:
     * </P>
     * <Pre>
     * Weekend<User> weekend = Weekend.of(User.class);
     * weekend.weekendCriteria()
     * .andIsNull(User::getId)
     * .andBetween(User::getId,0,10)
     * .andIn(User::getUserName, Arrays.asList("a","b","c"));
     * weekend.orderBy("id").desc();
     * </Pre>
     *
     * @param
     * @return
     */
    public List<T> selectByExample(Weekend<?> weekend) {
        return mapper.selectByExample(weekend);
    }

    public Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize) {
        return selectByExample(weekend, pageNum, pageSize, null);
    }

    public Page<T> selectByExample(Weekend<?> weekend, int pageNum, int pageSize, Map<String, String> order) {
        Page<T> page = PageHelper.startPage(pageNum, pageSize);
        if (CommonUtils.isNotEmpty(order)) {
            page.setOrderBy(orderSql(order));
        }
        page.doSelectPage(() -> mapper.selectByExample(weekend));
        return page;
    }

    private String orderSql(Map<String, String> order) {
        EntityTable entityTable = EntityHelper.getEntityTable(entityClass);
        Map<String, EntityColumn> propertyMap = entityTable.getPropertyMap();
        StringBuilder orderBy = new StringBuilder();
        order.forEach((k, v) -> {
            EntityColumn column = propertyMap.get(k);
            orderBy.append(column.getColumn()).append(" ").append(v).append(",");
        });
        orderBy.replace(orderBy.length() - 1, orderBy.length(), "");
        return orderBy.toString();
    }

    /**
     * 动态插入，如果主键是自增的，则返回主键
     *
     * @param record
     * @return
     */
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 通过主键动态修改
     *
     * @param record
     * @return
     */
    public int updateSelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 通过主键修改
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 通过条件修改
     *
     * @param record 要修改的值
     * @param where  过滤条件
     * @return
     */
    public int updateByWhere(T record, Weekend<?> where) {
        return mapper.updateByExample(record, where);
    }

    /**
     * 通过条件动态修改
     *
     * @param record
     * @param where
     * @return
     */
    public int updateSelectiveByWhere(T record, Weekend<?> where) {
        return mapper.updateByExampleSelective(record, where);
    }

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 通过条件删除 （慎用!!!）
     *
     * @param record
     * @return
     */
    public int delete(T record) {
        return mapper.delete(record);
    }

    public void assertFactoryAuth(Integer factoryId) {
        if (factoryId != null && !CollectionUtils.isEmpty(Session.getUserFactoryIds())) {
            Assert.isTrue(Session.getUserFactoryIds().contains(factoryId), NO_AUTH, "没有操作指定工厂的权限");
        }
    }

    public T getEntity(Integer id, String code, String sourceCode, Class<T> clazz) {
        T t;
        if (id != null && id > 0) {
            t = this.findById(id);
        } else {
            Weekend<T> weekend = Weekend.of(clazz);
            WeekendCriteria<T, Object> criterial = weekend.weekendCriteria();
            if (CommonUtils.isNotEmpty(code)) {
                criterial.andEqualTo("code", code);
            }
            if (CommonUtils.isNotEmpty(sourceCode)) {
                criterial.andEqualTo("sourceCode", sourceCode);
            }
            criterial.andNotEqualTo("status", Status.DELETE.getCode());
            t = this.selectOneByWeekend(weekend);
        }
        return t;
    }


}
