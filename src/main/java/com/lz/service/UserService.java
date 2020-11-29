package com.lz.service;

import com.lz.entity.User;
import com.lz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {


    List<User> list();

}
