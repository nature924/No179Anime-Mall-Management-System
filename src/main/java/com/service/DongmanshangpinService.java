package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DongmanshangpinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DongmanshangpinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DongmanshangpinView;


/**
 * 动漫商品
 *
 * @author 
 * @email 
 * @date 2023-01-27 20:13:13
 */
public interface DongmanshangpinService extends IService<DongmanshangpinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DongmanshangpinVO> selectListVO(Wrapper<DongmanshangpinEntity> wrapper);
   	
   	DongmanshangpinVO selectVO(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
   	
   	List<DongmanshangpinView> selectListView(Wrapper<DongmanshangpinEntity> wrapper);
   	
   	DongmanshangpinView selectView(@Param("ew") Wrapper<DongmanshangpinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DongmanshangpinEntity> wrapper);
   	

}

