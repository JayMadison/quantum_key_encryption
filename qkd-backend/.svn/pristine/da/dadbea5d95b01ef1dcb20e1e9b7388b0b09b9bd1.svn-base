package net.it691team1.qkdbackend.dao.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import net.it691team1.qkdbackend.core.SimulatorSearch;
import net.it691team1.qkdbackend.dao.SimulatorDao;
import net.it691team1.qkdbackend.dto.SimulatorDto;

public class SimulatorDaoImpl implements SimulatorDao {
	  private Connection connect = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	public SimulatorDaoImpl() {
		super();
	}
	
	public int insertSim(SimulatorDto sim, PrintWriter out) throws Exception {
		//Create local vaiables for the insert statement
		String eve = "N";
		String eveDetected = "N"; 
		int eveKnownFilters = 0;
		int filterSize = 0;
		int filterSetMatch = 0; 
		int eveImpactedBits = 0;
		int bitsCorrect = 0; 
		int suiteId = 0; 
		//Propigate the variables 
		if (sim.isEve()) { eve = "Y";}	
		if (sim.isEveDet()) {eveDetected = "Y";}
		if (sim.isEve()){eveKnownFilters = sim.getEveKnownFilters();}
		filterSize = sim.getFilterSize();
		filterSetMatch = sim.getFilterSetMatch();
		if (sim.isEve()) {eveImpactedBits = sim.getEveImpactedBits();}
		bitsCorrect = sim.getBitsCorrect();
		suiteId = sim.getSuiteId();
		
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/QKD","backend","Pace2013");
			 
			 preparedStatement = connect
			          .prepareStatement("INSERT INTO `QKD`.`Simulation` (`Eve`, `Eve_Detected`, " +
			          "`Eve_Known_Filters`, `Filter_Size`, `Filter_Set_Match`, `Eve_Impacted_Bits`, " +
			          "`Bits_Correct`, `Suite_ID`, `Run_T`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, now());");
			 
			//Set up the paramaters
			 preparedStatement.setString(1, eve);
			 //Don't propigate fields that are null with no eve
			 if (eve.equals("Y")){
			 preparedStatement.setString(2, eveDetected);
			 preparedStatement.setInt(3, eveKnownFilters);
			 } else {
		     preparedStatement.setNull(2, Types.VARCHAR);
			 preparedStatement.setNull(3, Types.INTEGER); 
			 }
			 preparedStatement.setInt(4, filterSize);
			 preparedStatement.setInt(5, filterSetMatch);
			 preparedStatement.setInt(6, eveImpactedBits);
			 preparedStatement.setInt(7, bitsCorrect);
			 //Only Propigate this is there is a suiteId
			 if (suiteId > 0){
				 preparedStatement.setInt(8, suiteId); 
			 } else {
				 preparedStatement.setNull(8, Types.INTEGER);
			 }
			 if(preparedStatement.executeUpdate() != 1){
				 return 0;
			 }
			 
			 
			 //Now get the max record id
			 preparedStatement = connect
		          .prepareStatement("select max(sim_id) as simNum from QKD.Simulation;");
			 resultSet = preparedStatement.executeQuery();
			 resultSet.first();
			 int simNum = resultSet.getInt("simNum");
			 connect.close();
			 //Since we are doing a max there is only one row
			 return simNum;
		} catch (Exception e) {
			out.println(e.getMessage());
			return 0;
		  //TODO Add A DB Execption
	    } 
	}

	public SimulatorDto getSim(SimulatorSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

}
