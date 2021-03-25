package com.nnk.springboot.services;

import java.security.Principal;

public interface IUserInfo {

    String getUserInfo(Principal user);;
}
