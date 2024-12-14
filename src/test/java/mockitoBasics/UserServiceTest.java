package mockitoBasics;

import dev.elshan.exception.UserNotCreatedException;
import dev.elshan.model.User;
import dev.elshan.repository.UserRepository;
import dev.elshan.repository.UserRepositoryImpl;
import dev.elshan.service.EmailSendingServiceImpl;
import dev.elshan.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Mock
    EmailSendingServiceImpl emailSendingService;
    User user;
    String id;
    String firstName;
    String lastName;
    Integer age;
    String password;
    String repeatedPassword;

    @BeforeEach
    void init(){
        id = UUID.randomUUID().toString();
        firstName = "elshan";
        lastName = "dunyamaliyev";
        age = 22;
        password = "12345";
        repeatedPassword = "12345";
    }

    @Test
    void testCreatUser_whenUserDetailsProvided_returnUserObject(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);
        user = userService.createUser(id,firstName,lastName,age,password,repeatedPassword);
        assertNotNull(user,"User cannot be null");
        Mockito.verify(userRepository,Mockito.times(2)).save(Mockito.any(User.class));
    }

    @Test
    void testCreatUser_whenUserFirstNameIsNull_throwIllegalArgumentException(){
        String emptyName = "";
        assertThrows(IllegalArgumentException.class,() -> {
            userService.createUser(id,emptyName,lastName,age,password,repeatedPassword);
        });
    }

    @Test
    void testCreatUser_whenUserRepositorySaveException_throwRuntimeException(){
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);
        assertThrows(UserNotCreatedException.class,() -> {
            userService.createUser(id,firstName,lastName,age,password,repeatedPassword);
        },"Should have throw user not created exception instead");
    }

    @Test
    void testCreatUser_whenEmailSendingException_throwRuntimeException(){
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(true);
        Mockito.doThrow(RuntimeException.class)
                        .when(emailSendingService)
                                .sendEmail(Mockito.any(User.class));

//        Mockito.doNothing()
//                .when(emailSendingService)
//                .sendEmail(Mockito.any(User.class));

//        Mockito.doCallRealMethod()
//                .when(emailSendingService)
//                .sendEmail(Mockito.any(User.class));

        assertThrows(UserNotCreatedException.class,() -> {
            userService.createUser(id,firstName,lastName,age,password,repeatedPassword);
        },"Should have throw user not created exception instead");
    }
}
