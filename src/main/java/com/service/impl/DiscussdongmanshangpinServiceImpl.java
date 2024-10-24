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


import com.dao.DiscussdongmanshangpinDao;
import com.entity.DiscussdongmanshangpinEntity;
import com.service.DiscussdongmanshangpinService;
import com.entity.vo.DiscussdongmanshangpinVO;
import com.entity.view.DiscussdongmanshangpinView;

@Service("discussdongmanshangpinService")
public class DiscussdongmanshangpinServiceImpl extends ServiceImpl<DiscussdongmanshangpinDao, DiscussdongmanshangpinEntity> implements DiscussdongmanshangpinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussdongmanshangpinEntity> page = this.selectPage(
                new Query<DiscussdongmanshangpinEntity>(params).getPage(),
                new EntityWrapper<DiscussdongmanshangpinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussdongmanshangpinEntity> wrapper) {
		  Page<DiscussdongmanshangpinView> page =new Query<DiscussdongmanshangpinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DiscussdongmanshangpinVO> selectListVO(Wrapper<DiscussdongmanshangpinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussdongmanshangpinVO selectVO(Wrapper<DiscussdongmanshangpinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussdongmanshangpinView> selectListView(Wrapper<DiscussdongmanshangpinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussdongmanshangpinView selectView(Wrapper<DiscussdongmanshangpinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
