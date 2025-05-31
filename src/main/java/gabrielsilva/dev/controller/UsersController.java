package gabrielsilva.dev.controller;

import gabrielsilva.dev.entity.Users;
import gabrielsilva.dev.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersDB = usersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(usersDB);
    }
}
