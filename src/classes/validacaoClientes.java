/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Gunner Correa
 */
public class validacaoClientes {

    //Método base para validação com Ereg
    public boolean validarTelefone(String numero) {
        Pattern pattern = Pattern.compile("\\d{4,4}-\\d{4,4}");//Responsável por armazenar uma expressão regular
        Matcher matcher = pattern.matcher(numero); //Responsável pelas buscar
        if (matcher.find()) {//Retorna true se encontrar o padrão de caracteres especificado pelas expressões regulares
            return true;
        } else {
            return false;
        }
    }

//##Validar CPF
    public boolean validaCPF(String strCpf) {
        int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
        int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
        String strDigitoVerificador, strDigitoResultado;

        if (!strCpf.substring(0, 1).equals("")) {
            try {
                strCpf = strCpf.replace('.', ' ');
                strCpf = strCpf.replace('-', ' ');
                strCpf = strCpf.replaceAll(" ", "");
                for (int iCont = 1; iCont < strCpf.length() - 1; iCont++) {
                    iDigitoCPF = Integer.valueOf(strCpf.substring(iCont - 1, iCont)).intValue();
                    iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
                    iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
                }
                iRestoDivisao = (iDigito1Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito1 = 0;
                } else {
                    iDigito1 = 11 - iRestoDivisao;
                }
                iDigito2Aux += 2 * iDigito1;
                iRestoDivisao = (iDigito2Aux % 11);
                if (iRestoDivisao < 2) {
                    iDigito2 = 0;
                } else {
                    iDigito2 = 11 - iRestoDivisao;
                }
                strDigitoVerificador = strCpf.substring(strCpf.length() - 2, strCpf.length());
                strDigitoResultado = String.valueOf(iDigito1) + String.valueOf(iDigito2);
                return strDigitoVerificador.equals(strDigitoResultado);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    //Validar CNPJ
    public boolean validaCNPJ(String strCNPJ) {
        int iSoma = 0, iDigito;
        char[] chCaracteresCNPJ;
        String strCNPJ_Calculado;

        if (!strCNPJ.substring(0, 1).equals("")) {
            try {
                strCNPJ = strCNPJ.replace('.', ' ');
                strCNPJ = strCNPJ.replace('/', ' ');
                strCNPJ = strCNPJ.replace('-', ' ');
                strCNPJ = strCNPJ.replaceAll(" ", "");
                strCNPJ_Calculado = strCNPJ.substring(0, 12);
                if (strCNPJ.length() != 14) {
                    return false;
                }
                chCaracteresCNPJ = strCNPJ.toCharArray();
                for (int i = 0; i < 4; i++) {
                    if ((chCaracteresCNPJ[i] - 48 >= 0) && (chCaracteresCNPJ[i] - 48 <= 9)) {
                        iSoma += (chCaracteresCNPJ[i] - 48) * (6 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if ((chCaracteresCNPJ[i + 4] - 48 >= 0) && (chCaracteresCNPJ[i + 4] - 48 <= 9)) {
                        iSoma += (chCaracteresCNPJ[i + 4] - 48) * (10 - (i + 1));
                    }
                }
                iDigito = 11 - (iSoma % 11);
                strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
                /* Segunda parte */
                iSoma = 0;
                for (int i = 0; i < 5; i++) {
                    if ((chCaracteresCNPJ[i] - 48 >= 0) && (chCaracteresCNPJ[i] - 48 <= 9)) {
                        iSoma += (chCaracteresCNPJ[i] - 48) * (7 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if ((chCaracteresCNPJ[i + 5] - 48 >= 0) && (chCaracteresCNPJ[i + 5] - 48 <= 9)) {
                        iSoma += (chCaracteresCNPJ[i + 5] - 48) * (10 - (i + 1));
                    }
                }
                iDigito = 11 - (iSoma % 11);
                strCNPJ_Calculado += ((iDigito == 10) || (iDigito == 11)) ? "0" : Integer.toString(iDigito);
                return strCNPJ.equals(strCNPJ_Calculado);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /*Validar PIS
     private boolean validaPIS(String strPIS) {
     char i, j, somatorio = 0;
     char chDigitoVerificador;
     char chPISAux;

     try {
     for (i = 0, j = 4; j >= 2; i++, j--) {
     somatorio += ((strPIS.charAt(i) - 0x30) * j);
     }
     for (j = 9; j >= 2; i++, j--) {
     somatorio += ((strPIS.charAt(i) - 0x30) * j);
     }
     if ((somatorio % 11) < 2) {
     chDigitoVerificador = 0;
     } else {
     chDigitoVerificador = (char) (11 - (somatorio % 11));
     }
     chPISAux = (char) (chDigitoVerificador + '0');
     if (strPIS.charAt(11) == chPISAux) {
     return true;
     } else {
     return false;
     }
     } catch (Exception e) {
     return false;
     }
     }
     * */
}
