package com.medzak.Service;

import com.medzak.Dto.DepartmentDto;
import com.medzak.Dto.ResponseDto;
import com.medzak.Dto.UserDto;
import com.medzak.Repo.UserRepository;
import com.medzak.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient webClient;

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public ResponseDto getUser(Long userId) {

        ResponseDto responseDto = new ResponseDto();
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);
        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/departments/" + user.getDepartment_id())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getID_USER());
        userDto.setFirstName(user.getFirst_name());
        return userDto;
    }
}