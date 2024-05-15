package com.example.demo.service;

import java.util.List;

import com.example.demo.business.UserBean;

public interface UserService {
	public UserBean adduser(UserBean userBean) throws Exception;
	public UserBean adduserwithconstructor(UserBean userBean) throws Exception;
	public UserBean getUserByID(int id) throws Exception;
    public void deleteAllRecords();
    public void deleteById(int id);
    public List<UserBean> getAllUsers();
	public UserBean updateUser(int id, UserBean userBean) throws Exception;
}
