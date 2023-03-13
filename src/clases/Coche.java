package clases;

public class Coche {

	private String matricula;
	private String marca;
	private String modelo;
	private int edad;
	private float precio;
	private String dni_propietario;

	/*************** METODOS ***************/

	/*************** CONSTRUCTOR ***************/
	/*************** GETTER && SETTER ***************/

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDni_propietario() {
		return dni_propietario;
	}

	public void setDni_propietario(String dni_propietario) {
		this.dni_propietario = dni_propietario;
	}

}
