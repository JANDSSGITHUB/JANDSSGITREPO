package com.dss;

import com.dss.exception.*;
import com.dss.model.LoginRequestModel;
import com.dss.model.RegistrationRequestModel;
import com.dss.model.User;
import com.dss.model.UserSearchModel;
import com.dss.repository.AdminRepository;
import com.dss.service.LoginService;
import com.dss.service.RegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@SpringBootTest
class Dss3MsLoginV1ApplicationTests {

	@MockBean
	private AdminRepository adminRepository;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private LoginService loginService;




	@Test
	void addAdminSucc() {
		User user = new User("Patrick@yahoo.com","PatrickTest",
				"RamirezTest","Pogi1@","0909");

		Mockito.when(adminRepository.save(user)).thenReturn(user);
		Assertions.assertEquals(user, registrationService.save(user));
	}

	@Test
	void addAdminNullPassword() {
		User user = new User("Patrick@yahoo.com","PatrickTest", "RamirezTest",null,"0909");
		Assertions.assertThrows(NullValuesException.class, () -> registrationService.save(user));
	}
	@Test
	void addAdminNullEmail() {
		User user = new User(null,"PatrickTest", "RamirezTest","P@ssw0rd","0909");
		Assertions.assertThrows(NullValuesException.class, () -> registrationService.save(user));
	}
	@Test
	void addAdminNullFirstName() {
		User user = new User("Patrick@yahoo.com",null, "RamirezTest","P@ssw0rd","0909");
		Assertions.assertThrows(NullValuesException.class, () -> registrationService.save(user));
	}
	@Test
	void addAdminNullLast() {
		User user = new User("Patrick@yahoo.com","PatrickTest", null,"P@ssw0rd","0909");
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


	@Test
	void setErrorMessage(){
		ErrorMessage errorMessage = new ErrorMessage("Error",505,"Error.html");
		ErrorMessage errorMessage1 = new ErrorMessage("error",404);
		ErrorMessage errorMessage2 = new ErrorMessage();
		errorMessage2.setErrorMessage("Error");
		errorMessage2.setErrorCode(505);
		errorMessage2.setDocumentation("ErrorDocs");
	}

	@Test
	void setUser(){
		User user = new User("Pat@gmail.com","Patrick","Ramirez", "091234567");
		User user1 = new User("Pat@gmail.com","Patrick","Ramirez", "password","9199191");
		User user2 = new User();
		user2.setEmailId("Email.com");
		user2.setPassword("Password");
		user2.setFirstName("Patrick");
		user2.setLastName("Ramirez");
		user2.setPhoneNumber("0101");
	}

	@Test
	void setUserManually(){
		User user = new User();
		user.setEmailId("pat@gmail.com");
		user.setFirstName("patrick");
		user.setLastName("Ramirez");
		user.setPassword("P@ssw0rd");
		user.setPhoneNumber("097272");
	}


	@Test
	void findAllUser(){
		List<User> user = new ArrayList<>();
		Mockito.when(adminRepository.findAll()).thenReturn(user);
		UserSearchModel userSearchModel = new UserSearchModel(
				"Pat@gmail.com"
				, "Patrick"
				,"Ramirez"
				, "123");
		Assertions.assertEquals(user,registrationService.findAllUsers(userSearchModel));

	}

	@Test
	void setTestRequestModel(){
		RegistrationRequestModel requestModel = new RegistrationRequestModel();
		requestModel.setUsername("Patrick");
		requestModel.setPassword("Password");
	}
	@Test
	void setTestUserSearchModel(){
		UserSearchModel requestModel = new UserSearchModel("Pat@gmail.com","Pat",
				"Ramirez","0192");
	}

	@Test
	void validateLogin(){
		LoginRequestModel requestModel = new LoginRequestModel();
		User user = new User("pat@gmail.com"
				, "Pat!1"
				, "Ramirez"
				,"efac8067fda1d446b5cbc7c4ea6d651d"
				, "091028172");
		requestModel.setEmailId("pat@gmail.com");
		requestModel.setPassword("Patrick1@");
		Mockito.when(adminRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword())).thenReturn(user);
		Assertions.assertTrue(loginService.validate(requestModel));
	}





}
