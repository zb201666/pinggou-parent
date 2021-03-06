package cn.itsource.pinggou.service.impl;

import cn.itsource.pinggou.domain.Brand;
import cn.itsource.pinggou.mapper.BrandMapper;
import cn.itsource.pinggou.query.BrandQuery;
import cn.itsource.pinggou.service.IBrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author zb
 * @since 2019-05-16
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

    @Override
    public IPage<Brand> selectByQuery(Page<Brand> page, BrandQuery query) {
        return baseMapper.selectByQuery(page,query);
    }

    @Override
    @Transactional
    public void updateLogo(Long id, String logo) {
        baseMapper.updateLogo(id,logo);
    }

    @Override
    public Map<String, Object> loadByProductTypeId(Long productTypeId) {
        Map<String, Object> map = new HashMap<>();
        List<Brand> brands = baseMapper.selectList(new QueryWrapper<Brand>().eq("product_type_id", productTypeId));
        map.put("brands", brands);
        TreeSet<String> letters = new TreeSet<>();
        brands.forEach(b->{
            letters.add(b.getFirstLetter());
        });
        map.put("letters", letters);
        return map;
    }
}
