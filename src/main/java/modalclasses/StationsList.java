package modalclasses;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class StationsList extends ArrayList<Station> {

	private StationsList stations;

	public StationsList() {
		// TODO Auto-generated constructor stub
		stations = new StationsList();
	}

	public StationsList getStations() {
		return stations;
	}

	public void setStations(StationsList stations) {
		this.stations = stations;
	}

}
