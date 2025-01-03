package model.dao;

import java.util.List;

import model.entity.User;

public interface UserDao {
	List<User>findAllUsers(); //查詢所有使用者
	User getUser(String username); //先查詢有沒有這個user
}
