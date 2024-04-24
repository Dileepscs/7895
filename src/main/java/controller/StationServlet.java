package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import DAL.StationDAL;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modalclasses.Station;
import modalclasses.StationsList;

@WebServlet(urlPatterns = "/stations")
@SuppressWarnings("serial")
public class StationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		JSONObject js = new JSONObject();
		StationDAL sdal = null;
		try {
			sdal = new StationDAL();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StationsList stations = null;
		try {
			stations = sdal.getAllStations();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Station s : stations) {
			js.put(s.getStationCode(), s.getStationName());
		}

		PrintWriter pw = null;
		try {
			pw = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.write(js.toString());
	}

}
