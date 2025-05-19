package model;

import exceptions.DniInvalidoException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 *
 * @author polmi
 */
public class ApiService {

    public static List<Usuario> obtenerUsuarios() throws Exception {
        List<Usuario> lista = new ArrayList<>();

        URL url = new URL("http://localhost:8080/api/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Error al conectar con la API. Código: " + status);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder responseText = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            responseText.append(inputLine);
        }
        in.close();
        conn.disconnect();

        // Supongamos que recibimos algo como:
        // [{"id":1,"nombre":"Pol","apellidos":"Miret",...}, {...}]
        String json = responseText.toString().trim();

        // Eliminamos los corchetes y separamos objetos
        json = json.substring(1, json.length() - 1);
        String[] objetos = json.split("(?<=\\}),\\s*(?=\\{)");

        for (String obj : objetos) {
            Usuario u = parsearUsuario(obj);
            lista.add(u);
        }

        return lista;
    }

    public static Usuario obtenerUsuarioPorId(int id) throws Exception {
        URL url = new URL("http://localhost:8080/api/usuarios/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("Error al conectar con la API: " + status);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        String json = response.toString();

        // Quitar llaves exteriores
        json = json.trim();
        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        Map<String, String> campos = new HashMap<>();
        String[] pares = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // ignora comas dentro de comillas

        for (String par : pares) {
            String[] kv = par.split(":", 2);
            if (kv.length == 2) {
                String clave = kv[0].trim().replaceAll("\"", "");
                String valor = kv[1].trim().replaceAll("^\"|\"$", ""); // sin comillas exteriores
                campos.put(clave, valor);
            }
        }

        return new Usuario(
                Integer.parseInt(campos.get("id")),
                campos.get("nombre"),
                campos.get("apellidos"),
                campos.get("email"),
                campos.get("dni"),
                campos.get("telefono"),
                campos.get("direccion")
        );
    }

    // Método simple para extraer los campos del JSON
    private static Usuario parsearUsuario(String json) throws DniInvalidoException {
        Usuario u = new Usuario();
        u.setId(obtenerValorEntero(json, "id"));
        u.setNombre(obtenerValor(json, "nombre"));
        u.setApellidos(obtenerValor(json, "apellidos"));
        u.setEmail(obtenerValor(json, "email"));
        u.setDni(obtenerValor(json, "dni"));
        u.setTelefono(obtenerValor(json, "telefono"));
        u.setDireccion(obtenerValor(json, "direccion"));
        return u;
    }

    private static String obtenerValor(String json, String clave) {
        String regex = "\"" + clave + "\":\"";
        int ini = json.indexOf(regex) + regex.length();
        int fin = json.indexOf("\"", ini);
        if (ini >= regex.length() && fin > ini) {
            return json.substring(ini, fin);
        }
        return "";
    }

    private static int obtenerValorEntero(String json, String clave) {
        String regex = "\"" + clave + "\":";
        int ini = json.indexOf(regex) + regex.length();
        int fin = json.indexOf(",", ini);
        if (fin == -1) {
            fin = json.indexOf("}", ini);
        }
        try {
            return Integer.parseInt(json.substring(ini, fin).trim());
        } catch (Exception e) {
            return 0;
        }
    }

    public static void guardarUsuario(Usuario usuario) throws Exception {
        URL url = new URL("http://localhost:8080/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Crear JSON manualmente
        String json = "{"
                + "\"nombre\":\"" + usuario.getNombre() + "\","
                + "\"apellidos\":\"" + usuario.getApellidos() + "\","
                + "\"email\":\"" + usuario.getEmail() + "\","
                + "\"dni\":\"" + usuario.getDni() + "\","
                + "\"telefono\":\"" + usuario.getTelefono() + "\","
                + "\"direccion\":\"" + usuario.getDireccion() + "\""
                + "}";

        conn.getOutputStream().write(json.getBytes("UTF-8"));

        int status = conn.getResponseCode();
        if (status != 201 && status != 200) {
            throw new RuntimeException("Error al guardar el usuario. Código: " + status);
        }

        conn.disconnect();
    }

}
