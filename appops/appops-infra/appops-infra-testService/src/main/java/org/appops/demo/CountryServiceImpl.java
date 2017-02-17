package org.appops.demo;

import pyramid.infra.core.provision.Benchmarked;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryServiceImpl implements CountryService {

	private final String USERNAME = "root";
	private final String PASSWORD = "mysql#htznr";
	//private final String CONNECTION_STRING = "jdbc:sqlserver://data2\\sql2014;databaseName=PyramidGis";
	private final String CONNECTION_STRING = "jdbc:mysql://localhost/gisdemo";
	private final String DEFAULT_QUERY_PREFIX = "SELECT country, city, Latitude, Longitude FROM cities limit ";

	@Benchmarked
	public List<Country> getCountryList() {
		return executeQuery(10);
	}
	
	public List<Country> getCountryList(int rowcount){
		return executeQuery(rowcount);
	}
	
	@Benchmarked
	public List<Country> getCountriesFiltered(CountryFilter filter) {
		return executeQuery(filter.getCount());
	}
	
	protected ArrayList<Country> executeQuery(int rowAmount) {

		String queryString = this.DEFAULT_QUERY_PREFIX + rowAmount;
		return executeQuery(queryString);
	}
	
	private double getParsedDouble(String d){
		
		try{
			return Double.parseDouble(d);
		}catch(Exception ex){
			return -1D ;
		}
	}
	
	@Benchmarked
	protected ArrayList<Country> executeQuery(String queryString) {
		ArrayList<Country> result = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(this.CONNECTION_STRING, this.USERNAME, this.PASSWORD);) {

			Statement statement = conn.createStatement();
			ResultSet queryResult = statement.executeQuery(queryString);

			while (queryResult.next()) {
				Country country = new Country(queryResult.getString(1), queryResult.getString(2),
						getParsedDouble(queryResult.getString(3)), getParsedDouble(queryResult.getString(4)));
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
