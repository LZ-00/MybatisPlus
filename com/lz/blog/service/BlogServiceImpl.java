package com.lz.blog.service;

import com.lz.blog.entity.Blog;
import com.lz.blog.mapper.BlogMapper;
import com.lz.blog.service.Service;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements Service {

}
