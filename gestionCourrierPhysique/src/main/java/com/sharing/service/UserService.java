package com.sharing.service;

import java.util.List;

import com.sharing.entity.Courrier;
import com.sharing.entity.UniteBancaire;
import com.sharing.entity.User;

public interface UserService {

	void CreateUser(User user);

	void updateUser(User user);

	User findUSerById(long idUser);

	void detachUser(User user);

	void deleteUser(User user);

	List<User> getAllUsers();

	User findUserByLogin(String login);
	
	User findUserByEmail(String email);

	User findUserByNameSurname(String name, String surname);

	List<User> getUsersByUB(UniteBancaire createdUniteBancaire);
	
	void removeUBFromUser (UniteBancaire uniteBancaire);
	
	List<Courrier> getCourrierEmetteur (User user);
	
	List<Courrier> getCourrierDestinataire (User user);
	
	List<Courrier> getCourrierEnAttente(User user);
}
