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

import com.entity.DongmanshangpinEntity;
import com.entity.view.DongmanshangpinView;

import com.service.DongmanshangpinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 动漫商品
 * 后端接口
 * @author 
 * @email 
 * @date 2023-01-27 20:13:13
 */
@RestController
@RequestMapping("/dongmanshangpin")
public class DongmanshangpinController {
    @Autowired
    private DongmanshangpinService dongmanshangpinService;

    @Autowired
    private StoreupService storeupService;

    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DongmanshangpinEntity dongmanshangpin,
		HttpServletRequest request){
        EntityWrapper<DongmanshangpinEntity> ew = new EntityWrapper<DongmanshangpinEntity>();

		PageUtils page = dongmanshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dongmanshangpin), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DongmanshangpinEntity dongmanshangpin, 
		HttpServletRequest request){
        EntityWrapper<DongmanshangpinEntity> ew = new EntityWrapper<DongmanshangpinEntity>();

		PageUtils page = dongmanshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dongmanshangpin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DongmanshangpinEntity dongmanshangpin){
       	EntityWrapper<DongmanshangpinEntity> ew = new EntityWrapper<DongmanshangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dongmanshangpin, "dongmanshangpin")); 
        return R.ok().put("data", dongmanshangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DongmanshangpinEntity dongmanshangpin){
        EntityWrapper< DongmanshangpinEntity> ew = new EntityWrapper< DongmanshangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dongmanshangpin, "dongmanshangpin")); 
		DongmanshangpinView dongmanshangpinView =  dongmanshangpinService.selectView(ew);
		return R.ok("查询动漫商品成功").put("data", dongmanshangpinView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DongmanshangpinEntity dongmanshangpin = dongmanshangpinService.selectById(id);
		dongmanshangpin.setClicktime(new Date());
		dongmanshangpinService.updateById(dongmanshangpin);
        return R.ok().put("data", dongmanshangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DongmanshangpinEntity dongmanshangpin = dongmanshangpinService.selectById(id);
		dongmanshangpin.setClicktime(new Date());
		dongmanshangpinService.updateById(dongmanshangpin);
        return R.ok().put("data", dongmanshangpin);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DongmanshangpinEntity dongmanshangpin, HttpServletRequest request){
    	dongmanshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dongmanshangpin);
        dongmanshangpinService.insert(dongmanshangpin);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DongmanshangpinEntity dongmanshangpin, HttpServletRequest request){
    	dongmanshangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(dongmanshangpin);
        dongmanshangpinService.insert(dongmanshangpin);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DongmanshangpinEntity dongmanshangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dongmanshangpin);
        dongmanshangpinService.updateById(dongmanshangpin);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dongmanshangpinService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<DongmanshangpinEntity> wrapper = new EntityWrapper<DongmanshangpinEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = dongmanshangpinService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DongmanshangpinEntity dongmanshangpin, HttpServletRequest request,String pre){
        EntityWrapper<DongmanshangpinEntity> ew = new EntityWrapper<DongmanshangpinEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = dongmanshangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dongmanshangpin), params), params));
        return R.ok().put("data", page);
    }









}
