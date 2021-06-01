package aseca.roobinhood.api.controller;

import aseca.roobinhood.api.domain.dto.CreateUserDto;
import aseca.roobinhood.api.domain.dto.UserDto;
import aseca.roobinhood.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public UserDto save(@RequestBody @Valid CreateUserDto userDto){
        return service.save(userDto);
    }

    @PostMapping("/update")
    public UserDto update(@RequestBody @Valid UserDto userDto){
        return service.update(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable(name = "id") @Valid Long id){
        return service.delete(id);
    }
}
