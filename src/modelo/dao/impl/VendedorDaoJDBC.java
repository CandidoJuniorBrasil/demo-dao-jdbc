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
import modelo.dao.VendedorDao;
import modelo.entidades.Departamento;
import modelo.entidades.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {
	
	private Connection con;
	
	public VendedorDaoJDBC(Connection con) {
		 this.con = con;
	}

	@Override
	public void insert(Vendedor obj) {
	
		PreparedStatement st = null;
		
		try {
			
		    st = con.prepareStatement(
		    		"INSERT INTO seller "
		    		+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
		    		+"VALUES "
		    		+"(?,?,?,?,?)",
		    		Statement.RETURN_GENERATED_KEYS);
		    st.setString(1, obj.getName());
		    st.setString(2, obj.getEmail());
		    st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
		    st.setDouble(4, obj.getBaseSalary());
		    st.setInt(5, obj.getDepartamento().getId());
		    
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
	public void update(Vendedor obj) {
		
		PreparedStatement st = null;
		
		try {
			
		    st = con.prepareStatement(
		    		"UPDATE seller "
		    		+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
		    		+ "WHERE id = ? ");
		    st.setString(1, obj.getName());
		    st.setString(2, obj.getEmail());
		    st.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
		    st.setDouble(4, obj.getBaseSalary());
		    st.setInt(5, obj.getDepartamento().getId());
		    st.setInt(6, obj.getId());		    
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
			
			st = con.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
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
	public Vendedor findById(Integer id) {
	
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.id = department.id "
					+ "WHERE seller.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departamento dep = instanciacaoDepartamento(rs);
				Vendedor vd = instanciacaoVendedor(rs, dep);
				return vd;
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

	private Vendedor instanciacaoVendedor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor vd = new Vendedor();
		vd.setId(rs.getInt("Id"));
		vd.setName(rs.getString("Name"));
		vd.setEmail(rs.getString("Email"));
		vd.setBaseSalary(rs.getDouble("BaseSalary"));
		vd.setBirthDate(rs.getDate("BirthDate"));
		vd.setDepartamento(dep);
		return vd;
	}

	private Departamento instanciacaoDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setDepartamento(rs.getNString("DepName"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					
					dep = instanciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
					
				}
							
				Vendedor vd = instanciacaoVendedor(rs, dep);
				lista.add(vd);
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

	@Override
	public List<Vendedor> findByDepartment(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name");
			
			st.setInt(1, departamento.getId());
			
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap();
			
			while (rs.next()) {
				
				Departamento dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					
					dep = instanciacaoDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), dep);
					
				}
							
				Vendedor vd = instanciacaoVendedor(rs, dep);
				lista.add(vd);
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
