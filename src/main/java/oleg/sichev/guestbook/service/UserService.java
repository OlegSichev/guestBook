package oleg.sichev.guestbook.service;

import oleg.sichev.guestbook.DTO.UserDTO;
import oleg.sichev.guestbook.model.User;

public interface UserService {
    User registerNewUserAccount(UserDTO userDTO) throws Exception;
}
