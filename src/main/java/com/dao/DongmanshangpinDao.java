package com.dao;

import com.entity.DongmanshangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DongmanshangpinVO;
import com.entity.view.DongmanshangpinView;


/**
 * 动漫商品
 * 
 * @author 
 * @email 
 * @date 2023-01-27 20:13:13
 */
public interface DongmanshangpinDao extends BaseMapper<DongmanshangpinEntity> {
	
	List<DongmanshangpinVO> selectListVO(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
	
	DongmanshangpinVO selectVO(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
	
	List<DongmanshangpinView> selectListView(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);

	List<DongmanshangpinView> selectListView(Pagination page,@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
	
	DongmanshangpinView selectView(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
	

}
