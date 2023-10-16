package com.example.studentapplication.utils.genericFunctions

import android.util.Patterns
import com.example.studentapplication.utils.AuthValidations

fun validateEmail(email: String): AuthValidations {
    return if (email.isEmpty())
        AuthValidations.Error("الإيميل لا يمكن أن يكون فارغا !")
    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        AuthValidations.Error("صيغة الإيميل غير صحيحة !")
    else
        AuthValidations.Success
}

fun validatePassword(password: String): AuthValidations {
    if (!password.isLongEnough())
        return AuthValidations.Error("كلمة السر قصيرة !")
    if (!password.hasEnoughDigits())
        return AuthValidations.Error("كلمة السر يجب أن تحتوي علي رقمين عالأقل")
    if (!password.isMixedCase())
        return AuthValidations.Error("كلمة السر يجب أن تحتوي علي حروف صغيرة وحروف كبيرة !")
    if (!password.hasSpecialChar())
        return AuthValidations.Error("كلمة السر يجب أن تحتوي علي Special Character !")
    return AuthValidations.Success
}

fun validateName(name: String): AuthValidations {
    return if (name.isEmpty())
        AuthValidations.Error("الاسم لا يمكن أن يكون فارغا !")
    else if (name.length < 8)
        AuthValidations.Error("لابد أن يكون الاسم أكبر من 8 حروف!")
    else
        AuthValidations.Success
}

fun validatePhone(phone: String): AuthValidations {
    if (phone.isEmpty())
        return AuthValidations.Error("رقم الهاتف لايمكن أن يكون فارغا !")
    if (!Patterns.PHONE.matcher(phone).matches())
        return AuthValidations.Error("صيغة رقم الهاتف غير صحيحة !")
    return AuthValidations.Success
}

fun validateClassName(className: String): AuthValidations {
    if (className.isEmpty())
        return AuthValidations.Error("قم باختيار السنة الدراسية المقيد بها !")
    return AuthValidations.Success

}

fun String.isLongEnough() = length >= 8
fun String.hasEnoughDigits() = count(Char::isDigit) > 0
fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
fun String.hasSpecialChar() = any { it in "!,+^@#$" }