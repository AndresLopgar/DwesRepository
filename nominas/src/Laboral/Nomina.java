package Laboral;

public class Nomina {

	private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
			230000 };

	/**
	 * Este es un método que nos devuelde el sueldo del empleado pasado por parámetro.
	 * @param empleado objeto de la clase Empleado.
	 * @return un double que se obtiene a partir de unos calculos del empleado.
	 */
	public Double nomina(Empleado empleado) {
		double sueldoBase = 0;

		switch (empleado.getCategoria()) {
		case 1:
			sueldoBase = SUELDO_BASE[0];
			break;
		case 2:
			sueldoBase = SUELDO_BASE[1];
			break;
		case 3:
			sueldoBase = SUELDO_BASE[2];
			break;
		case 4:
			sueldoBase = SUELDO_BASE[3];
			break;
		case 5:
			sueldoBase = SUELDO_BASE[4];
			break;
		case 6:
			sueldoBase = SUELDO_BASE[5];
			break;
		case 7:
			sueldoBase = SUELDO_BASE[6];
			break;
		case 8:
			sueldoBase = SUELDO_BASE[7];
			break;
		case 9:
			sueldoBase = SUELDO_BASE[8];
			break;
		case 10:
			sueldoBase = SUELDO_BASE[9];
			break;
		}

		double sueldo = sueldoBase + (5000 * empleado.anyosTrabajados);
		return sueldo;
	}
}
