package com.myha.petadoption.data.model

data class Pet(val uuid: String, val name: String, val location: String) {
}

/*
Pet
id
name
owner (User)
description (html)
type (dog, cat, ..)
breed (null, breed A,...)
age (adult, senior,..)
gender (male, female)
vaccine (Array health status)
attachments (Array Attachment)
 */