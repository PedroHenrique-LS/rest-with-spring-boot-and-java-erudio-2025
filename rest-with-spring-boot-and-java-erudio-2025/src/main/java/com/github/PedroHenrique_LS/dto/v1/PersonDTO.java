package com.github.PedroHenrique_LS.dto.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.PedroHenrique_LS.serializer.GenderSerializer;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonFilter("PersonFilter")
//@JsonPropertyOrder({"id", "first_name", "last_name", "gender", "address"})
public class PersonDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
//	@JsonProperty("first_name")
	private String firstName;
	
//	@JsonProperty("last_name")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String lastName;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String phoneNumber;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthDay;
	
	private String address;
	
	@JsonSerialize(using = GenderSerializer.class)
	private String gender;
	private String sensitiveData;

	public PersonDTO() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getSensitiveData() {
		return sensitiveData;
	}

	public void setSensitiveData(String sensitiveData) {
		this.sensitiveData = sensitiveData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
