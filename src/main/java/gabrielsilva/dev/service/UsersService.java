package gabrielsilva.dev.service;

import gabrielsilva.dev.entity.Users;
import gabrielsilva.dev.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }
}
