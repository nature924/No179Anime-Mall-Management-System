package com.dao;

import com.entity.DiscussdongmanshangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussdongmanshangpinVO;
import com.entity.view.DiscussdongmanshangpinView;


/**
 * 动漫商品评论表
 * 
 * @author 
 * @email 
 * @date 2023-01-27 20:13:14
 */
public interface DiscussdongmanshangpinDao extends BaseMapper<DiscussdongmanshangpinEntity> {
	
	List<DiscussdongmanshangpinVO> selectListVO(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
	
	DiscussdongmanshangpinVO selectVO(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
	
	List<DiscussdongmanshangpinView> selectListView(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);

	List<DiscussdongmanshangpinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
	
	DiscussdongmanshangpinView selectView(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
	

}
