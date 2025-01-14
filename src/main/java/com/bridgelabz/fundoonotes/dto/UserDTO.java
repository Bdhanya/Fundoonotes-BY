package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	@ApiModelProperty(required =true,notes = "Should start with capital letter" )
	@NotBlank(message = "Firstname cannot be blank")
	private String firstName;
	
	@NotBlank(message = "Lastname cannot be blank")
	private String lastName;
	
	@ApiModelProperty(required =true,notes = "Should be a valid mail",example = "venkat@gmail.com")
	@Email(message = "EmailId is not valid")
	private String emailId;
	
	@Length(min =10,max = 10 ,message = "Phonenumber is not valid")
	private String phoneNumber;
	
	
//	@Max(value = 100,message = "qua not valid")
//	private Long qua;
	
	//@Min(value = 8,message = "Password should contain 8 minimum characters")
	@Pattern(regexp = "[a-zA-Z]{8,}",message = "password should should contain only alpabhets")
	private String password;

}
