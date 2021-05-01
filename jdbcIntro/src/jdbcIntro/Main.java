package jdbcIntro;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	
	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Connection connection = null;
		
		DbHelper helper = new DbHelper();
		PreparedStatement statement = null;//sql sorgusu
		ResultSet resultSet ; //geri d�nen tablo
		try {
			
			connection = helper.getConnection();
			String sql = "delete from city where id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, 4082);//prepareStatement den sonra olmal� bu sat�r
			int result = statement.executeUpdate();
			System.out.println("Kay�t Silindi  "+result+" sat�r etkilendi");
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}finally {
			statement.close();
			connection.close();
		}
		
	}
	public static void selectDemo()throws SQLException {
		Connection connection = null;
		DbHelper helper = new DbHelper();
		Statement statement = null;//sql sorgusu
		ResultSet resultSet ; //geri d�nen tablo
		try {
			
			connection = helper.getConnection();
			statement = connection.createStatement(); //gidece�i ba�lant� i�in
			resultSet = statement.executeQuery("select * from country");
			ArrayList<Country> countries = new ArrayList<Country>();
			while (resultSet.next()) {
				countries.add(new Country(
						resultSet.getString("Code"),
						resultSet.getString("Name"),
						resultSet.getString("Continent"),
						resultSet.getString("Region")));
			}
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}finally {
			connection.close();
		}
	}
	public static void insertDemo()throws SQLException {

		Connection connection = null;
		DbHelper helper = new DbHelper();
		PreparedStatement statement = null;//sql sorgusu
		ResultSet resultSet ; //geri d�nen tablo
		try {
			
			connection = helper.getConnection();
			String sql = "insert into city (Name,countryCode,District,Population) "
					+ "values(?,?,?,?)";
			statement = connection.prepareStatement(sql);
			statement.setString(1, "Of");
			statement.setString(2, "TUR");
			statement.setString(3, "TRABZON");
			statement.setInt(4, 40000);
			int result = statement.executeUpdate();
			System.out.println("Kay�t Eklendi "+result);
		} catch (SQLException exception) {
			helper.showErrorMessage(exception);
		}finally {
			statement.close();
			connection.close();
		}
	}
	public static void updateDemo()throws SQLException {
		// TODO Auto-generated method stub
				Connection connection = null;
				DbHelper helper = new DbHelper();
				PreparedStatement statement = null;//sql sorgusu
				ResultSet resultSet ; //geri d�nen tablo
				try {
					
					connection = helper.getConnection();
					String sql = "update city set population=40000,district='Trabzon' where id = ?";
					statement = connection.prepareStatement(sql);
					statement.setInt(1, 4082);//prepareStatement den sonra olmal� bu sat�r
					int result = statement.executeUpdate();
					System.out.println("Kay�t G�ncellendi  "+result+" etkilendi");
				} catch (SQLException exception) {
					helper.showErrorMessage(exception);
				}finally {
					statement.close();
					connection.close();
				}
				
	}
}
