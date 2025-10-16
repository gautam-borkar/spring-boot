package com.gborkar.rest_api.UserBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static long id = 0;
  private static List<UserBean> userList = new ArrayList<UserBean>();

  static {
    userList.add(new UserBean(++id, "John Doe", LocalDate.now().minusYears(10)));
    userList.add(new UserBean(++id, "Adam Eve", LocalDate.now().minusYears(7)));
  }

  public List<UserBean> listUsers() {
    return userList;
  }

  public UserBean getUserById(int id) {
    return userList.get(id);
  }

  public void save(UserBean user) {
    user.setId(++id);
    userList.add(user);
  }

}
