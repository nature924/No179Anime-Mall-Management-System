package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DongmanshangpinDao;
import com.entity.DongmanshangpinEntity;
import com.service.DongmanshangpinService;
import com.entity.vo.DongmanshangpinVO;
import com.entity.view.DongmanshangpinView;

@Service("dongmanshangpinService")
public class DongmanshangpinServiceImpl extends ServiceImpl<DongmanshangpinDao, DongmanshangpinEntity> implements DongmanshangpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DongmanshangpinEntity> page = this.selectPage(
                new Query<DongmanshangpinEntity>(params).getPage(),
                new EntityWrapper<DongmanshangpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DongmanshangpinEntity> wrapper) {
		  Page<DongmanshangpinView> page =new Query<DongmanshangpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DongmanshangpinVO> selectListVO(Wrapper<DongmanshangpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DongmanshangpinVO selectVO(Wrapper<DongmanshangpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DongmanshangpinView> selectListView(Wrapper<DongmanshangpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DongmanshangpinView selectView(Wrapper<DongmanshangpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
