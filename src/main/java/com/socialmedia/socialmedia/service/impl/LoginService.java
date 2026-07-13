package com.socialmedia.socialmedia.service.impl;


import com.socialmedia.socialmedia.dto.AuthRequestDTO;

import java.util.Map;

public interface LoginService {
    Map<String,String> login(AuthRequestDTO authRequestDTO);
}
