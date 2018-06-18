package com.sharing.service;

import java.util.List;

import com.sharing.entity.Notification;
import com.sharing.entity.User;

public interface NotificationService {
	
	List<Notification> getNotifications(User user);

}
