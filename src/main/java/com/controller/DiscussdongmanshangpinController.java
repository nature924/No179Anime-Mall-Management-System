package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.DiscussdongmanshangpinEntity;
import com.entity.view.DiscussdongmanshangpinView;

import com.service.DiscussdongmanshangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 动漫商品评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2023-01-27 20:13:14
 */
@RestController
@RequestMapping("/discussdongmanshangpin")
public class DiscussdongmanshangpinController {
    @Autowired
    private DiscussdongmanshangpinService discussdongmanshangpinService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussdongmanshangpinEntity discussdongmanshangpin,
		HttpServletRequest request){
        EntityWrapper<DiscussdongmanshangpinEntity> ew = new EntityWrapper<DiscussdongmanshangpinEntity>();

		PageUtils page = discussdongmanshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdongmanshangpin), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussdongmanshangpinEntity discussdongmanshangpin, 
		HttpServletRequest request){
        EntityWrapper<DiscussdongmanshangpinEntity> ew = new EntityWrapper<DiscussdongmanshangpinEntity>();

		PageUtils page = discussdongmanshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussdongmanshangpin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussdongmanshangpinEntity discussdongmanshangpin){
       	EntityWrapper<DiscussdongmanshangpinEntity> ew = new EntityWrapper<DiscussdongmanshangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussdongmanshangpin, "discussdongmanshangpin")); 
        return R.ok().put("data", discussdongmanshangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussdongmanshangpinEntity discussdongmanshangpin){
        EntityWrapper< DiscussdongmanshangpinEntity> ew = new EntityWrapper< DiscussdongmanshangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussdongmanshangpin, "discussdongmanshangpin")); 
		DiscussdongmanshangpinView discussdongmanshangpinView =  discussdongmanshangpinService.selectView(ew);
		return R.ok("查询动漫商品评论表成功").put("data", discussdongmanshangpinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussdongmanshangpinEntity discussdongmanshangpin = discussdongmanshangpinService.selectById(id);
        return R.ok().put("data", discussdongmanshangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussdongmanshangpinEntity discussdongmanshangpin = discussdongmanshangpinService.selectById(id);
        return R.ok().put("data", discussdongmanshangpin);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussdongmanshangpinEntity discussdongmanshangpin, HttpServletRequest request){
    	discussdongmanshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussdongmanshangpin);
        discussdongmanshangpinService.insert(discussdongmanshangpin);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussdongmanshangpinEntity discussdongmanshangpin, HttpServletRequest request){
    	discussdongmanshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussdongmanshangpin);
        discussdongmanshangpinService.insert(discussdongmanshangpin);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscussdongmanshangpinEntity discussdongmanshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussdongmanshangpin);
        discussdongmanshangpinService.updateById(discussdongmanshangpin);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussdongmanshangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<DiscussdongmanshangpinEntity> wrapper = new EntityWrapper<DiscussdongmanshangpinEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = discussdongmanshangpinService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
