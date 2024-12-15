package modelo.entidades;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private int id;
		private String departamento;
		
		public Departamento () {
			
		}

		public Departamento(int id, String departamento) {
			
			this.id = id;
			this.departamento = departamento;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDepartamento() {
			return departamento;
		}

		public void setDepartamento(String departamento) {
			this.departamento = departamento;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Departamento other = (Departamento) obj;
			return id == other.id;
		}

		@Override
		public String toString() {
			return "Departamento [id=" + id + ", departamento=" + departamento + "]";
		}			
}
