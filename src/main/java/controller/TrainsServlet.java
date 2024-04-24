package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import DAL.TrainsDAL;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modalclasses.Train;
import modalclasses.TrainsList;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/gettrains")
public class TrainsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) {

		try {
			TrainsDAL tdal = new TrainsDAL();
			String from = req.getParameter("from");
			String to = req.getParameter("to");
			TrainsList trains = tdal.getTrains(from, to);
			JSONObject js = new JSONObject();
			for (Train train : trains) {
				js.putOpt(Integer.toString(train.getTrainNo()), train.getTrainName());
			}
			PrintWriter pw = res.getWriter();
			pw.write(js.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
