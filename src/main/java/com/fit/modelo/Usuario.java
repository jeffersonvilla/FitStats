package com.fit.modelo;

public class Usuario {

	private int id;
	
	private String nombre;
	
	private String email;
	
	private String password;
	
	public static final String FORMATO_NOMBRE = "^[A-Za-z]+(?:\\s[A-Za-z]+)+$";
	
	public static final String FORMATO_PASSWORD = "^(?!.*[\\s\\r\\n\\t]).{8,}$";
	
	public static final String MENSAJE_NOMBRE_NO_VALIDO = "Nombres y apellidos separados por espacio";
	
	public static final String MENSAJE_EMAIL_NO_VALIDO = "Formato 'ejemplo@mail'";
	
	public static final String MENSAJE_PASSWORD_NO_VALIDO = "Minimo 8 caracteres";

	public static final String MENSAJE_USUARIO_REGISTRADO_CORRECTAMENTE = "Usuario registrado con exito";
	
	public static final String MENSAJE_PROBLEMA_REGISTRANDO_USUARIO = "Hubo un problema registrando el usuario";
	
	public static final String MENSAJE_EMAIL_EN_USO = "Este email ya está en uso";
	
	public static final String MENSAJE_CAMPO_VACIO = "Debe llenar este campo";

	public static final String MENSAJE_EMAIL_NO_REGISTRADO = "Email no registrado";

	public static final String MENSAJE_PASSWORD_INCORRECTO = "Contraseña incorrecta";
	
	public Usuario(int id, String nombre, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}

	public Usuario(String nombre, String email, String password) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public Usuario(int id, String nombre, String email, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + "]";
	}
}
