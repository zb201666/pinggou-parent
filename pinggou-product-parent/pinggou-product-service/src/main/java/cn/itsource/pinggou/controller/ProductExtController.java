package cn.itsource.pinggou.controller;

import cn.itsource.pinggou.service.IProductExtService;
import cn.itsource.pinggou.domain.ProductExt;
import cn.itsource.pinggou.query.ProductExtQuery;
import cn.itsource.pinggou.util.AjaxResult;
import cn.itsource.pinggou.util.PageList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductExtController {
    @Autowired
    public IProductExtService productExtService;

    /**
    * 保存和修改公用的
    * @param productExt  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/productExt",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductExt productExt){
        try {
            if(productExt.getId()!=null){
                productExtService.updateById(productExt);
            }else{
                productExtService.save(productExt);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/productExt/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productExtService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取
    @RequestMapping(value = "/productExt/{id}",method = RequestMethod.GET)
    public ProductExt get(@PathVariable("id") Long id)
    {
        return productExtService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/productExt/list",method = RequestMethod.GET)
    public List<ProductExt> list(){
        return productExtService.list();
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/productExt/page",method = RequestMethod.POST)
    public PageList<ProductExt> json(@RequestBody ProductExtQuery query) {
        IPage<ProductExt> productExtIPage = productExtService.page(new Page<>(query.getPage(), query.getSize()));
        return new PageList<>(productExtIPage.getTotal(),productExtIPage.getRecords());
    }

    /**
     * @author zb
     * @description 根据商品id查询详情
     * @date 2019/5/23
     * @name loadProductExtByProductId
     * @param productId
     * @return cn.itsource.pinggou.domain.ProductExt
     */
    @GetMapping("/productExt/loadProductExtByProductId")
    public ProductExt loadProductExtByProductId(Long productId){
        ProductExt productExt = productExtService.loadProductExtByProductId(productId);
        return productExt;
    }
}
