package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import modelo.dao.DepartamentoDao;
import modelo.entidades.Departamento;

public class DepartamentoDaoJDBC implements DepartamentoDao {
	
	private Connection con;

	public DepartamentoDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Departamento obj) {
		
		PreparedStatement st = null;
		
		try {
			
		    st = con.prepareStatement(
		    		"INSERT INTO department "
		    		+"(Name) "
		    		+"VALUES "
		    		+"(?)",
		    		Statement.RETURN_GENERATED_KEYS);
		    
		    st.setString(1, obj.getDepartamento());
		    
		    int rowsAffected = st.executeUpdate();
		    
		    if (rowsAffected > 0) {
		    	ResultSet rs = st.getGeneratedKeys();
		    	if (rs.next()) {
		    		int id = rs.getInt(1);
		    		obj.setId(id);
		    	}
		    	DB.closeResultSet(rs);
		    }
		    else {
		    	throw new DbException("Erro inesperado! Sem linhas afetadas.");
		    }
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
		    DB.closeStatement(st);
		}			
		
	}

	@Override
	public void update(Departamento obj) {
		
		PreparedStatement st = null;
		
		try {
			
		    st = con.prepareStatement(
		    		"UPDATE department "
		    		+ "SET Name = ? "
		    		+ "WHERE id = ? ");
		    st.setString(1, obj.getDepartamento());
		    st.setInt(2, obj.getId());		    
		    st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
		    DB.closeStatement(st);
		}			
		
	}

	@Override
	public void deleteByID(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			
			st = con.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Departamento findById(Integer id) {
		
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(
					"SELECT department.* "
					+ "FROM department "
					+ "WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = instanciacaoDepartamento(rs);
				return dep;
			}
			return null;
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}
	
	private Departamento instanciacaoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("Id"));
		dep.setDepartamento(rs.getNString("Name"));
		return dep;
	}	
		
	@Override
	public List<Departamento> findAll() {
		
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st = con.prepareStatement(
					"SELECT department.* "
					+"FROM department "
					+"ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Departamento> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("Id"));
				
				if (dep == null) {
					
					dep = instanciacaoDepartamento(rs);
					map.put(rs.getInt("Id"), dep);
					
				}
							
				lista.add(dep);
			}
			return lista;
		}
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
