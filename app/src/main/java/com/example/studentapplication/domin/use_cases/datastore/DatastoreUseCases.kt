package com.example.studentapplication.domin.use_cases.datastore

data class DatastoreUseCases(
    val setIDUseCase: SetIDUseCase,
    val setTokenUseCase: SetTokenUseCase,
    val setNameUseCase: SetUserNameUseCase,
    val setPhoneUseCase: SetPhoneUseCase,
    val setEmailUseCase: SetEmailUseCase,
    val getIDUseCase: GetIDUseCase,
    val getTokenUseCase: GetTokenUseCase,
    val getEmailUseCase: GetEmailUseCase,
    val getPhoneUseCase: GetPhoneUseCase,
    val getNameUseCase: GetNameUseCase ,
    val setPasswordUseCase: SetPasswordUseCase,
    val getPasswordUseCase: GetPasswordUseCase
)
