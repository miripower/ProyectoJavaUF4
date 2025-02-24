package model.validations;

/**
 *
 * @author polmi
 */
public class UserDataValidations {

    /*
    Verifica el tipo de documento(typeDoc)
    Despues recoge el tipo de documento en un String ID y verifica que sea correcto.
    En caso del typeDoc == 1 (NIF/DNI) Verifica que la longitud sea la correcta (9 caracteres).
    Despues verifica que si los primeros 8 caracteres son digitos.
    Despues verifica que el ultimo caracter sea una letra.
    Creamos una variable llamada calculatedLetter que hace referencia a una funcion llamada calculateLetter que le paasmos los 8 nuemeros del NIF
    y esta funcion se encarga de calulcar la letra. Para calcular la letra lo que hace es sacar el residuo de 23 de los 8 numeros.
    La variable providedLetter recoge la letra que ha introducido el usuario. Despues le decimos que si calculatedLetter no es igual a providedLetter 
    imprima un mensaje de que la letra no es correcta.
    Si todo es correcto devuelve true.
     */
    public static boolean checkId(String id) {
        if (id.length() != 9) {
            System.out.println("Longitud incorrecta del NIF. Debe tener 9 caracteres");
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                System.out.println("Los primeros 8 caracteres del NIF deben ser dígitos");
                return false;
            }
        }

        char lastChar = Character.toUpperCase(id.charAt(8));
        if (!Character.isLetter(lastChar)) {
            System.out.println("El último carácter del NIF debe ser una letra");
            return false;
        }

        char calculatedLetter = calculateLetter(id.substring(0, 8));

        char providedLetter = Character.toUpperCase(id.charAt(8));
        if (calculatedLetter != providedLetter) {
            System.out.println("La letra del NIF no es correcta.");
            return false;
        }
        System.out.println("Formato del DNI correcto");
        return true;
    }

    //Funcion para caluclar la letra del DNI
    private static char calculateLetter(String digits) {
        String letra = "TRWAGMYFPDXBNJZSQVHLCKE";
        int remainder = Integer.parseInt(digits) % 23;
        return letra.charAt(remainder);
    }

    /*
    Primero verificamos que el String de la fecha tenga un total de 10 caracteres(dd-mm-yyyy).
    Despues procedemos con el split a dividir la fecha cortando por '-'. En caso de que introduzcamos 01-01-1991 .split se encarga de 
    almacenarlo en un array tal que asi ejemploArray = [01, 01, 1991].
    Verificamos que el split ha cogido tres partes correctamente.
    Iniciamos tres variables llamadas day, month, year y les asignamos el valor de su respectiva parte del array.
    Por útlimo procedemos a verificar que day, month y year esten correctos.
    Verificamos que day no pueda ser menor que 1 ni mayor que 31, month no puede ser menor a 1 ni mayor a 12, year no puede ser menor a 1900 ni mayor al año actual.
    En caso de que el mes sea 2 (Febrero) primero verificamos que el año no sea bisiesto.
    Para hacer eso calculamos que el residuo del año introducido entre 4 no sea igual a 0 significa que el ese febrero solo tiene 28 dias(no es bisiesto).
    En caso de que esa condicion no se cumpla significa que el año si es bisiesto asi que febrero puede tener 29 dias.
     */
    public static boolean checkFormatDate(String date) {
        if (date.length() != 10) {
            System.out.println("El formato de fecha introducido es incorrecto. Ejemplo: (01-01-1991)");
            return false;
        } else {
            String[] dateParts = date.split("-");

            if (dateParts.length != 3) {
                System.out.println("Error de formato en las campos de la fecha. Ejemplo: (01-01-1991)");
                return false;
            } else {
                int day, month, year;
                day = Integer.parseInt(dateParts[0]);
                month = Integer.parseInt(dateParts[1]);
                year = Integer.parseInt(dateParts[2]);

                if (day < 1 || day > 31) {
                    System.out.println("Dia erroneo, tiene que estar entre 1 y 31");
                    return false;
                } else if (month < 1 || month > 12) {
                    System.out.println("Mes erroneo, tiene que estar entre 1 y 12");
                    return false;
                } else if (year < 1900 || year > 2024) {
                    System.out.println("Año erroneo");
                    return false;
                }

                if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                    System.out.println("El mes que has introducido solo tiene 30 dias");
                    return false;
                } else if (month == 2) {
                    if ((year % 4 != 0) || (year % 100 == 0 && year % 400 != 0)) {
                        if (day > 28) {
                            System.out.println("Febrero en el año " + year + " solo tenia 28 dias");
                            return false;
                        }
                    } else if (day > 29) {
                        System.out.println("Febrero en el año " + year + " tiene 29 dias");
                        return false;
                    }
                }

                System.out.println("Formato de fecha correcto");
                return true;
            }
        }
    }

    /*
    Primero verificamos que la longitud del codigo postal sea 5.
    Despues con un bucle for recorremos caracter por caracter para verificar que sea un digito.
     */
    public static boolean checkPostalCode(String zip) {
        if (zip.length() != 5) {
            System.out.println("La longitud del código postal debe ser de 5 dígitos");
            return false;
        }

        for (int i = 0; i < zip.length(); i++) {
            if (!Character.isDigit(zip.charAt(i))) {
                System.out.println("El código postal solo puede contener números");
                return false;
            }
        }
        System.out.println("Codigo postal correcto");
        return true;
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                System.out.println("En este campo solo se pueden introducir numeros");
                return false;
            }
        }
        System.out.println("Valor correcto");
        return true;
    }

    /*
    Con un bucle for recorremos caracter por caracter para verificar que sea una letra.
     */
    public static boolean isAlphabetic(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))) {
                System.out.println("En este campo solo se pueden introducir letras");
                return false;
            }
        }
        System.out.println("Valor correcto");
        return true;
    }

    /*
    La función verifica si la dirección de correo electrónico tiene un formato válido. 
    Para ello, comprueba si contiene un símbolo "@" y al menos un punto "." después del símbolo "@", 
    y luego verifica que la parte del dominio (después del último punto) sea "com", "es", "net", "cat", "edu" o "co". 
    Si la dirección cumple con estas condiciones, la función devuelve true; de lo contrario, devuelve false.
     */
    public static boolean checkEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1 && atIndex < email.length() - 1) {
            int dotIndex = email.indexOf('.', atIndex);
            if (dotIndex != -1 && dotIndex < email.length() - 1) {
                int lastDotIndex = email.lastIndexOf('.');
                String domain = email.substring(lastDotIndex + 1);
                return domain.equals("com") || domain.equals("es") || domain.equals("net")
                        || domain.equals("cat") || domain.equals("edu") || domain.equals("co");
            }
        }
        return false;
    }

    /*
    Primero verificamos que la longitud del nombre sea menor o igual a 100.
    Seguidamente con un bucle for recorremos caracter por caracter para verificar que todo sean letras.
     */
    public static boolean checkName(String name) {
        if (name.length() < 1 || name.length() >= 100) {
            System.out.println("Valor introducido demasiado largo");
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (!Character.isLetter(name.charAt(i)) && name.charAt(i) != ' ') {
                System.out.println("En este campo solo se pueden introducir letras y espacios");
                return false;
            }
        }

        System.out.println("Valor correcto");
        return true;
    }

}
