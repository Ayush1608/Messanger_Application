
package com.self.restfulwebservices.model;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@ApiModel(description = "Details about the user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Integer id;

	@Size(min = 2, message = "Name should have atleast 2 characters")
	@ApiModelProperty(notes = "Name should have atleast 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = "Birthday cannot be in future")
	private Date birthDate;

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
