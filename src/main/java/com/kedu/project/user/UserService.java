package com.kedu.project.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.project.security.crypto.EncryptionUtil;
import com.kedu.project.security.jwt.JWTUtil;

@Service
public class UserService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private JWTUtil jwt;

    public int idChack(UserDTO dto) {
        return dao.idChack(dto);
    }

    public int nickNameChack(UserDTO dto) {
        return dao.nickNameChack(dto);
    }

    public int signup(UserDTO dto) {
        dto.setPassword(EncryptionUtil.encrypt(dto.getPassword()));
        return dao.signup(dto);
    }

    public Map<String, String> login(UserDTO dto) {
        dto.setPassword(EncryptionUtil.encrypt(dto.getPassword()));
        String babySeq = dao.login(dto);
        String token = jwt.createToken(dto.getUser_id());

        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("babySeq", babySeq);

        return map;
    }

    public String pindIdByEmail(UserDTO dto) {
        return dao.pindIdByEmail(dto);
    }
}
