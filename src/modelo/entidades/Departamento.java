package modelo.entidades;

import java.io.Serializable;


public class Departamento implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private Integer id;
		private String departamento;
		
		public Departamento () {
			
		}

		public Departamento (Integer id, String departamento) {
			
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
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
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
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Departamento [id=" + id + ", departamento=" + departamento + "]";
		}			
}
