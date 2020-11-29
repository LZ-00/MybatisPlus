package com.lz.service.impl;

import com.lz.entity.Blog;
import com.lz.mapper.BlogMapper;
import com.lz.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lz
 * @since 2020-11-29
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
