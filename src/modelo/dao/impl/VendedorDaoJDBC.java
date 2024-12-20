package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(Integer id) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
