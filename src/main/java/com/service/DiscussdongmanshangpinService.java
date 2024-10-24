package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussdongmanshangpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussdongmanshangpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussdongmanshangpinView;


/**
 * 动漫商品评论表
 *
 * @author 
 * @email 
 * @date 2023-01-27 20:13:14
 */
public interface DiscussdongmanshangpinService extends IService<DiscussdongmanshangpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussdongmanshangpinVO> selectListVO(Wrapper<DiscussdongmanshangpinEntity> wrapper);
   	
   	DiscussdongmanshangpinVO selectVO(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
   	
   	List<DiscussdongmanshangpinView> selectListView(Wrapper<DiscussdongmanshangpinEntity> wrapper);
   	
   	DiscussdongmanshangpinView selectView(@Param("ew") Wrapper<DiscussdongmanshangpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussdongmanshangpinEntity> wrapper);
   	

}

