package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modalclasses.Train;
import modalclasses.TrainsList;

public class TrainsDAL {

	private TrainsList trains;
	private Db db;

	public TrainsDAL() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		db = new Db();
		trains = new TrainsList();
	}

	public TrainsList getTrains(String from, String to) throws SQLException, ClassNotFoundException {
		Connection conn = db.connect();
		ResultSet rs;

		PreparedStatement ps1 = conn.prepareStatement("select stationid from railstations where stationcode = ?");
		ps1.setString(1, from);
		rs = ps1.executeQuery();
		int fromStationId = rs.getInt(1);

		PreparedStatement ps2 = conn.prepareStatement("select stationid from railstations where stationcode = ?");
		ps2.setString(1, from);
		rs = ps2.executeQuery();
		int toStationId = rs.getInt(1);

		String query = "select r.trainnumber,r.trainname,r.trainschedule from "
				+ "railtrainstations c1 join railtrainstations c2 on c1.trainnumber = c2.trainnumber join railtrains r on c1.trainnumber = r.trainnumber "
				+ "where c1.stationcode = ? and c2.stationcode = ? and c2.trainstationindex > c1.trainstationindex";

		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, fromStationId);
		ps.setInt(2, toStationId);
		rs = ps.executeQuery();

		while (rs.next()) {
			// jsobj.put(rs.getString(1), rs.getString(2));
			// System.out.println(rs.getString(1));
			// System.out.println(rs.getString(2));
			Train t = new Train();
			t.setTrainNo(rs.getInt(1));
			t.setTrainName(rs.getString(2));
			t.setTrainSchedule(rs.getString(3));
			trains.add(t);
		}
		return trains;
	}
}
