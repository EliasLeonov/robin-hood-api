package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.dto.security.CreateUserDto;
import aseca.roobinhood.api.dto.UserDto;
import aseca.roobinhood.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public UserDto save(@RequestBody @Valid CreateUserDto userDto){
        return userService.save(userDto);
    }

    @PostMapping("/update")
    public UserDto update(@RequestBody @Valid UserDto userDto){
        return userService.update(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable(name = "id") @Valid Long id){
        return userService.delete(id);
    }
}
