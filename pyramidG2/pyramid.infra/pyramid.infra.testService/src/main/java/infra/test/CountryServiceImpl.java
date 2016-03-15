package infra.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import pyramid.infra.core.provision.Benchmarked;

public class CountryServiceImpl implements CountryService {

	private final String USERNAME = "sa";
	private final String PASSWORD = "snap3535!";
	private final String CONNECTION_STRING = "jdbc:sqlserver://data2\\sql2014;databaseName=PyramidGis";
	private final String DEFAULT_QUERY_PREFIX = "SELECT TOP ";
	private final String DEFAULT_QUERY_POSTFIX = " country, city, Latitude, Longitude FROM WORLDcities";

	@Override
	public List<Country> getCountryList() {
		return executeQuery(10);
	}
	
	@Benchmarked
	@Override
	public List<Country> getCountriesFiltered(CountryFilter filter) {
		return executeQuery(filter.getCount());
	}
	
	@Benchmarked
	public ArrayList<Country> executeQuery(int rowAmount) {
		String queryString = this.DEFAULT_QUERY_PREFIX + rowAmount + this.DEFAULT_QUERY_POSTFIX;
		return executeQuery(queryString);
	}
	
	@Benchmarked
	public ArrayList<Country> executeQuery(String querySring) {
		ArrayList<Country> result = new ArrayList<Country>();

		try (Connection conn = DriverManager.getConnection(this.CONNECTION_STRING, this.USERNAME, this.PASSWORD);) {

			Statement statement = conn.createStatement();
			ResultSet queryResult = statement.executeQuery(querySring);

			while (queryResult.next()) {
				Country country = new Country(queryResult.getString(1), queryResult.getString(2),
						queryResult.getDouble(3), queryResult.getDouble(4));
				result.add(country);
			}


		} catch (Exception ex) {
			Logger.getAnonymousLogger().log(Level.ALL, ex.getMessage(), ex);
		}

		return result;
	}
	
	@Benchmarked
	public boolean executeCommand(String command) {
		boolean executed = false;

		try (Connection conn = DriverManager.getConnection(this.CONNECTION_STRING, this.USERNAME, this.PASSWORD)) {
			Statement statement = conn.createStatement();
			executed = statement.execute(command);
		} catch (SQLException ex) {
			executed = false;
		}
		return executed;
	}

}
