package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.business.UserBean;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserBean adduserwithconstructor(UserBean userBean) {
    	UserBean user = null;
		try {
			UserEntity userEntity = convertBeantoEntity(userBean);
			userRepository.save(userEntity);
			user = convertEntitytoBean(userEntity);
		}catch(Exception e) {
			throw e;
		}
		return user;
    }
	public UserBean adduser(UserBean userBean) throws Exception {
		UserBean user = null;
		try {
			UserEntity userEntity = convertBeantoEntity(userBean);
			userRepository.save(userEntity);
			user = convertEntitytoBean(userEntity);
		}catch(Exception e) {
			throw e;
		}
		return user;
	}
	
	private UserBean convertEntitytoBean(UserEntity userEntity) {
		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(userEntity, userBean);
		return userBean;
	}
	
	private UserEntity convertBeantoEntity(UserBean userBean) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userBean, userEntity);
		return userEntity;
	}

	@Override
	public UserBean getUserByID(int id) throws Exception {
		UserBean user = null;
		Optional<UserEntity> userEntity = userRepository.findById(id);
		
		if(userEntity.isPresent()) {
			user= convertEntitytoBean(userEntity.get());
		}
		return user;
	}

	@Override
	public void deleteAllRecords() {
		userRepository.deleteAllRecords();
	}
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
	public List<UserBean> getAllUsers() {
        List<UserEntity> entity = userRepository.findAll();
        List<UserBean> bean = new ArrayList<>();
        for(UserEntity e: entity) {
        	bean.add(convertEntitytoBean(e));
        }
        return bean;
    }
	public UserBean updateUser(int id, UserBean userBean) throws Exception {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found"));

        // Update user details
        existingUser.setUserName(userBean.getUserName());
        existingUser.setUserAge(userBean.getUserAge());
        UserEntity e = userRepository.save(existingUser);
        UserBean bean = null;
        bean = convertEntitytoBean(e);
        return bean;
    }
}
