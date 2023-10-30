package com.neosoft.service;

import com.neosoft.dto.Policydto;
import com.neosoft.dto.Responcedto;
import com.neosoft.dto.Userdto;
import com.neosoft.exception.UserGlobalException;
import com.neosoft.model.User;
import com.neosoft.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        User users = userRepository.save(user);
        if (!isValidEmail(users.getEmail())) {
            throw new UserGlobalException("The Email Id does not contain @");
        }
        return users;
    }
    private boolean isValidEmail(String email) {
        return email != null && email.matches(".*@.*");
    }

    @Override
    public Responcedto getUserPolicy(Integer userid) {
        Responcedto responcedto =new Responcedto();
        User user=userRepository.findById(userid);
        Userdto userdto = mapToUser(user);
        ResponseEntity<Policydto> policyresponce = restTemplate.getForEntity("http://localhost:8282/getPolicy/" + user.getId(), Policydto.class);
        Policydto policydto = policyresponce.getBody();
        //how to set the values
        responcedto.setUserdto(userdto);
        responcedto.setPolicydto(policydto);

        return responcedto;
    }

    private Userdto mapToUser(@NotNull User user) {
        //get the value from user and set into userdto
        Userdto userdto =new Userdto();
        userdto.setId(user.getId());
        userdto.setFirstname(user.getFirstname());
        userdto.setLastname(user.getLastname());
        userdto.setEmail(user.getEmail());
        return userdto;
    }


}
