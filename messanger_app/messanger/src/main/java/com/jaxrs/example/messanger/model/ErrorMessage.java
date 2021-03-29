package com.jaxrs.example.messanger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Error")
public class ErrorMessage 
{
	int errorCode;
	String errorMessage;
	String documentation;

	public ErrorMessage() 
	{
		System.out.println("constructor called");
	}
	public ErrorMessage(int errorCode, String errorMessage, String documentation)
	{
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}