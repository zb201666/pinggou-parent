package cn.itsource.pinggou.mapper;

import cn.itsource.pinggou.domain.Brand;
import cn.itsource.pinggou.query.BrandQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * @author zb
     * @description 带分页的高级查询
     * @date 2019/5/16
     * @name selectByQuery
     * @param page
     * @param query
     * @return com.baomidou.mybatisplus.core.metadata.IPage<cn.itsource.pinggou.domain.Brand>
     */
    IPage<Brand> selectByQuery(Page<Brand> page, @Param("query") BrandQuery query);

    /**
     * @author zb
     * @description 根据id更改logo
     * @date 2019/5/20
     * @name updateLogo
     * @param id
     * @param logo
     * @return void
     */
    @Update("update t_brand set logo=#{logo} where id=#{id}")
    void updateLogo(@Param("id") Long id, @Param("logo") String logo);
}
