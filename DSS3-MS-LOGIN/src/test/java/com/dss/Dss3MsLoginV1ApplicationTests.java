package com.dss;

import com.dss.exception.CustomErrorException;
import com.dss.exception.DuplicateUserException;
import com.dss.exception.NoRegisteredAccountException;
import com.dss.exception.NullValuesException;
import com.dss.model.User;
import com.dss.repository.AdminRepository;
import com.dss.service.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.function.Supplier;

@SpringBootTest
class Dss3MsLoginV1ApplicationTests {

	@MockBean
	private AdminRepository adminRepository;

	@Autowired
	private RegistrationService registrationService;



	@Test
	void addAdminSucc() {
		User user = new User("Patrick@yahoo.com","PatrickTest",
				"RamirezTest","Pogi1@","0909");

		Mockito.when(adminRepository.save(user)).thenReturn(user);
		Assertions.assertEquals(user, registrationService.save(user));
	}

	@Test
	void addAdminNullValues() {
		User user = new User("Patrick@yahoo.com","PatrickTest", "RamirezTest",null,"0909");
		Assertions.assertThrows(NullValuesException.class, () -> registrationService.save(user));
	}

	@Test
	void addAdminInvalidInput(){
		User user = new User("pat@gmail.com", "Pat1", "Pat2","Test!1", "123");
		Assertions.assertThrows(CustomErrorException.class, () -> registrationService.save(user));
	}
	@Test
	void addAdminHasPhoneNumber(){
		User mockUser = new User("pat@gmail.com", "pat", "pat","Test!1", "123");
		Mockito.when(adminRepository.findByPhoneNumber(mockUser.getPhoneNumber())).thenReturn(mockUser);

		User user = new User("test@gmail.com", "test", "test","Test!1", "123");
		Assertions.assertThrows(CustomErrorException.class, () -> registrationService.save(user));
	}

	@Test
	void addAdminAlreadyExistsEmail(){
		User mockUser = new User("pat@gmail.com", "test", "test","Test!1", "654");
		Mockito.when(adminRepository.findByEmailId(mockUser.getEmailId())).thenReturn(Optional.of(mockUser));

		User user = new User("pat@gmail.com", "test", "test","Test!1", "123");
		Assertions.assertThrows(DuplicateUserException.class, () -> registrationService.save(user));
	}

	@Test
	void addAdminInvalidPassFormat(){
		User user = new User("pat@gmail.com", "pat", "pat","pat", "123");
		Assertions.assertThrows(CustomErrorException.class, () -> registrationService.save(user));
	}

	@Test
	void loginAdmin(){
		User user = new User("pat@gmail.com"
				, "Pat!1"
				, "Ramirez"
				,"39bc68b5bce8017aacce3b9797d384e7"
				, "091028172");
		Mockito.when(adminRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword())).thenReturn(user);
		Assertions.assertEquals(user,registrationService.validate("pat@gmail.com","PatrickPogi1!"));
	}

	@Test
	void adminNotFound(){
		User user = new User("pat@gmail.com"
				, "Pat!1"
				, "Ramirez"
				,"39bc68b5bce8017aacce3b9797d384e7"
				, "091028172");
		Mockito.when(adminRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword())).thenReturn(user);
		Assertions.assertThrows(NoRegisteredAccountException.class
				, () -> registrationService.validate("pat@gmail.com","wrongpassword"));
	}




}
